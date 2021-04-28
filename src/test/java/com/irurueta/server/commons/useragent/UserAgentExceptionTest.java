/*
 * Copyright (C) 2016 Alberto Irurueta Carro (alberto@irurueta.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.irurueta.server.commons.useragent;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserAgentExceptionTest {

    @Test
    public void testConstructor() {
        UserAgentException ex = new UserAgentException();
        assertNotNull(ex);

        ex = new UserAgentException("message");
        assertNotNull(ex);

        ex = new UserAgentException(new Exception());
        assertNotNull(ex);

        ex = new UserAgentException("message", new Exception());
        assertNotNull(ex);
    }
}
