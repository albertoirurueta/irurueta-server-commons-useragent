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
 * Represents the more commonly used operating system families.
 */
public enum OperatingSystemFamily {
    /**
     * AIX (Advanced Interactive eXecutive) is a Unix operating system from IBM.
     */
    AIX,

    /**
     * AmigaOS is the native operating system for the Commodore Amiga,
     * consisting of the components of Workbench, AmigaDOS with the command line
     * interpreter CLI (later renamed to shell) and for many Amiga models in the
     * ROM included kernel kickstart.
     */
    AMIGA_OS,

    /**
     * Android is both an operating system and a software platform for mobile
     * devices like smart phones, mobile phones, netbooks and tablets, which is
     * developed by the Open Handset Alliance.
     */
    ANDROID,

    /**
     * AROS is a free operating system aiming at being compatible with AmigaOS
     * at the API level.
     */
    AROS,

    /**
     * Bada is a service-oriented operating system that is developed by Samsung
     * Electronics and is designed for use in smartphones.
     */
    BADA,

    /**
     * Be Operating System (BeOS) was an operating system of the company
     * Be Incorporated and was called in later versions Be.
     */
    BEOS,

    /**
     * BlackBerry OS (up to the fifth edition known as the BlackBerry Device
     * Software, also known as Research In Motion OS) is a proprietary mobile
     * operating system developed by BlackBerry Ltd for its BlackBerry line
     * of smartphone handheld devices.
     */
    BLACKBERRY_OS,

    /**
     * Binary Runtime Environment for Wireless (Brew MP, Brew, or BREW) is an
     * application development platform created by Qualcomm.
     */
    BREW,

    /**
     * The Berkeley Software Distribution (BSD) is a version of the Unix
     * operating system, which was created at the University of California at
     * Berkeley in 1977.
     */
    BSD,

    /**
     * Danger OS ia a smartphone operating system.
     */
    DANGEROS,

    /**
     * Firefox OS is an open source operating system for smartphones and tablet
     * computers being developed by Mozilla.
     */
    FIREFOX_OS,

    /**
     * Haiku (formerly OpenBeOS) is an open-source project with the aim, to
     * reprogram and expand that in 2001 abandoned operating system BeOS.
     */
    HAIKU,

    /**
     * HP-UX (Hewlett Packard UniX) is a commercial Unix operating system from
     * Hewlett-Packard and is based on UNIX System V.
     */
    HPUX,

    /**
     * Inferno is a distributed computer operating system that comes from Bell
     * Laboratories.
     */
    INFERNO_OS,

    /**
     * iOS (until June 2010 iPhone OS) is the standard operating system of Apple
     * products like iPhone, iPod touch, iPad, and the second generation of
     * Apple TV. iOS is based on Mac OS X.
     */
    IOS,

    /**
     * IRIX is a commercial Unix operating system of the company Silicon
     * Graphics (SGI).
     */
    IRIX,

    /**
     * The Java Virtual Machine (abbreviated Java VM or JVM) is the part of the
     * Java Runtime Environment (JRE) for Java programs, which is responsible
     * for the execution of Java bytecode.
     */
    JVM,

    /**
     * Linux or GNU/Linux are usually called free, unix-like multi-user
     * operating systems running based on the Linux kernel and other GNU
     * software.
     */
    LINUX,

    /**
     * Mac OS is the name of the classic operating system (1984-2001) by Apple
     * for Macintosh computers.
     */
    MAC_OS,

    /**
     * MeeGo was a Linux kernel-based free mobile operating system project
     * resulting from the fusion of Intel's Moblin and Nokia's Maemo operating
     * systems.
     */
    MEEGO,

    /**
     * Minix is a free unixoides operating system that was developed by Andrew
     * S.
     */
    MINIX,

    /**
     * MorphOS is an Amiga-compatible computer operating system.
     */
    MORPHOS,

    /**
     * This value indicates the operating systems from Nintendo, which they
     * developed for their devices.
     */
    NINTENDO,

    /**
     * OpenVMS (Open Virtual Memory System), previously known as VAX-11/VMS,
     * VAX/VMS or (informally) VMS, is a computer server operating system that
     * runs on VAX, Alpha and Itanium-based families of computers.
     */
    OPENVMS,

    /**
     * OS/2 is a multitasking operating system for PCs.
     */
    OS_2,

    /**
     * OS X, formerly Mac OS X, is a Unix-based operating systems developed by
     * Apple.
     */
    OS_X,

    /**
     * Palm OS was the operating system for organizer of the Palm series (known
     * as PDAs) and smartphones.
     */
    PALM_OS,

    /**
     * The PlayStation Vita system software is the official, updatable firmware
     * and operating system for the PlayStation Vita.
     */
    PLAYSTATION_VITA,

    /**
     * QNX is a POSIX-compatible proprietary Unix-like real-time operating
     * system that focused primarily at the embedded market.
     */
    QNX,

    /**
     * RISC OS is a computer operating system originally designed by Acorn
     * Computers Ltd.
     */
    RISC_OS,

    /**
     * Sailfish is a Linux-based mobile operating system developed by Jolla in
     * cooperation with the Mer project and supported by the Sailfish Alliance.
     */
    SAILFISH_OS,

    /**
     * Solaris is the name of an operating system distribution based on SunOS
     * and is a Unix operating system.
     */
    SOLARIS,

    /**
     * Syllable is a slim and fast desktop Unix-like operating system for x86
     * processors.
     */
    SYLLABLE,

    /**
     * The Symbian platform, simply called Symbian, is an operating system for
     * smartphones and PDAs.
     */
    SYMBIAN,

    /**
     * Tizen is a free operating system based on Linux respectively Debian and
     * was launched by the Linux Foundation and LiMo Foundation.
     */
    TIZEN,

    /**
     * Unknown operating system family.
     * This value will be returned if the operating system family cannot be
     * determined.
     */
    UNKNOWN,

    /**
     * webOS is a smartphone and tablet operating system from Hewlett-Packard
     * (formerly HP Palm).
     */
    WEBOS,

    /**
     * The Wii Operating System is based on Nintendo's proprietary software and
     * runs on the Wii video game console.
     */
    WII_OS,

    /**
     * Microsoft Windows is a trademark for operating systems of the Microsoft
     * Corporation.
     */
    WINDOWS,

    /**
     * XrossMediaBar (XMB) is the name of the graphical user interface, which
     * are used on PlayStation 3, PlayStation Portable, Sony Blu-Ray players and
     * Sony Bravia TVs.
     */
    XROSSMEDIABAR,
}

