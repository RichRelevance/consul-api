/**
 * Copyright (C) 2015 Matt Christiansen (matt@nikore.net)
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
package net.nikore.consul;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

public class SingleUrlParametersTest {

  @Test
  public void testToUrlParameters() throws Exception {
    UrlParameters parameters = new SingleUrlParameters("key", "value");
    assertEquals(Collections.singletonList("key=value"), parameters.toUrlParameters());

    parameters = new SingleUrlParameters("key");
    assertEquals(Collections.singletonList("key"), parameters.toUrlParameters());

  }
}