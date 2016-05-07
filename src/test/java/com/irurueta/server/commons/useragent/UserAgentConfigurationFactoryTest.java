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

import com.irurueta.server.commons.configuration.ConfigurationException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserAgentConfigurationFactoryTest {
    public static boolean USER_AGENT_DETECTION_ENABLED = true;
    public static int USER_AGENT_CACHE_SIZE = 200;
    public static int USER_AGENT_CACHE_EXPIRATION_TIME = 3;
    
    public UserAgentConfigurationFactoryTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    @Test
    public void testGetInstance(){
        UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();
        assertNotNull(factory);
    }
    
    @Test
    public void testConfigure() throws ConfigurationException{
        UserAgentConfigurationFactory factory = UserAgentConfigurationFactory.
                getInstance();
        
        //test configuration with properties
        Properties props = buildProperties();
        
        factory.reset();
        UserAgentConfiguration cfg1 = factory.configure(props);
        assertNotNull(cfg1);
        
        //test default configuration
        UserAgentConfiguration cfg2 = factory.configure();
        assertNotNull(cfg2);
        
        assertSame(cfg1, cfg2);
        
        UserAgentDetector.getInstance().close();
    }
    
    @Test
    public void testReset() throws ConfigurationException{
        UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();
        
        Properties props = buildProperties();
        
        UserAgentConfiguration cfg1 = factory.configure(props);
        UserAgentConfiguration cfg2 = factory.configure();
        
        assertSame(cfg1, cfg2);
        
        //test reset
        factory.reset();
        UserAgentConfiguration cfg3 = factory.configure(props);
        assertNotSame(cfg3, cfg1);
        
        UserAgentDetector.getInstance().close();
    }
    
    @Test
    public void testReconfigure() throws ConfigurationException{
        UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();
        
        Properties props = buildProperties();
        
        UserAgentConfiguration cfg1 = factory.configure(props);
        UserAgentConfiguration cfg2 = factory.reconfigure();
        
        assertNotSame(cfg1, cfg2);
        
        UserAgentConfiguration cfg3 = factory.reconfigure(props);
        
        assertNotSame(cfg2, cfg3);
        
        UserAgentDetector.getInstance().close();
    }
    
    @Test
    public void testGetConfiguration() throws ConfigurationException{
        UserAgentConfigurationFactory factory =
                UserAgentConfigurationFactory.getInstance();

        factory.reset();
        
        assertNull(factory.getConfiguration());
        
        Properties props = buildProperties();
        
        UserAgentConfiguration cfg1 = factory.configure(props);
        UserAgentConfiguration cfg2 = factory.getConfiguration();
        
        assertNotNull(cfg2);
        assertSame(cfg1, cfg2);
        
        UserAgentDetector.getInstance().close();        
    }
    
    public Properties buildProperties(){
        Properties properties = new Properties();
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
