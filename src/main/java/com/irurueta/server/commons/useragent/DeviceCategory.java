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
 * Device categories based on user agent.
 * This is used to categorize devices used to make web requests from
 */
public enum DeviceCategory {
    /**
     * A game console is an interactive compute that produces a video display
     * signal which can be used with a display device (a television, monitor,
     * etc.) to display a video game.
     */
    GAME_CONSOLE,

    /**
     * A personal digital assistant (PDA), also known as a palmtop computer, or
     * personal data assistant, is a mobile device that functions as a personal
     * information manager.
     */
    PDA,

    /**
     * A personal computer (PC) is a general-purpose computer, whose size,
     * capabilities, and original sale price makes it useful for individuals,
     * and which is intended to be operated directly by an end-user with no
     * intervening computer operator.
     */
    PERSONAL_COMPUTER,

    /**
     * A smart TV, sometimes referred to as connected TV or hybrid TV.
     */
    SMART_TV,

    /**
     * A smartphone is a mobile phone build on a mobile operating system, with
     * more advanced computing capability and connectivity than a feature phone.
     */
    SMARTPHONE,

    /**
     * A tablet computer, or simply tablet, is a mobile computer with display,
     * circuitry and battery in a single unit.
     */
    TABLET,

    /**
     * Wearable computers, also known as body-borne computers are miniature
     * electronic devices that are worn by the bearer under, with or on top of
     * clothing.
     */
    WEARABLE_COMPUTER,

    /**
     * A device that doesn't match the other categories.
     */
    OTHER,

    /**
     * An unknown device category.
     */
    UNKNOWN
}
