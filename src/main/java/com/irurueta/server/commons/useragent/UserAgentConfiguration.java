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

import com.irurueta.server.commons.configuration.Configuration;

/**
 * Interface defining parameters to configure user agent detection.
 */
public interface UserAgentConfiguration extends Configuration {
    /**
     * Indicates whether user agent detection is enabled or not.
     * @return true if user agent detection is enabled, false otherwise.
     */
    boolean isUserAgentDetectionEnabled();
    
    /**
     * Returns amount of user agents that are cached.
     * A cache of user agents is used to speed up the parsing process when
     * user agents get repeated, which can happen if a user makes several
     * requests to the server, or multiple users have the same user agent.
     * @return amount of user agents that are cached.
     */
    int getUserAgentCacheSize();
    
    /**
     * Returns maximum amount of time to keep user agents cached expressed
     * in hours.
     * @return maximum amount of time to keep user agents cached expressed
     * in hours.
     */
    int getUserAgentCacheExpirationTimeHours();
}
