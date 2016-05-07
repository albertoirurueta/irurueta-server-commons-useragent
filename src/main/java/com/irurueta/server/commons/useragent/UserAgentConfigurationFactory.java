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

import com.irurueta.server.commons.configuration.BaseConfigurationFactory;
import com.irurueta.server.commons.configuration.ConfigurationException;
import java.lang.ref.SoftReference;
import java.util.Properties;

/**
 * Class in charge of configuring user agent detection services.
 */
public class UserAgentConfigurationFactory extends 
        BaseConfigurationFactory<UserAgentConfiguration> {

    /**
     * Property indicating whether user agent detection is enabled or not.
     */
    public static final String USER_AGENT_DETECTION_ENABLED_PROPERTY = 
            "com.irurueta.server.commons.useragent.USER_AGENT_DETECTION_ENABLED";
    
    /**
     * Default value indicating whether user agent detection is enabled or not.
     */
    public static final boolean DEFAULT_USER_AGENT_DETECTION_ENABLED = true;
    
    /**
     * Property indicating the amount of user agents that are cached.
     * A cache of user agents is used to speed up the parsing process when
     * user agents get repeated, which can happen if a user makes several 
     * requests to the server, or multiple users have the same user agent.
     */
    public static final String USER_AGENT_CACHE_SIZE_PROPERTY =
            "com.irurueta.server.commons.useragent.USER_AGENT_CACHE_SIZE";
    
    /**
     * Default number of different user agents being cached.
     */
    public static final int DEFAULT_USER_AGENT_CACHE_SIZE = 100;
    
    /**
     * Property indicating the maximum amount of time to keep user agents cached
     * expressed in hours.
     */
    public static final String USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY =
            "com.irurueta.server.commons.useragent.USER_AGENT_CACHE_EXPIRATION_TIME_HOURS";
    
    /**
     * Default maximum number of time to keep user agents cached, which is 2 
     * hours.
     */
    public static final int DEFAULT_USER_AGENT_CACHE_EXPIRATION_TIME_HOURS = 2;
    
    /**
     * Reference to factory singleton (to keep it cached in memory unless memory
     * is claimed).
     */
    private static SoftReference<UserAgentConfigurationFactory> mReference = null;
    
    /**
     * Constructor.
     */
    private UserAgentConfigurationFactory() {}
    
    /**
     * Factory method to create or return singleton instance.
     * @return factory singleton.
     */
    public synchronized static UserAgentConfigurationFactory getInstance() {
        UserAgentConfigurationFactory factory;
        if (mReference == null || (factory = mReference.get()) == null) {
            factory = new UserAgentConfigurationFactory();
            mReference = new SoftReference<>(factory);
        }
        return factory;
    }
    
    /**
     * Configures the user agent service using provided properties.
     * @param properties properties containing user agent configuration
     * @return user agent configuration.
     * @throws ConfigurationException if any property value was invalid.
     */
    @Override
    public UserAgentConfiguration configure(Properties properties) 
            throws ConfigurationException {
        if(mConfiguration != null) return mConfiguration;
        
        if(properties == null){
            //use default configuration
            mConfiguration = new UserAgentConfigurationImpl();
        }else{
            mConfiguration = new UserAgentConfigurationImpl(properties);
        }
        
        //configure user agent detector
        UserAgentDetector.getInstance();
        
        return mConfiguration;
    }
    
    /**
     * Resets configuration by removing any existing configuration if already
     * defined.
     * @return returns this instance so that this method can be chained.
     * @throws ConfigurationException if something fails during configuration 
     * reset.
     */
    @Override
    public synchronized BaseConfigurationFactory<UserAgentConfiguration> reset()
            throws ConfigurationException {
        //reset user agent detector
        UserAgentDetector.reset();
        mConfiguration = null;
        return super.reset();
    }
    
}
