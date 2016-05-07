/**
 * Copyright (C) 2016 Alberto Irurueta Carro (alberto@irurueta.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.irurueta.server.commons.useragent;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.uadetector.ReadableDeviceCategory;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * Class to parse and process a user agent from a web browser, web crawler, bot,
 * email client, RESt library, etc.
 * This class will detect which kind of user agent made a request, operating 
 * system information of the requester, kind of device, etc.
 */
public class UserAgentDetector {
    
    /**
     * Logger for this class.
     */
    private static final Logger LOG = Logger.getLogger(UserAgentDetector.class.
            getName());
    
    /**
     * Singleton instance of UserAgentDetector stored in a soft reference (to 
     * keep it cached in memory unless memory is claimed),
     */
    private static SoftReference<UserAgentDetector> mReference;
    
    /**
     * Indicates whether user agent detection is enabled or not.
     */
    private boolean mEnabled;
    
    /**
     * Amount of user agents that are cached.
     */
    private int mCacheSize;
    
    /**
     * Amount of time to keep user agents cached expressed in hours.
     */
    private int mCacheExpirationTime;
    
    /**
     * Internal user agent string parser.
     */
    private UserAgentStringParser mParser;
    
    /**
     * Cache to hold instantiated instances of radable user agents.
     */
    private Cache<String, ReadableUserAgent> mCache;
    
    /**
     * Constructor.
     * Creates and configures a UserAgentDetector instance.
     */
    private UserAgentDetector() {
        mEnabled = false;
        try{
            UserAgentConfiguration cfg = UserAgentConfigurationFactory.
                    getInstance().configure();
            mEnabled = cfg.isUserAgentDetectionEnabled();
            if(mEnabled){
                mCacheSize = cfg.getUserAgentCacheSize();
                mCacheExpirationTime = 
                        cfg.getUserAgentCacheExpirationTimeHours();
                
                mParser = UADetectorServiceFactory.getCachingAndUpdatingParser();
                mCache = CacheBuilder.newBuilder().maximumSize(mCacheSize).
                        expireAfterWrite(mCacheExpirationTime, TimeUnit.HOURS).
                        build();
                LOG.log(Level.INFO, "User agent detection is enabled");
            }else{
                LOG.log(Level.INFO, "User agent detection is disabled");
            }
            
        }catch(Throwable t){
            mEnabled = false;
            LOG.log(Level.INFO, "User agent detection is disabled because " + 
                    "configuration failed", t);
        }
    }
    
    /**
     * Factory method to return the singleton instance of UserAgentDetector 
     * based on current configuration.
     * @return singleton instance.
     */
    public static synchronized UserAgentDetector getInstance() {
        UserAgentDetector detector;
        if (mReference == null || (detector = mReference.get()) == null) {
            detector = new UserAgentDetector();
            mReference = new SoftReference<>(detector);
        }
        return detector;
    }
    
    /**
     * Indicates whether user agent detection is enabled or not. If not enabled
     * no user agent detection will be done when requested.
     * @return true if user agent detection is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return mEnabled;
    }
    
    /**
     * Amount of user agents that are cached.
     * A cache of user agents is used to speed up the parsing process when
     * user agents get repeated, which can happen if a user makes several
     * requests to the server, or multiple users have the same user agent
     * @return amount of user agents that are cached.
     */
    public int getCacheSize() {
        return mCacheSize;
    }
    
    /**
     * Amount of time to keep user agents cached expressed in hours.
     * @return amount of time to keep user agents cached expressed in hours.
     */
    public int getCacheExpirationTime() {
        return mCacheExpirationTime;
    }
    
    /**
     * Detects data on provided user agent string.
     * Detected data can be operating system, user agent type (browser, mail 
     * client, etc), user agent family, type of device, etc.
     * @param userAgentString original user agent string being parsed
     * @return detected user agent data.
     * @throws UserAgentDetectionDisabledException if user agent detection is
     * disabled.
     * @throws UserAgentException if anything else fails.
     */
    public UserAgentData detect(String userAgentString) throws 
            UserAgentDetectionDisabledException, UserAgentException {
        if(!mEnabled) throw new UserAgentDetectionDisabledException();
        
        try{
            ReadableUserAgent result = mCache.getIfPresent(userAgentString);
            if(result == null){
                result = mParser.parse(userAgentString);
                mCache.put(userAgentString, result);
            }        
        
            DeviceCategory deviceCategory = null;
            String deviceCategoryName = null;
            if(result.getDeviceCategory() != null){
                deviceCategory = toDeviceCategory(
                        result.getDeviceCategory().getCategory());
                deviceCategoryName = result.getDeviceCategory().getName();
            }
            String family = (result.getFamily() != null) ? 
                    result.getFamily().getName() : null;
            OperatingSystemFamily osFamily = result.getOperatingSystem() != null ?
                    toOsFamily(result.getOperatingSystem().getFamily()) : null;
            String osFamilyName = null, osName = null, osProducer = null, 
                    osVersion = null;
            if(result.getOperatingSystem() != null){
                osFamilyName = result.getOperatingSystem().getFamilyName();
                osName = result.getOperatingSystem().getName();
                osProducer = result.getOperatingSystem().getProducer();
                osVersion = result.getOperatingSystem().getVersionNumber() != null ?
                        result.getOperatingSystem().getVersionNumber().toVersionString() : null;
            }
            UserAgentType userAgentType = toUserAgentType(result.getType());
            String userAgentVersion = result.getVersionNumber() != null ?
                    result.getVersionNumber().toVersionString() : null;
            
            return new UserAgentData(userAgentString, deviceCategory, 
                    deviceCategoryName, family, osFamily, osFamilyName, osName,
                    osProducer, osVersion, userAgentType, userAgentVersion);
        }catch(Throwable t){
            throw new UserAgentException(t);
        }
    }
    
    /**
     * Stops internal user agent parser. Once closed, user agent detection will 
     * no longer be available.
     */
    public void close() {
        if(mParser != null) mParser.shutdown();
        if(mCache != null) mCache.invalidateAll();
        mEnabled = false;
        LOG.log(Level.INFO, "User agent detection has been shutdown");
    }
    
    /**
     * Resets UserAgentDetector so a new instance having new configuration can
     * be created.
     */
    protected static synchronized void reset() {
        UserAgentDetector detector = mReference != null ? 
                mReference.get() : null;
        if(detector != null){
            detector.close();
        }
        mReference = null;
    }   
    
    /**
     * This method is called on garbage collection.
     * When this method is called, internal user agent parser will be stopped.
     * @throws Throwable if anything fails.
     */
    @Override
    protected void finalize() throws Throwable {
        try{
            close();
        }catch(Throwable ignore){
        }finally{
            super.finalize();
        }
    }
    
    /**
     * Converts an internal device category enumerator into a DeviceCategory 
     * enumerator used by this package.
     * @param category internal category to be converted.
     * @return a device category.
     */
    protected DeviceCategory toDeviceCategory(
            ReadableDeviceCategory.Category category) {
        if(category != null){
            switch(category){
                case GAME_CONSOLE:
                    return DeviceCategory.GAME_CONSOLE;
                case OTHER:
                    return DeviceCategory.OTHER;
                case PDA:
                    return DeviceCategory.PDA;
                case PERSONAL_COMPUTER:
                    return DeviceCategory.PERSONAL_COMPUTER;
                case SMARTPHONE:
                    return DeviceCategory.SMARTPHONE;
                case SMART_TV:
                    return DeviceCategory.SMART_TV;
                case TABLET:
                    return DeviceCategory.TABLET;
                case UNKNOWN:
                    return DeviceCategory.UNKNOWN;
                case WEARABLE_COMPUTER:
                    return DeviceCategory.WEARABLE_COMPUTER;
            }
        }
        return null;
    }
    
    /**
     * Converts an internal OS family enumerator into an OperatingSystemFamily.
     * enumerator used by this package.
     * @param family internal Os family to be converted.
     * @return an OS family.
     */    
    protected OperatingSystemFamily toOsFamily(
            net.sf.uadetector.OperatingSystemFamily family) {
        if(family != null){
            switch(family){
                case AIX:
                    return OperatingSystemFamily.AIX;
                case AMIGA_OS:
                    return OperatingSystemFamily.AMIGA_OS;
                case ANDROID:
                    return OperatingSystemFamily.ANDROID;
                case AROS:
                    return OperatingSystemFamily.AROS;
                case BADA:
                    return OperatingSystemFamily.BADA;
                case BEOS:
                    return OperatingSystemFamily.BEOS;
                case BLACKBERRY_OS:
                    return OperatingSystemFamily.BLACKBERRY_OS;
                case BREW:
                    return OperatingSystemFamily.BREW;
                case BSD:
                    return OperatingSystemFamily.BSD;
                case DANGEROS:
                    return OperatingSystemFamily.DANGEROS;
                case FIREFOX_OS:
                    return OperatingSystemFamily.FIREFOX_OS;
                case HAIKU:
                    return OperatingSystemFamily.HAIKU;
                case HPUX:
                    return OperatingSystemFamily.HPUX;
                case INFERNO_OS:
                    return OperatingSystemFamily.INFERNO_OS;
                case IOS:
                    return OperatingSystemFamily.IOS;
                case IRIX:
                    return OperatingSystemFamily.IRIX;
                case JVM:
                    return OperatingSystemFamily.JVM;
                case LINUX:
                    return OperatingSystemFamily.LINUX;
                case MAC_OS:
                    return OperatingSystemFamily.MAC_OS;
                case MEEGO:
                    return OperatingSystemFamily.MEEGO;
                case MINIX:
                    return OperatingSystemFamily.MINIX;
                case MORPHOS:
                    return OperatingSystemFamily.MORPHOS;
                case NINTENDO:
                    return OperatingSystemFamily.NINTENDO;
                case OPENVMS:
                    return OperatingSystemFamily.OPENVMS;
                case OS_2:
                    return OperatingSystemFamily.OS_2;
                case OS_X:
                    return OperatingSystemFamily.OS_X;
                case PALM_OS:
                    return OperatingSystemFamily.PALM_OS;
                case PLAYSTATION_VITA:
                    return OperatingSystemFamily.PLAYSTATION_VITA;
                case QNX:
                    return OperatingSystemFamily.QNX;
                case RISC_OS:
                    return OperatingSystemFamily.RISC_OS;
                case SAILFISH_OS:
                    return OperatingSystemFamily.SAILFISH_OS;
                case SOLARIS:
                    return OperatingSystemFamily.SOLARIS;
                case SYLLABLE:
                    return OperatingSystemFamily.SYLLABLE;
                case SYMBIAN:
                    return OperatingSystemFamily.SYMBIAN;
                case TIZEN:
                    return OperatingSystemFamily.TIZEN;
                case UNKNOWN:
                    return OperatingSystemFamily.UNKNOWN;
                case WEBOS:
                    return OperatingSystemFamily.WEBOS;                    
                case WII_OS:
                    return OperatingSystemFamily.WII_OS;
                case WINDOWS:
                    return OperatingSystemFamily.WINDOWS;
                case XROSSMEDIABAR:
                    return OperatingSystemFamily.XROSSMEDIABAR;
            }
        }
        return null;
    }
    
    /**
     * Converts an internal user agent type enumerator into a UserAgentType
     * enumerator used by this package.
     * @param type internal user agent type to be converted.
     * @return a user agent type.
     */
    protected UserAgentType toUserAgentType(
            net.sf.uadetector.UserAgentType type) {
        if(type != null){
            switch(type){
                case BROWSER:
                    return UserAgentType.BROWSER;
                case EMAIL_CLIENT:
                    return UserAgentType.EMAIL_CLIENT;
                case FEED_READER:
                    return UserAgentType.FEED_READER;
                case LIBRARY:
                    return UserAgentType.LIBRARY;
                case MEDIAPLAYER:
                    return UserAgentType.MEDIAPLAYER;
                case MOBILE_BROWSER:
                    return UserAgentType.MOBILE_BROWSER;
                case OFFLINE_BROWSER:
                    return UserAgentType.OFFLINE_BROWSER;
                case OTHER:
                    return UserAgentType.OTHER;
                case ROBOT:
                    return UserAgentType.ROBOT;
                case UNKNOWN:
                    return UserAgentType.UNKNOWN;
                case USERAGENT_ANONYMIZER:
                    return UserAgentType.USERAGENT_ANONYMIZER;
                case VALIDATOR:
                    return UserAgentType.VALIDATOR;
                case WAP_BROWSER:
                    return UserAgentType.WAP_BROWSER;
            }
        }
        return null;
    }
}
