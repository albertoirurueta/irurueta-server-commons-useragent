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
import net.sf.uadetector.ReadableDeviceCategory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserAgentDetectorTest {
    String[] ie6clients = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; T312461)",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; XMPP Tiscali Communicator v.10.0.2; .NET CLR 2.0.50727)",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"
    };

    String[] ie7clients = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)",
                    "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0 ; .NET CLR 2.0.50215; SL Commerce Client v1.0; Tablet PC 2.0",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506)" // Windows Mail on Vista
    };

    String[] ie8clients = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; WOW64; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; Media Center PC 5.0; .NET CLR 1.1.4322)",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Win64; x64; .NET CLR 2.0.50727; SLCC1; Media Center PC 5.0; .NET CLR 3.0.04506)"
    };

    String[] ie9clients = new String[] {
                    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Zune 4.0; InfoPath.3; MS-RTC LM 8; .NET4.0C; .NET4.0E)",
                    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0"
    };


    String[] ie55clients = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; .NET CLR 1.1.4322)",
                    "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)",
                    "Mozilla/4.0 (compatible; MSIE 5.5; Windows 95)"
    };

    String[] ieTooOld = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 4.01; Windows 95)",
                    "Mozilla/4.0 (compatible; MSIE 4.0; Windows 95; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
                    "Mozilla/2.0 (compatible; MSIE 3.03; Windows 3.1)"
    };

    String[] outlook2007 = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; .NET CLR 1.1.4322; MSOffice 12)"
    };

    String[] outlook2010 = new String[] {
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; GTB6.4; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 1.1.4322; .NET CLR 3.5.30729; .NET CLR 3.0.30729; OfficeLiveConnector.1.3; OfficeLivePatch.0.0; MSOffice 14)",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; Media Center PC 6.0; SLCC2; ms-office; MSOffice 14)"
    };


    String[] outookExpress = new String[] {
                    "Outlook-Express/7.0 (MSIE 6.0; Windows NT 5.1; SV1; SIMBAR={xxx}; .NET CLR 2.0.50727; .NET CLR 1.1.4322; TmstmpExt)",
                    "Outlook-Express/7.0 (MSIE 7.0; Windows NT 5.1; InfoPath.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; TmstmpExt)"
    };

    String[] ieMobile6 = new String[] {
                    "HTC_TyTN Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 6.12)",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 6.12) Vodafone/1.0/HTC_s710/1.22.172.3",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 6.8) PPC; 240x320; HTC_TyTN/1.0 Profile/MIDP-2.0 Configuration/CLDC-1.1"
    };

    String[] ieMobile7 = new String[] {
             "HTC_TouchDual Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6)",
             "PPC; 240x320; HTC_P3450/1.0 Profile/MIDP-2.0 Configuration/CLDC-1.1 Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6)",
         "Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6) PPC; MDA Vario/3.0 Profile/MIDP-2.0 Configuration/CLDC-1.1",
         "Palm750/v0005 Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.6) UP.Link/6.3.0.0.0"
    };

    String[] lotusNotes = new String[] {
            "Mozilla/4.0 (compatible; Lotus-Notes/5.0; Windows-NT)",
            "Mozilla/4.0 (compatible; Lotus-Notes/6.0; Windows-NT)"
    };

    String[] lynxClient = new String[] {
                    "Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 OpenSSL/0.9.7d",
                    "Lynx/2.7.1ac-0.102+intl+csuite libwww-FM/2.14"
    };

    String[] konqueror = new String[] {
                    "Mozilla/5.0 (compatible; konqueror/3.3; linux 2.4.21-243-smp4G) (KHTML, like Geko)",
                    "Mozilla/6.0 (compatible; Konqueror/4.2; i686 FreeBSD 6.4; 20060308)",
                    "Mozilla/5.0 (compatible; Konqueror/3.1; Linux 2.4.21-20.0.1.ELsmp; X11; i686; , en_US, en, de)"
    };

    String[] chrome = new String[] {
                    "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/532.9 (KHTML, like Gecko) Chrome/5.0.310.0 Safari/532.9",
                    "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/532.9 (KHTML, like Gecko) Chrome/5.0.309.0 Safari/532.9"
    };

    String[] chrome8 = new String[] {
                    "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.558.0 Safari/534.10",
                    "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML, like Gecko) Ubuntu/10.10 Chrome/8.1.0.0 Safari/540.0"
    };

    String[] chrome9 = new String[] {
                    "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML,like Gecko) Chrome/9.1.0.0 Safari/540.0",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.14 (KHTML, like Gecko) Chrome/9.0.600.0 Safari/534.14"
    };

    String[] chrome10 = new String[] {
                    "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.15 (KHTML, like Gecko) Ubuntu/10.10 Chromium/10.0.613.0 Chrome/10.0.613.0 Safari/534.15"
    };

    String[] firefox3 = new String[] {
                    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.14) Gecko/2009090216 Ubuntu/9.04 (jaunty) Firefox/3.0.14"
    };

    String[] firefox4 = new String[] {
                    "Mozilla/5.0 (X11; Linux x86_64; rv:2.0b4) Gecko/20100818 Firefox/4.0b4",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b9pre) Gecko/20101228 Firefox/4.0b9pre"
    };

    String[] firefox3mobile = {
                    "Mozilla/5.0 (X11; U; Linux armv7l; en-US; rv:1.9.2a1pre) Gecko/20091127 Firefox/3.5 Maemo Browser 1.5.6 RX-51 N900"
    };

    String[] safari = {
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/525.28.3 (KHTML, like Gecko) Version/3.2.3 Safari/525.28.3",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-gb) AppleWebKit/523.10.6 (KHTML, like Gecko) Version/3.0.4 Safari/523.10.6"
    };

    String[] safari5 = {
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; en-us) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; ja-JP) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_5; en-us) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4"
    };

    String[] safari4 = {
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_2; en-us) AppleWebKit/531.21.8 (KHTML, like Gecko) Version/4.0.4 Safari/531.21.10",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; es-ES) AppleWebKit/531.22.7 (KHTML, like Gecko) Version/4.0.5 Safari/531.22.7",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/4.0.1 Safari/530.18"
    };

    String[] mobileSafari = {                       
                    "Mozilla/5.0 (Linux; U; Android 2.1; en-us; Nexus One Build/ERD62) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17",
                    "Mozilla/5.0 (iPod; U; CPU iPhone OS 2_0 like Mac OS X; de-de) AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5A347 Safari/525.20", // Mobile Safari 3.1.1
                    "Mozilla/5.0 (iPod; U; CPU like Mac OS X; en) AppleWebKit/420.1 (KHTML, like Gecko) Version/3.0 Mobile/3A101a Safari/419.3", // Mobile Safari 3.0
                    "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B367 Safari/531.21.10",
                    "Mozilla/5.0 (iPod; U; CPU iPhone OS 4_1 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8B117 Safari/6531.22.7"
    };

    // similar to Safari, but doesn't mention Safari in the user-agent string
    String[] appleMail = {
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; en-us) AppleWebKit/533.18.1 (KHTML, like Gecko)"
    };

    String[] omniWeb = {
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/531.9+(KHTML, like Gecko, Safari/528.16) OmniWeb/v622.10.0",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US) AppleWebKit/525.18 (KHTML, like Gecko, Safari/525.20) OmniWeb/v622.3.0.105198"
    };

    String[] opera = {
                    "Opera/8.0 (Macintosh; PPC Mac OS X; U; en)",
            };

    String[] opera9 = {
                    "Opera/9.52 (Windows NT 5.1; U; en)",
                    "Opera/9.20 (Macintosh; Intel Mac OS X; U; en)"
            };

    String[] opera10 = {
                    "Opera/9.80 (Windows NT 5.2; U; en) Presto/2.2.15 Version/10.10",
                    "Opera/9.80 (Macintosh; Intel Mac OS X; U; en) Presto/2.6.30 Version/10.61"
            };

    String[] operaMini = {
            "Opera/9.60 (J2ME/MIDP; Opera Mini/4.2.13337/458; U; en) Presto/2.2.0",
            "Opera/9.80 (J2ME/MIDP; Opera Mini/5.0.16823/1428; U; en) Presto/2.2.0"
    };

    String[] camino2 = {
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en; rv:1.9.0.19) Gecko/2010111021 Camino/2.0.6 (MultiLang) (like Firefox/3.0.19)",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en; rv:1.9.0.18) Gecko/2010021619 Camino/2.0.2 (like Firefox/3.0.18)"
    };

    String[] camino = {
                    "Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; it; rv:1.8.1.21) Gecko/20090327 Camino/1.6.7 (MultiLang) (like Firefox/2.0.0.21pre)",
                    "Mozilla/5.0 (Macintosh; U; PPC Mac OS X Mach-O; en-US; rv:1.8.0.4) Gecko/20060613 Camino/1.0.2"
    };

    String[] flock = {
                    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008100716 Firefox/3.0.3 Flock/2.0"
    };

    String[] seaMonkey = {
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1.13) Gecko/20100914 Mnenhy/0.8.3 SeaMonkey/2.0.8"
    };

    String[] bots = {
                    "Mozilla/5.0 (compatible; Googlebot/2.1; http://www.google.com/bot.html)",
                    "Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",
                    "Googlebot-Image/1.0"
    };

    String[] tools = {
                    "curl/7.19.5 (i586-pc-mingw32msvc) libcurl/7.19.5 OpenSSL/0.9.8l zlib/1.2.3",
                    "Wget/1.8.1"
    };

    String[] thunderbird3 = {
                    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.2.12) Gecko/20101027 Thunderbird/3.1.6",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; sv-SE; rv:1.9.2.8) Gecko/20100802 Thunderbird/3.1.2 ThunderBrowse/3.3.2"
    };

    String[] thunderbird2 = {
                    "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-GB; rv:1.8.1.14) Gecko/20080421 Thunderbird/2.0.0.14",
                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; en-US; rv:1.8.1.17) Gecko/20080914 Thunderbird/2.0.0.17"
    };
    
    String[] bb10 = {
        "Mozilla/5.0 (BB10; Kbd) AppleWebKit/537.10+ (KHTML, like Gecko) Version/10.1.0.4633 Mobile Safari/537.10+"
    };
    
    String[] windowsPhone = {
        "Mozilla/5.0 (compatible; MSIE 10.0; Windows Phone 8.0; Trident/6.0; IEMobile/10.0; ARM; Touch; NOKIA; Lumia 920)"
    };
    
    String[] surface = {
        "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; ARM; Trident/6.0; Touch)",
        "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko"        
    };
        
    public UserAgentDetectorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {
        UserAgentDetector.reset();
    }

    @Test
    public void testGetInstanceCloseAndReset(){        
        UserAgentDetector detector1 = UserAgentDetector.getInstance();
        UserAgentDetector detector2 = UserAgentDetector.getInstance();
        
        assertSame(detector1, detector2);
        
        assertTrue(detector1.isEnabled());
        
        detector1.close();
        assertFalse(detector1.isEnabled());
        
        UserAgentDetector detector3 = UserAgentDetector.getInstance();
        
        assertSame(detector2, detector3);
        
        UserAgentDetector.reset();
        
        UserAgentDetector detector4 = UserAgentDetector.getInstance();
        
        assertNotSame(detector3, detector4);
    }
    
    @Test
    public void testIsEnabledGetCacheSizeAndCacheExpirationTime() 
            throws ConfigurationException{
        UserAgentConfiguration cfg = UserAgentConfigurationFactory.
                getInstance().configure();
        
        UserAgentDetector detector = UserAgentDetector.getInstance();
        
        assertTrue(detector.isEnabled());
        assertEquals(detector.getCacheSize(), cfg.getUserAgentCacheSize());
        assertEquals(detector.getCacheExpirationTime(), 
                cfg.getUserAgentCacheExpirationTimeHours());        
    }
    
    @Test
    public void testToDeviceCategory(){
        UserAgentDetector detector = UserAgentDetector.getInstance();
        
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.GAME_CONSOLE), 
                DeviceCategory.GAME_CONSOLE);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.OTHER), 
                DeviceCategory.OTHER);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.PDA), 
                DeviceCategory.PDA);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.PERSONAL_COMPUTER), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.SMARTPHONE), 
                DeviceCategory.SMARTPHONE);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.SMART_TV), 
                DeviceCategory.SMART_TV);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.TABLET), 
                DeviceCategory.TABLET);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.UNKNOWN), 
                DeviceCategory.UNKNOWN);
        assertEquals(detector.toDeviceCategory(
                ReadableDeviceCategory.Category.WEARABLE_COMPUTER), 
                DeviceCategory.WEARABLE_COMPUTER);        
    }
    
    @Test
    public void testToOsFamily(){
        UserAgentDetector detector = UserAgentDetector.getInstance();
        
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.AIX), 
                OperatingSystemFamily.AIX);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.AMIGA_OS), 
                OperatingSystemFamily.AMIGA_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.ANDROID), 
                OperatingSystemFamily.ANDROID);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.AROS), 
                OperatingSystemFamily.AROS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.BADA), 
                OperatingSystemFamily.BADA);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.BEOS), 
                OperatingSystemFamily.BEOS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.BLACKBERRY_OS), 
                OperatingSystemFamily.BLACKBERRY_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.BREW), 
                OperatingSystemFamily.BREW);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.BSD), 
                OperatingSystemFamily.BSD);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.DANGEROS), 
                OperatingSystemFamily.DANGEROS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.FIREFOX_OS), 
                OperatingSystemFamily.FIREFOX_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.HAIKU), 
                OperatingSystemFamily.HAIKU);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.HPUX), 
                OperatingSystemFamily.HPUX);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.INFERNO_OS), 
                OperatingSystemFamily.INFERNO_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.IOS), 
                OperatingSystemFamily.IOS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.IRIX), 
                OperatingSystemFamily.IRIX);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.JVM), 
                OperatingSystemFamily.JVM);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.LINUX), 
                OperatingSystemFamily.LINUX);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.MAC_OS), 
                OperatingSystemFamily.MAC_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.MEEGO), 
                OperatingSystemFamily.MEEGO);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.MINIX), 
                OperatingSystemFamily.MINIX);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.MORPHOS), 
                OperatingSystemFamily.MORPHOS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.NINTENDO), 
                OperatingSystemFamily.NINTENDO);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.OPENVMS), 
                OperatingSystemFamily.OPENVMS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.OS_2), 
                OperatingSystemFamily.OS_2);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.OS_X), 
                OperatingSystemFamily.OS_X);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.PALM_OS), 
                OperatingSystemFamily.PALM_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.PLAYSTATION_VITA), 
                OperatingSystemFamily.PLAYSTATION_VITA);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.QNX), 
                OperatingSystemFamily.QNX);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.RISC_OS), 
                OperatingSystemFamily.RISC_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.SAILFISH_OS), 
                OperatingSystemFamily.SAILFISH_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.SOLARIS), 
                OperatingSystemFamily.SOLARIS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.SYLLABLE), 
                OperatingSystemFamily.SYLLABLE);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.SYMBIAN), 
                OperatingSystemFamily.SYMBIAN);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.TIZEN), 
                OperatingSystemFamily.TIZEN);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.UNKNOWN), 
                OperatingSystemFamily.UNKNOWN);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.WEBOS), 
                OperatingSystemFamily.WEBOS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.WII_OS), 
                OperatingSystemFamily.WII_OS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.WINDOWS), 
                OperatingSystemFamily.WINDOWS);
        assertEquals(detector.toOsFamily(
                net.sf.uadetector.OperatingSystemFamily.XROSSMEDIABAR), 
                OperatingSystemFamily.XROSSMEDIABAR);
    }
    
    @Test
    public void testToUserAgentType(){
        UserAgentDetector detector = UserAgentDetector.getInstance();

        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.BROWSER), 
                UserAgentType.BROWSER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.EMAIL_CLIENT), 
                UserAgentType.EMAIL_CLIENT);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.FEED_READER), 
                UserAgentType.FEED_READER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.LIBRARY), 
                UserAgentType.LIBRARY);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.MEDIAPLAYER), 
                UserAgentType.MEDIAPLAYER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.MOBILE_BROWSER), 
                UserAgentType.MOBILE_BROWSER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.OFFLINE_BROWSER), 
                UserAgentType.OFFLINE_BROWSER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.OTHER), 
                UserAgentType.OTHER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.ROBOT), 
                UserAgentType.ROBOT);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.UNKNOWN), 
                UserAgentType.UNKNOWN);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.USERAGENT_ANONYMIZER), 
                UserAgentType.USERAGENT_ANONYMIZER);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.VALIDATOR), 
                UserAgentType.VALIDATOR);
        assertEquals(detector.toUserAgentType(
                net.sf.uadetector.UserAgentType.WAP_BROWSER), 
                UserAgentType.WAP_BROWSER);        
    }
    
    @Test
    public void testDetect() throws UserAgentException{
        UserAgentDetector detector = UserAgentDetector.getInstance();
        
        //IE 6
        UserAgentData uaData = detector.detect(ie6clients[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2000");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.0");
        assertEquals(uaData.getUserAgent(), ie6clients[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "6.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie6clients[1]);

        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows XP");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.1");
        assertEquals(uaData.getUserAgent(), ie6clients[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "6.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());        
        
        uaData = detector.detect(ie6clients[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2003 Server");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.2");
        assertEquals(uaData.getUserAgent(), ie6clients[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "6.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie7clients[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows XP");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.1");
        assertEquals(uaData.getUserAgent(), ie7clients[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie7clients[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.TABLET);
        assertEquals(uaData.getDeviceCategoryName(), "Tablet");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Vista");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.0");
        assertEquals(uaData.getUserAgent(), ie7clients[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.0b");
        assertFalse(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertTrue(uaData.isTablet());
        assertFalse(uaData.isSmartTV());        
        
        uaData = detector.detect(ie7clients[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows XP");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.1");
        assertEquals(uaData.getUserAgent(), ie7clients[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie7clients[3]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Vista");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.0");
        assertEquals(uaData.getUserAgent(), ie7clients[3]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie8clients[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Vista");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.0");
        assertEquals(uaData.getUserAgent(), ie8clients[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "8.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie8clients[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Vista");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.0");
        assertEquals(uaData.getUserAgent(), ie8clients[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "8.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie9clients[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 7");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.1");
        assertEquals(uaData.getUserAgent(), ie9clients[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "9.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(ie9clients[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 7");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.1");
        assertEquals(uaData.getUserAgent(), ie9clients[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "9.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(ie55clients[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2000");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.0");
        assertEquals(uaData.getUserAgent(), ie55clients[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.5");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie55clients[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2000");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.0");
        assertEquals(uaData.getUserAgent(), ie55clients[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.5");        
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ie55clients[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 95");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "95");
        assertEquals(uaData.getUserAgent(), ie55clients[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.5");        
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ieTooOld[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 95");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "95");
        assertEquals(uaData.getUserAgent(), ieTooOld[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.01"); 
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ieTooOld[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 95");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "95");
        assertEquals(uaData.getUserAgent(), ieTooOld[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0");   
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ieTooOld[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 3.x");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "3.1");
        assertEquals(uaData.getUserAgent(), ieTooOld[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.03");  
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(outlook2007[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Outlook 2007");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Vista");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.0");
        assertEquals(uaData.getUserAgent(), outlook2007[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "");   
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ieMobile6[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows CE");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile6[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "6.12");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ieMobile6[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows CE");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile6[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "6.12");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(ieMobile6[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows CE");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile6[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "6.8");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(ieMobile7[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows CE");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile7[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.6");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(ieMobile7[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows CE");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile7[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.6");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(ieMobile7[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows CE");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile7[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.6");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(ieMobile7[3]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.PALM_OS);
        assertEquals(uaData.getOsFamilyName(), "Palm OS");
        assertEquals(uaData.getOsName(), "Palm OS");
        assertEquals(uaData.getOsProducer(), "Palm, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), ieMobile7[3]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "7.6");         
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(lotusNotes[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Lotus Notes");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows NT");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), lotusNotes[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "5.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(lotusNotes[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Lotus Notes");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows NT");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), lotusNotes[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "6.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(lynxClient[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Lynx");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), lynxClient[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.8.5rel.1");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(lynxClient[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Lynx");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), lynxClient[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.8.5rel.1");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(lynxClient[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Lynx");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), lynxClient[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.7.1ac");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(konqueror[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Konqueror");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), konqueror[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.3");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(konqueror[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Konqueror");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.BSD);
        assertEquals(uaData.getOsFamilyName(), "BSD");
        assertEquals(uaData.getOsName(), "FreeBSD");
        assertEquals(uaData.getOsProducer(), "FreeBSD Foundation");
        assertEquals(uaData.getOsVersion(), "6.4");
        assertEquals(uaData.getUserAgent(), konqueror[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.2");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(konqueror[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Konqueror");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), konqueror[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.1");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(chrome[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chrome");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2003 Server");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.2");
        assertEquals(uaData.getUserAgent(), chrome[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.0.310.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(chrome[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chrome");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), chrome[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.0.309.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(chrome8[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chrome");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2003 Server");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.2");
        assertEquals(uaData.getUserAgent(), chrome8[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "8.0.558.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(chrome8[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chrome");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux (Ubuntu)");
        assertEquals(uaData.getOsProducer(), "Canonical Ltd.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), chrome8[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "8.1.0.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(chrome9[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chrome");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), chrome9[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "9.1.0.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(chrome9[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chrome");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows XP");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.1");
        assertEquals(uaData.getUserAgent(), chrome9[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "9.0.600.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(chrome10[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Chromium");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux (Ubuntu)");
        assertEquals(uaData.getOsProducer(), "Canonical Ltd.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), chrome10[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "10.0.613.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(firefox3[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Firefox");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux (Ubuntu)");
        assertEquals(uaData.getOsProducer(), "Canonical Ltd.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), firefox3[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.0.14");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(firefox4[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Firefox");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), firefox4[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0b4");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(firefox4[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Firefox");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 7");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.1");
        assertEquals(uaData.getUserAgent(), firefox4[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0b9pre");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(firefox3mobile[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "MicroB");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux (Maemo)");
        assertEquals(uaData.getOsProducer(), "Nokia");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), firefox3mobile[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "1.5.6");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.5 Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.5.7");
        assertEquals(uaData.getUserAgent(), safari[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.2.3");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), safari[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.0.4");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari5[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.6 Snow Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.6.4");
        assertEquals(uaData.getUserAgent(), safari5[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari5[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 7");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.1");
        assertEquals(uaData.getUserAgent(), safari5[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari5[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.6 Snow Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.6.5");
        assertEquals(uaData.getUserAgent(), safari5[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.0.3");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari4[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.6 Snow Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.6.2");
        assertEquals(uaData.getUserAgent(), safari4[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0.4");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(safari4[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 7");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.1");
        assertEquals(uaData.getUserAgent(), safari4[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0.5");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(safari4[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.5 Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.5.7");
        assertEquals(uaData.getUserAgent(), safari4[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0.1");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(mobileSafari[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "Android Browser");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.ANDROID);
        assertEquals(uaData.getOsFamilyName(), "Android");
        assertEquals(uaData.getOsName(), "Android 2.0/1 Eclair");
        assertEquals(uaData.getOsProducer(), "Google, Inc.");
        assertEquals(uaData.getOsVersion(), "2.1");
        assertEquals(uaData.getUserAgent(), mobileSafari[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(mobileSafari[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "Mobile Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.IOS);
        assertEquals(uaData.getOsFamilyName(), "iOS");
        assertEquals(uaData.getOsName(), "iOS");
        assertEquals(uaData.getOsProducer(), "Apple Inc.");
        assertEquals(uaData.getOsVersion(), "2.0");
        assertEquals(uaData.getUserAgent(), mobileSafari[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.1.1");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(mobileSafari[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "Mobile Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), mobileSafari[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "3.0");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(mobileSafari[3]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.TABLET);
        assertEquals(uaData.getDeviceCategoryName(), "Tablet");
        assertEquals(uaData.getFamily(), "Mobile Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.IOS);
        assertEquals(uaData.getOsFamilyName(), "iOS");
        assertEquals(uaData.getOsName(), "iOS");
        assertEquals(uaData.getOsProducer(), "Apple Inc.");
        assertEquals(uaData.getOsVersion(), "3.2");
        assertEquals(uaData.getUserAgent(), mobileSafari[3]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0.4");
        assertFalse(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertTrue(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(mobileSafari[4]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "Mobile Safari");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.IOS);
        assertEquals(uaData.getOsFamilyName(), "iOS");
        assertEquals(uaData.getOsName(), "iOS 4");
        assertEquals(uaData.getOsProducer(), "Apple Inc.");
        assertEquals(uaData.getOsVersion(), "4.1");
        assertEquals(uaData.getUserAgent(), mobileSafari[4]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.0.5");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(appleMail[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Apple Mail");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.6 Snow Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.6.4");
        assertEquals(uaData.getUserAgent(), appleMail[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(omniWeb[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "OmniWeb");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.MAC_OS);
        assertEquals(uaData.getOsFamilyName(), "Mac OS");
        assertEquals(uaData.getOsName(), "Mac OS");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), omniWeb[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(omniWeb[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "OmniWeb");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.MAC_OS);
        assertEquals(uaData.getOsFamilyName(), "Mac OS");
        assertEquals(uaData.getOsName(), "Mac OS");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), omniWeb[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(opera[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Opera");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), opera[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "8.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(opera9[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Opera");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows XP");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.1");
        assertEquals(uaData.getUserAgent(), opera9[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "9.52");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(opera9[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Opera");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), opera9[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "9.20");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(opera10[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Opera");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 2003 Server");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.2");
        assertEquals(uaData.getUserAgent(), opera10[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "10.10");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(opera10[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Opera");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), opera10[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "10.61");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(operaMini[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "Opera Mini");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.JVM);
        assertEquals(uaData.getOsFamilyName(), "JVM");
        assertEquals(uaData.getOsName(), "JVM (Platform Micro Edition)");
        assertEquals(uaData.getOsProducer(), "Sun Microsystems, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), operaMini[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "4.2.13337");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(operaMini[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "Opera Mini");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.JVM);
        assertEquals(uaData.getOsFamilyName(), "JVM");
        assertEquals(uaData.getOsName(), "JVM (Platform Micro Edition)");
        assertEquals(uaData.getOsProducer(), "Sun Microsystems, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), operaMini[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "5.0.16823");
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(camino2[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Camino");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.6 Snow Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.6");
        assertEquals(uaData.getUserAgent(), camino2[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.0.6");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(camino2[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Camino");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X 10.6 Snow Leopard");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "10.6");
        assertEquals(uaData.getUserAgent(), camino2[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.0.2");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(camino[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Camino");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), camino[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "1.6.7");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(camino[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Camino");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), camino[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "1.0.2");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(flock[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Flock");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), flock[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(seaMonkey[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "SeaMonkey");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows XP");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "5.1");
        assertEquals(uaData.getUserAgent(), seaMonkey[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "2.0.8");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(bots[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.UNKNOWN);
        assertEquals(uaData.getDeviceCategoryName(), "");
        assertEquals(uaData.getFamily(), "unknown");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), bots[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.UNKNOWN);
        assertEquals(uaData.getUserAgentVersion(), "");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(bots[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.OTHER);
        assertEquals(uaData.getDeviceCategoryName(), "Other");
        assertEquals(uaData.getFamily(), "Yahoo!");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), bots[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.ROBOT);
        assertEquals(uaData.getUserAgentVersion(), "");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(bots[2]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.OTHER);
        assertEquals(uaData.getDeviceCategoryName(), "Other");
        assertEquals(uaData.getFamily(), "Googlebot");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), bots[2]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.ROBOT);
        assertEquals(uaData.getUserAgentVersion(), "1.0");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(tools[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.OTHER);
        assertEquals(uaData.getDeviceCategoryName(), "Other");
        assertEquals(uaData.getFamily(), "cURL");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), tools[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.LIBRARY);
        assertEquals(uaData.getUserAgentVersion(), "7.19.5");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(tools[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Wget");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.UNKNOWN);
        assertEquals(uaData.getOsFamilyName(), "unknown");
        assertEquals(uaData.getOsName(), "unknown");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), tools[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.OFFLINE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "1.8.1");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(thunderbird3[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Thunderbird");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.LINUX);
        assertEquals(uaData.getOsFamilyName(), "Linux");
        assertEquals(uaData.getOsName(), "Linux");
        assertEquals(uaData.getOsProducer(), "");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), thunderbird3[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "3.1.6");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(thunderbird3[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Thunderbird");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 7");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.1");
        assertEquals(uaData.getUserAgent(), thunderbird3[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "3.1.2");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(thunderbird2[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Thunderbird");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Vista");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.0");
        assertEquals(uaData.getUserAgent(), thunderbird2[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "2.0.0.14");
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(thunderbird2[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "Thunderbird");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.OS_X);
        assertEquals(uaData.getOsFamilyName(), "OS X");
        assertEquals(uaData.getOsName(), "OS X");
        assertEquals(uaData.getOsProducer(), "Apple Computer, Inc.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), thunderbird2[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.EMAIL_CLIENT);
        assertEquals(uaData.getUserAgentVersion(), "2.0.0.17");        
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());

        uaData = detector.detect(bb10[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "BlackBerry Browser");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.BLACKBERRY_OS);
        assertEquals(uaData.getOsFamilyName(), "BlackBerry OS");
        assertEquals(uaData.getOsName(), "BlackBerry OS");
        assertEquals(uaData.getOsProducer(), "BlackBerry Ltd");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), bb10[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "");        
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(windowsPhone[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.SMARTPHONE);
        assertEquals(uaData.getDeviceCategoryName(), "Smartphone");
        assertEquals(uaData.getFamily(), "IE Mobile");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows Phone 8");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "");
        assertEquals(uaData.getUserAgent(), windowsPhone[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.MOBILE_BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "10.0");  
        assertFalse(uaData.isDesktop());
        assertTrue(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(surface[0]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows RT");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.2");
        assertEquals(uaData.getUserAgent(), surface[0]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "10.0");         
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());
        
        uaData = detector.detect(surface[1]);
        
        assertEquals(uaData.getDeviceCategory(), 
                DeviceCategory.PERSONAL_COMPUTER);
        assertEquals(uaData.getDeviceCategoryName(), "Personal computer");
        assertEquals(uaData.getFamily(), "IE");
        assertEquals(uaData.getOsFamily(), OperatingSystemFamily.WINDOWS);
        assertEquals(uaData.getOsFamilyName(), "Windows");
        assertEquals(uaData.getOsName(), "Windows 8.1");
        assertEquals(uaData.getOsProducer(), "Microsoft Corporation.");
        assertEquals(uaData.getOsVersion(), "6.3");
        assertEquals(uaData.getUserAgent(), surface[1]);
        assertEquals(uaData.getUserAgentType(), UserAgentType.BROWSER);
        assertEquals(uaData.getUserAgentVersion(), "11.0");                 
        assertTrue(uaData.isDesktop());
        assertFalse(uaData.isMobile());
        assertFalse(uaData.isTablet());
        assertFalse(uaData.isSmartTV());        
    }
}
