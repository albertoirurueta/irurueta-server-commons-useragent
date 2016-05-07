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

/**
 * This enum represents the type of a user agent. 
 */
public enum UserAgentType {
    /**
     * A web browser, especially on a desktop, notebook or workstation.
     */
    BROWSER,
    
    /**
     * An email client, email reader.
     */
    EMAIL_CLIENT,
    
    /**
     * A news aggregator, also termed as feed aggregator, feed reader, news 
     * reader or RSS reader.
     */
    FEED_READER,
    
    /**
     * A library is a collection of resources used to develop software.
     */
    LIBRARY,
    
    /**
     * Media player, also called multimedia player, is a term typically used to 
     * describe computer software for playing back multimedia files.
     */
    MEDIAPLAYER,
    
    /**
     * A mobile browser, also called a microbrowser, minibrowser, or wireless
     * internet browser (WIB), is a web browser designed for use on a mobile
     * device.
     */
    MOBILE_BROWSER,
    
    /**
     * Offline browser which may download completely or partially a website to a
     * hard drive.
     */
    OFFLINE_BROWSER,
    
    /**
     * Bots, such as Web crawlers.
     */
    ROBOT,
    
    /**
     * A software to hide the real user agent information.
     */
    USERAGENT_ANONYMIZER,
    
    /**
     * A software or service that validates parts of a website or webservice, 
     * such as CSS, HTML, JSON, XML.
     */
    VALIDATOR,
    
    /**
     * A WAP browser is a web browser for mobile devices such as mobile phones 
     * that use the Wireless Application Protocol (WAP).
     */
    WAP_BROWSER,
    
    /**
     * A software that doesn't match the other types.
     */
    OTHER,
    
    /**
     * An unknown user agent type.
     */
    UNKNOWN
}
