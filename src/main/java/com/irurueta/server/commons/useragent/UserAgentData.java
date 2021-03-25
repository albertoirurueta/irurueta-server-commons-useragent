/*
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

/**
 * Contains detected user agent data.
 */
public class UserAgentData {
    
    /**
     * Original user agent string.
     */
    private String mUserAgent;
    
    /**
     * Detected device category based on user agent string.
     */
    private DeviceCategory mDeviceCategory;
    
    /**
     * Detected device category name based on user agent string.
     */
    private String mDeviceCategoryName;
    
    /**
     * Detected user agent family based on user agent string.
     */
    private String mFamily;
    
    /**
     * Detected operating system family based on user agent string.
     */
    private OperatingSystemFamily mOsFamily;
    
    /**
     * Detected operating system family name based on user agent string.
     */
    private String mOsFamilyName;
    
    /**
     * Detected operating system name based on user agent string.
     */
    private String mOsName;
    
    /**
     * Detected operating system producer based on user agent string.
     */
    private String mOsProducer;
    
    /**
     * Detected operating system version based on user agent string.
     */
    private String mOsVersion;
    
    /**
     * Detected user agent type.
     */
    private UserAgentType mUserAgentType;
    
    /**
     * Version of user agent (i.e. browser version, etc).
     */
    private String mUserAgentVersion;
    
    /**
     * Constructor.
     * @param userAgent Original user agent string.
     * @param deviceCategory Detected device category based on user agent 
     * string.
     * @param deviceCategoryName Detected category name based on user agent 
     * string.
     * @param family Detected user agent family based on user agent string.
     * @param osFamily Detected operating system family based on user agent 
     * string.
     * @param osFamilyName Detected operating system family name based on user 
     * agent string.
     * @param osName Detected operating system name based on user agent string.
     * @param osProducer Detected operating system producer based on user agent 
     * string.
     * @param osVersion Detected operating system version based on user agent 
     * string.
     * @param userAgentType Detected user agent type.
     * @param userAgentVersion version of user agent (i.e. browser version, 
     * etc).
     */
    public UserAgentData(String userAgent, DeviceCategory deviceCategory,
            String deviceCategoryName, String family, 
            OperatingSystemFamily osFamily, String osFamilyName, String osName, 
            String osProducer, String osVersion, UserAgentType userAgentType,
            String userAgentVersion) {
        mUserAgent = userAgent;
        mDeviceCategory = deviceCategory;
        mDeviceCategoryName = deviceCategoryName;
        mFamily = family;
        mOsFamily = osFamily;
        mOsFamilyName = osFamilyName;
        mOsName = osName;
        mOsProducer = osProducer;
        mOsVersion = osVersion;
        mUserAgentType = userAgentType;
        mUserAgentVersion = userAgentVersion;
    }
    
    /**
     * Returns original user agent string.
     * @return original user agent string.
     */
    public String getUserAgent() {
        return mUserAgent;
    }

    /**
     * Sets original user agent string.
     * @param userAgent original user agent string to be set.
     */
    protected void setUserAgent(String userAgent) {
        this.mUserAgent = userAgent;
    }
    
    /**
     * Returns detected device category based on user agent string.
     * @return detected device category.
     */
    public DeviceCategory getDeviceCategory() {
        return mDeviceCategory;
    }

    /**
     * Sets detected device category based on user agent string.
     * @param deviceCategory device category to be set.
     */
    protected void setDeviceCategory(DeviceCategory deviceCategory) {
        this.mDeviceCategory = deviceCategory;
    }
    
    /**
     * Returns detected device category name based on user agent string.
     * @return detected device category name based on user agent string.
     */
    public String getDeviceCategoryName() {
        return mDeviceCategoryName;
    }
    
    /**
     * Sets detected device category name based on user agent string.
     * @param deviceCategoryName device category name to be set.
     */
    protected void setDeviceCategoryName(String deviceCategoryName) {
        this.mDeviceCategoryName = deviceCategoryName;
    }

    /**
     * Returns detected user agent family based on user agent string.
     * @return detected user agent family based on user agent string.
     */
    public String getFamily() {
        return mFamily;
    }
    
    /**
     * Sets detected user agent family based on user agent string.
     * @param family user agent family to be set.
     */
    protected void setFamily(String family) {
        this.mFamily = family;
    }

    /**
     * Returns detected operating system family based on user agent string.
     * @return detected operating system family based on user agent string.
     */
    public OperatingSystemFamily getOsFamily() {
        return mOsFamily;
    }
    
    /**
     * Sets detected operating system family based on user agent string.
     * @param osFamily detected operating system fmaily based on user agent
     * string.
     */
    protected void setOsFamily(OperatingSystemFamily osFamily) {
        this.mOsFamily = osFamily;
    }

    /**
     * Returns detected operating system family name based on user agent string.
     * @return detected operating system family name based on user agent string.
     */
    public String getOsFamilyName() {
        return mOsFamilyName;
    }
    
    /**
     * Sets detected operating system family name based on user agent string.
     * @param osFamilyName operating system family name to be set.
     */
    protected void setOsFamilyName(String osFamilyName) {
        this.mOsFamilyName = osFamilyName;
    }

    /**
     * Returns detected operating system name based on user agent string.
     * @return detected operating system name based on user agent string.
     */
    public String getOsName() {
        return mOsName;
    }
    
    /**
     * Sets detected operating system name based on user agent string.
     * @param osName operating system name to be set.
     */
    protected void setOsName(String osName) {
        this.mOsName = osName;
    }

    /**
     * Returns detected operating system producer based on user agent string.
     * @return detected operating system producer based on user agent string.
     */
    public String getOsProducer() {
        return mOsProducer;
    }
    
    /**
     * Sets detected operating system producer based on user agent string.
     * @param osProducer operating system producer to be set.
     */
    protected void setOsProducer(String osProducer) {
        this.mOsProducer = osProducer;
    }

    /**
     * Returns detected operating system version based on user agent string.
     * @return detected operating system version based on user agent string.
     */
    public String getOsVersion() {
        return mOsVersion;
    }
    
    /**
     * Sets detected operating system version based on user agent string.
     * @param osVersion operating system version to be set.
     */
    protected void setOsVersion(String osVersion) {
        this.mOsVersion = osVersion;
    }

    /**
     * Returns detected user agent type.
     * @return detected user agent type.
     */
    public UserAgentType getUserAgentType() {
        return mUserAgentType;
    }
    
    /**
     * Sets detected user agent type.
     * @param userAgentType user agent type to be set.
     */
    protected void setUserAgentType(UserAgentType userAgentType) {
        this.mUserAgentType = userAgentType;
    }
    
    /**
     * Returns version of user agent (i.e. browser version, etc).
     * @return version of user agent.
     */
    public String getUserAgentVersion() {
        return mUserAgentVersion;
    }
    
    /**
     * Sets version of user agent (i.e. browser version, etc).
     * @param userAgentVersion version of user agent to be set.
     */
    public void setUserAgentVersion(String userAgentVersion) {
        this.mUserAgentVersion = userAgentVersion;
    }
    
    /**
     * Indicates if detected user agent data corresponds to a desktop
     * web browser or email client.
     * If true, server should display a typical desktop web UI.
     * @return true if user agent appears to belong to a desktop web
     * browser or email client.
     */
    public boolean isDesktop() {
        return isDesktop(mUserAgentType, mDeviceCategory);
    }
    
    /**
     * Indicates if detected user agent data corresponds to a mobile web browser
     * or email client.
     * If true, server should display a UI adapted for small screens like 
     * smartphones.
     * @return true if user agent appears to belong to a mobile web browser or
     * email client.
     */
    public boolean isMobile() {
        return isMobile(mUserAgentType, mDeviceCategory);
    }
    
    /**
     * Indicates if detected user agent data corresponds to a tablet web browser
     * or email client.
     * If true, server should display a UI similar to desktop screens but 
     * enhanced for touch event (i.e. having larger buttons, support for touch
     * gestures, etc).
     * @return true if user agent appears to belong to a tablet web browser or 
     * email client.
     */
    public boolean isTablet() {
        return isTablet(mUserAgentType, mDeviceCategory);
    }
    
    /**
     * Indicates if detected user agent data corresponds to a smart TV.
     * If true, server should display a simple UI ready to be used using a
     * TV remote control.
     * @return true if user agent appears to belong to a TV web browser or email
     * client.
     */
    public boolean isSmartTV() {
        return isSmartTV(mUserAgentType, mDeviceCategory);
    }
    
    /**
     * Indicates if provided user agent type and device category corresponds to 
     * a desktop web browser or email client.
     * If true, server should display a typical desktop web UI.
     * @param userAgentType user agent type (browser, email client, library, 
     * etc).
     * @param deviceCategory device category (desktop PC, smartphone, tablet, 
     * smart TV, game console, etc).
     * @return true if user agent appears to belong to a desktop web
     * browser or email client.
     */
    public static boolean isDesktop(UserAgentType userAgentType, 
            DeviceCategory deviceCategory) {
        return (userAgentType == UserAgentType.BROWSER || 
                userAgentType == UserAgentType.EMAIL_CLIENT) &&
                (deviceCategory == DeviceCategory.OTHER ||
                deviceCategory == DeviceCategory.PERSONAL_COMPUTER ||
                deviceCategory == DeviceCategory.UNKNOWN) || 
                (!isMobile(userAgentType, deviceCategory) && 
                !isTablet(userAgentType, deviceCategory) && 
                !isSmartTV(userAgentType, deviceCategory)) ;
    }
    
    /**
     * Indicates if provided user agent type and device category corresponds to 
     * a mobile web browser or email client.
     * If true, server should display a UI adapted for small screens like 
     * smartphones.
     * @param userAgentType user agent type (browser, email client, library, 
     * etc).
     * @param deviceCategory device category (desktop PC, smartphone, tablet, 
     * smart TV, game console, etc).
     * @return true if user agent appears to belong to a mobile web browser or
     * email client.
     */
    public static boolean isMobile(UserAgentType userAgentType, 
            DeviceCategory deviceCategory) {
        return (userAgentType == UserAgentType.BROWSER ||
                userAgentType == UserAgentType.EMAIL_CLIENT ||
                userAgentType == UserAgentType.MOBILE_BROWSER) &&
                (deviceCategory == DeviceCategory.PDA ||
                deviceCategory == DeviceCategory.SMARTPHONE ||
                deviceCategory == DeviceCategory.WEARABLE_COMPUTER);
    }
    
    /**
     * Indicates if detected user agent type and device category corresponds to 
     * a tablet web browser or email client.
     * If true, server should display a UI similar to desktop screens but 
     * enhanced for touch event (i.e. having larger buttons, support for touch
     * gestures, etc).
     * @param userAgentType user agent type (browser, email client, library, 
     * etc).
     * @param deviceCategory device category (desktop PC, smartphone, tablet, 
     * smart TV, game console, etc).
     * @return true if user agent appears to belong to a tablet web browser or 
     * email client.
     */
    public static boolean isTablet(UserAgentType userAgentType, 
            DeviceCategory deviceCategory) {
        return (userAgentType == UserAgentType.BROWSER ||
                userAgentType == UserAgentType.EMAIL_CLIENT ||
                userAgentType == UserAgentType.MOBILE_BROWSER) &&
                (deviceCategory == DeviceCategory.TABLET);
    }
    
    /**
     * Indicates if detected user agent type and device category corresponds to 
     * a smart TV.
     * If true, server should display a simple UI ready to be used using a
     * TV remote control.
     * @param userAgentType user agent type (browser, email client, library, 
     * etc).
     * @param deviceCategory device category (desktop PC, smartphone, tablet, 
     * smart TV, game console, etc).
     * @return true if user agent appears to belong to a TV web browser or email
     * client.
     */
    public static boolean isSmartTV(UserAgentType userAgentType, 
            DeviceCategory deviceCategory) {
        return (userAgentType == UserAgentType.BROWSER ||
                userAgentType == UserAgentType.EMAIL_CLIENT ||
                userAgentType == UserAgentType.MOBILE_BROWSER) &&
                (deviceCategory == DeviceCategory.SMART_TV);
    }    
}
