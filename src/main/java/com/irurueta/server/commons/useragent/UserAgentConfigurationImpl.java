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

import com.irurueta.server.commons.configuration.ConfigurationException;
import java.util.Properties;

/**
 * Class containing configuration for user agent detection services.
 */
public class UserAgentConfigurationImpl implements UserAgentConfiguration {

    /**
     * Indicates whether user agent detection is enabled or not.
     */
    private boolean mUserAgentDetectionEnabled;
    
    /**
     * Amount of user agents that are cached.
     * A cache of user agents is used to speed up the parsing process when
     * user agents get repeated, which can happen if a user makes several
     * requests to the server, or multiple users have the same user agent.
     */
    private int mUserAgentCacheSize;
    
    /**
     * Maximum amount of time to keep user agents cached expressed in hours.
     */
    private int mUserAgentCacheExpirationTimeHours;
    
    /**
     * Constructor.
     */
    public UserAgentConfigurationImpl() {
        mUserAgentDetectionEnabled = UserAgentConfigurationFactory.
                DEFAULT_USER_AGENT_DETECTION_ENABLED;
        mUserAgentCacheSize = UserAgentConfigurationFactory.
                DEFAULT_USER_AGENT_CACHE_SIZE;
        mUserAgentCacheExpirationTimeHours = UserAgentConfigurationFactory.
                DEFAULT_USER_AGENT_CACHE_EXPIRATION_TIME_HOURS;
    }
    
    /**
     * Constructor from properties.
     * @param properties properties containing configuration.
     * @throws ConfigurationException if any property value is invalid.
     */
    public UserAgentConfigurationImpl(Properties properties) 
            throws ConfigurationException {
        fromProperties(properties);
    }
            
    /**
     * Indicates whether user agent detection is enabled or not.
     * @return true if user agent detection is enabled, false otherwise.
     */    
    @Override
    public boolean isUserAgentDetectionEnabled() {
        return mUserAgentDetectionEnabled;
    }
    
    /**
     * Returns amount of user agents that are cached.
     * A cache of user agents is used to speed up the parsing process when
     * user agents get repeated, which can happen if a user makes several
     * requests to the server, or multiple users have the same user agent.
     * @return amount of user agents that are cached
     */
    @Override
    public int getUserAgentCacheSize() {
        return mUserAgentCacheSize;
    }
    
    /**
     * Returns maximum amount of time to keep user agents cached expressed
     * in hours.
     * @return maximum amount of time to keep user agents cached expressed
     * in hours.
     */
    @Override
    public int getUserAgentCacheExpirationTimeHours() {
        return mUserAgentCacheExpirationTimeHours;
    }
    
    /**
     * Loads configuration from provided properties.
     * @param properties properties containing configuration.
     * @throws ConfigurationException if any properties value is invalid.
     */
    @Override
    public final void fromProperties(Properties properties) 
            throws ConfigurationException {
        try {
            mUserAgentDetectionEnabled = Boolean.parseBoolean(properties.getProperty(
                    UserAgentConfigurationFactory.
                    USER_AGENT_DETECTION_ENABLED_PROPERTY, Boolean.toString(
                    UserAgentConfigurationFactory.
                    DEFAULT_USER_AGENT_DETECTION_ENABLED)));
            
            mUserAgentCacheSize = Integer.parseInt(properties.getProperty(
                    UserAgentConfigurationFactory.
                    USER_AGENT_CACHE_SIZE_PROPERTY, Integer.toString(
                    UserAgentConfigurationFactory.
                    DEFAULT_USER_AGENT_CACHE_SIZE)));
            
            if (mUserAgentCacheSize <= 0) {
                throw new ConfigurationException(
                        "User agent cache size must be positive");
            }
            
            mUserAgentCacheExpirationTimeHours = Integer.parseInt(properties.
                    getProperty(UserAgentConfigurationFactory.
                    USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, Integer.
                    toString(UserAgentConfigurationFactory.
                    DEFAULT_USER_AGENT_CACHE_EXPIRATION_TIME_HOURS)));
            
            if (mUserAgentCacheExpirationTimeHours <= 0) {
                throw new ConfigurationException("User agent cache " + 
                        "expiration time must be positive");
            }
        } catch(ConfigurationException e) {
            throw e;
        } catch(Exception e) {
            throw new ConfigurationException(e);
        }
    }

    /**
     * Converts current configuration into properties.
     * @return properties containing configuration.
     */
    @Override
    public Properties toProperties() {
        Properties properties = new Properties();
        properties.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_DETECTION_ENABLED_PROPERTY, Boolean.toString(mUserAgentDetectionEnabled));
        properties.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, Integer.toString(mUserAgentCacheSize));
        properties.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, 
                Integer.toString(mUserAgentCacheExpirationTimeHours));
        return properties;
    }
}
