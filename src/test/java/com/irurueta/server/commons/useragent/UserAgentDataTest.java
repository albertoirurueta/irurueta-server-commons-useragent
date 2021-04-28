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

import org.junit.Test;

import static org.junit.Assert.*;

public class UserAgentDataTest {

    @Test
    public void testConstructor() {
        final UserAgentData uaData = new UserAgentData("user-agent-string",
                DeviceCategory.GAME_CONSOLE, "categoryName", "family",
                OperatingSystemFamily.AIX, "osFamilyName", "osName",
                "osProducer", "osVersion", UserAgentType.BROWSER, "uaVersion");

        // check correctness
        assertEquals(uaData.getUserAgent(), "user-agent-string");
        assertEquals(uaData.getDeviceCategory(), DeviceCategory.GAME_CONSOLE);
        assertEquals(uaData.getDeviceCategoryName(), "categoryName");
        assertEquals(uaData.getFamily(), "family");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.AIX);
        assertEquals(uaData.getOsFamilyName(), "osFamilyName");
        assertEquals(uaData.getOsName(), "osName");
        assertEquals(uaData.getOsProducer(), "osProducer");
        assertEquals(uaData.getOsVersion(), "osVersion");
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "uaVersion");
    }

    @Test
    public void testGetSetUserAgent() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getUserAgent());

        // set new value
        uaData.setUserAgent("user-agent-string");

        // check correctness
        assertEquals(uaData.getUserAgent(), "user-agent-string");
    }

    @Test
    public void testGetSetDeviceCategory() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getDeviceCategory());

        // set new value
        uaData.setDeviceCategory(DeviceCategory.GAME_CONSOLE);

        // check correctness
        assertEquals(uaData.getDeviceCategory(), DeviceCategory.GAME_CONSOLE);
    }

    @Test
    public void testGetSetDeviceCategoryName() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getDeviceCategoryName());

        // set new value
        uaData.setDeviceCategoryName("categoryName");

        // check correctness
        assertEquals(uaData.getDeviceCategoryName(), "categoryName");
    }

    @Test
    public void testGetSetFamily() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getFamily());

        // set new value
        uaData.setFamily("family");

        // check correctness
        assertEquals(uaData.getFamily(), "family");
    }

    @Test
    public void testGetSetOsFamily() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getOsFamily());

        // set new value
        uaData.setOsFamily(OperatingSystemFamily.ANDROID);

        // check correctness
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.ANDROID);
    }

    @Test
    public void testGetSetOsFamilyName() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getOsFamilyName());

        // set new value
        uaData.setOsFamilyName("familyName");

        // check correctness
        assertEquals(uaData.getOsFamilyName(), "familyName");
    }

    @Test
    public void testGetSetOsName() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getOsName());

        // set new value
        uaData.setOsName("osName");

        // check correctness
        assertEquals(uaData.getOsName(), "osName");
    }

    @Test
    public void testGetSetOsProducer() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getOsProducer());

        // set new value
        uaData.setOsProducer("osProducer");

        // check correctness
        assertEquals(uaData.getOsProducer(), "osProducer");
    }

    @Test
    public void testGetSetOsVersion() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getOsVersion());

        // set new value
        uaData.setOsVersion("osVersion");

        // check correctness
        assertEquals(uaData.getOsVersion(), "osVersion");
    }

    @Test
    public void testGetSetUserAgentType() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getUserAgentType());

        // set new value
        uaData.setUserAgentType(UserAgentType.EMAIL_CLIENT);

        // check correctness
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
    }

    @Test
    public void testGetSetUserAgentVersion() {
        final UserAgentData uaData = new UserAgentData(null, null, null,
                null, null, null, null, null, null,
                null, null);

        // check default value
        assertNull(uaData.getUserAgentVersion());

        // set new value
        uaData.setUserAgentVersion("uaVersion");

        // check correctness
        assertEquals(uaData.getUserAgentVersion(), "uaVersion");
    }

    @Test
    public void testIsDesktop() {
        assertTrue(UserAgentData.isDesktop(UserAgentType.BROWSER,
                DeviceCategory.OTHER));
        assertTrue(UserAgentData.isDesktop(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.OTHER));
        assertTrue(UserAgentData.isDesktop(UserAgentType.BROWSER,
                DeviceCategory.PERSONAL_COMPUTER));
        assertTrue(UserAgentData.isDesktop(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.PERSONAL_COMPUTER));
        assertTrue(UserAgentData.isDesktop(UserAgentType.BROWSER,
                DeviceCategory.UNKNOWN));
        assertTrue(UserAgentData.isDesktop(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.UNKNOWN));

        assertFalse(UserAgentData.isDesktop(UserAgentType.BROWSER,
                DeviceCategory.SMARTPHONE));
        assertFalse(UserAgentData.isDesktop(UserAgentType.MOBILE_BROWSER,
                DeviceCategory.SMARTPHONE));

        UserAgentData uaData = new UserAgentData(null,
                DeviceCategory.PERSONAL_COMPUTER, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertTrue(uaData.isDesktop());

        uaData = new UserAgentData(null, DeviceCategory.SMARTPHONE, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertFalse(uaData.isDesktop());
    }

    @Test
    public void testIsMobile() {
        assertTrue(UserAgentData.isMobile(UserAgentType.BROWSER,
                DeviceCategory.PDA));
        assertTrue(UserAgentData.isMobile(UserAgentType.BROWSER,
                DeviceCategory.SMARTPHONE));
        assertTrue(UserAgentData.isMobile(UserAgentType.BROWSER,
                DeviceCategory.WEARABLE_COMPUTER));
        assertTrue(UserAgentData.isMobile(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.PDA));
        assertTrue(UserAgentData.isMobile(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.SMARTPHONE));
        assertTrue(UserAgentData.isMobile(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.WEARABLE_COMPUTER));
        assertTrue(UserAgentData.isMobile(UserAgentType.MOBILE_BROWSER,
                DeviceCategory.PDA));
        assertTrue(UserAgentData.isMobile(UserAgentType.MOBILE_BROWSER,
                DeviceCategory.SMARTPHONE));
        assertTrue(UserAgentData.isMobile(UserAgentType.MOBILE_BROWSER,
                DeviceCategory.WEARABLE_COMPUTER));

        assertFalse(UserAgentData.isMobile(UserAgentType.BROWSER,
                DeviceCategory.PERSONAL_COMPUTER));

        UserAgentData uaData = new UserAgentData(null,
                DeviceCategory.SMARTPHONE, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertTrue(uaData.isMobile());

        uaData = new UserAgentData(null,
                DeviceCategory.PERSONAL_COMPUTER, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertFalse(uaData.isMobile());
    }

    @Test
    public void testIsTablet() {
        assertTrue(UserAgentData.isTablet(UserAgentType.BROWSER,
                DeviceCategory.TABLET));
        assertTrue(UserAgentData.isTablet(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.TABLET));
        assertTrue(UserAgentData.isTablet(UserAgentType.MOBILE_BROWSER,
                DeviceCategory.TABLET));

        assertFalse(UserAgentData.isTablet(UserAgentType.BROWSER,
                DeviceCategory.PERSONAL_COMPUTER));

        UserAgentData uaData = new UserAgentData(null,
                DeviceCategory.TABLET, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertTrue(uaData.isTablet());

        uaData = new UserAgentData(null,
                DeviceCategory.PERSONAL_COMPUTER, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertFalse(uaData.isTablet());
    }

    @Test
    public void testIsSmartTV() {
        assertTrue(UserAgentData.isSmartTV(UserAgentType.BROWSER,
                DeviceCategory.SMART_TV));
        assertTrue(UserAgentData.isSmartTV(UserAgentType.EMAIL_CLIENT,
                DeviceCategory.SMART_TV));
        assertTrue(UserAgentData.isSmartTV(UserAgentType.MOBILE_BROWSER,
                DeviceCategory.SMART_TV));

        assertFalse(UserAgentData.isSmartTV(UserAgentType.BROWSER,
                DeviceCategory.PERSONAL_COMPUTER));

        UserAgentData uaData = new UserAgentData(null,
                DeviceCategory.SMART_TV, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertTrue(uaData.isSmartTV());

        uaData = new UserAgentData(null,
                DeviceCategory.PERSONAL_COMPUTER, null, null,
                OperatingSystemFamily.ANDROID, null, null, null, null,
                UserAgentType.BROWSER, null);

        assertFalse(uaData.isSmartTV());
    }
}
