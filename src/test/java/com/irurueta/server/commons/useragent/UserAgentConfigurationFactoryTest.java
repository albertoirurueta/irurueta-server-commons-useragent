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

public class UserAgentConfigurationFactoryTest {
    public static final boolean USER_AGENT_DETECTION_ENABLED = true;
    public static final int USER_AGENT_CACHE_SIZE = 200;
    public static final int USER_AGENT_CACHE_EXPIRATION_TIME = 3;

    @Test
    public void testGetInstance() {
        final UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();
        assertNotNull(factory);
    }

    @Test
    public void testConfigure() throws ConfigurationException {
        final UserAgentConfigurationFactory factory = UserAgentConfigurationFactory.
                getInstance();

        // test configuration with properties
        final Properties props = buildProperties();

        factory.reset();
        final UserAgentConfiguration cfg1 = factory.configure(props);
        assertNotNull(cfg1);

        // test default configuration
        final UserAgentConfiguration cfg2 = factory.configure();
        assertNotNull(cfg2);

        assertSame(cfg1, cfg2);

        UserAgentDetector.getInstance().close();
    }

    @Test
    public void testReset() throws ConfigurationException {
        final UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();

        final Properties props = buildProperties();

        final UserAgentConfiguration cfg1 = factory.configure(props);
        final UserAgentConfiguration cfg2 = factory.configure();

        assertSame(cfg1, cfg2);

        // test reset
        factory.reset();
        final UserAgentConfiguration cfg3 = factory.configure(props);
        assertNotSame(cfg3, cfg1);

        UserAgentDetector.getInstance().close();
    }

    @Test
    public void testReconfigure() throws ConfigurationException {
        final UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();

        final Properties props = buildProperties();

        final UserAgentConfiguration cfg1 = factory.configure(props);
        final UserAgentConfiguration cfg2 = factory.reconfigure();

        assertNotSame(cfg1, cfg2);

        final UserAgentConfiguration cfg3 = factory.reconfigure(props);

        assertNotSame(cfg2, cfg3);

        UserAgentDetector.getInstance().close();
    }

    @Test
    public void testGetConfiguration() throws ConfigurationException {
        final UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();

        factory.reset();

        assertNull(factory.getConfiguration());

        final Properties props = buildProperties();

        final UserAgentConfiguration cfg1 = factory.configure(props);
        final UserAgentConfiguration cfg2 = factory.getConfiguration();

        assertNotNull(cfg2);
        assertSame(cfg1, cfg2);

        UserAgentDetector.getInstance().close();
    }

    public Properties buildProperties() {
        final Properties properties = new Properties();
        properties.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_DETECTION_ENABLED_PROPERTY, Boolean.toString(
                USER_AGENT_DETECTION_ENABLED));
        properties.setProperty(UserAgentConfigurationFactory.
                USER_AGENT_CACHE_SIZE_PROPERTY, Integer.toString(
                USER_AGENT_CACHE_SIZE));
        properties.setProperty(UserAgentConfigurationFactory.
                        USER_AGENT_CACHE_EXPIRATION_TIME_HOURS_PROPERTY,
                Integer.toString(USER_AGENT_CACHE_EXPIRATION_TIME));
        return properties;
    }
}
