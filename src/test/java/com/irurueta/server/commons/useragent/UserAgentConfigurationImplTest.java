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
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class UserAgentConfigurationImplTest {

    @Test
    public void testDefaultConstructorAndGetters() {
        final UserAgentConfiguration cfg = new UserAgentConfigurationImpl();

        assertEquals(cfg.isUserAgentDetectionEnabled(),
                UserAgentConfigurationFactory.
                        DEFAULT_USER_AGENT_DETECTION_ENABLED);
        assertEquals(cfg.getUserAgentCacheSize(),
                UserAgentConfigurationFactory.DEFAULT_USER_AGENT_CACHE_SIZE);
        assertEquals(cfg.getUserAgentCacheExpirationTimeHours(),
                UserAgentConfigurationFactory.
                        DEFAULT_USER_AGENT_CACHE_EXPIRATION_TIME_HOURS);
    }

    @Test
    public void testConstructorWithPropertiesAndGetters()
            throws ConfigurationException {
        // build properties
        Properties props = buildProperties();

        UserAgentConfiguration cfg = new UserAgentConfigurationImpl(props);
        assertFalse(cfg.isUserAgentDetectionEnabled());
        assertEquals(cfg.getUserAgentCacheSize(), 200);
        assertEquals(cfg.getUserAgentCacheExpirationTimeHours(), 4);

        // Force ConfigurationException (wrong cache size value)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, "wrong");
        cfg = null;
        try {
            cfg = new UserAgentConfigurationImpl(props);
            fail("ConfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }
        assertNull(cfg);

        // Force ConfigurationException (negative cache size)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, "-100");
        try {
            cfg = new UserAgentConfigurationImpl(props);
            fail("CenfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }
        assertNull(cfg);

        // Force ConfigurationException (wrong cache expiration time)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, "wrong");
        try {
            cfg = new UserAgentConfigurationImpl(props);
            fail("ConfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }
        assertNull(cfg);

        // Force ConfigurationException (negative cache expiration time)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, "-1");
        try {
            cfg = new UserAgentConfigurationImpl(props);
            fail("ConfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }
        assertNull(cfg);
    }

    @Test
    public void testFromProperties() throws ConfigurationException {
        Properties props = buildProperties();

        final UserAgentConfiguration cfg = new UserAgentConfigurationImpl();

        // load configuration from properties
        cfg.fromProperties(props);

        // check correctness
        assertFalse(cfg.isUserAgentDetectionEnabled());
        assertEquals(cfg.getUserAgentCacheSize(), 200);
        assertEquals(cfg.getUserAgentCacheExpirationTimeHours(), 4);

        // Force ConfigurationException (wrong cache size value)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, "wrong");
        try {
            cfg.fromProperties(props);
            fail("ConfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }

        // Force ConfigurationException (negative cache size)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, "-100");
        try {
            cfg.fromProperties(props);
            fail("CenfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }

        // Force ConfigurationException (wrong cache expiration time)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, "wrong");
        try {
            cfg.fromProperties(props);
            fail("ConfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }

        // Force ConfigurationException (negative cache expiration time)
        props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, "-1");
        try {
            cfg.fromProperties(props);
            fail("ConfigurationException expected but not thrown");
        } catch (final ConfigurationException ignore) {
        }

    }

    @Test
    public void testToProperties() throws ConfigurationException {
        final Properties props = buildProperties();
        final UserAgentConfiguration cfg = new UserAgentConfigurationImpl(props);

        // convert back to properties
        final Properties props2 = cfg.toProperties();

        assertEquals(props.getProperty(UserAgentConfigurationFactory.
                USER_AGENT_DETECTION_ENABLED_PROPERTY), props2.getProperty(
                UserAgentConfigurationFactory.
                        USER_AGENT_DETECTION_ENABLED_PROPERTY));
        assertEquals(props.getProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY), props2.getProperty(
                UserAgentConfigurationFactory.USER_AGENT_CACHE_SIZE_PROPERTY));
        assertEquals(props.getProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY), props2.
                getProperty(UserAgentConfigurationFactory.
                        USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY));
    }

    private Properties buildProperties() {
        final Properties props = new Properties();
        props.setProperty(UserAgentConfigurationFactory.
                        USER_AGENT_DETECTION_ENABLED_PROPERTY,
                Boolean.FALSE.toString());
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, "200");
        props.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY, "4");
        return props;
    }
}
