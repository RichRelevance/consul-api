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

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void testGenerateUrl() throws Exception {
    Assert.assertEquals("/some-url", Utils.generateUrl("/some-url"));
    Assert.assertEquals("/some-url", Utils.generateUrl("/some-url", (UrlParameters) null));
    Assert.assertEquals("/some-url", Utils.generateUrl("/some-url", null, null));

    //
    UrlParameters first = new SingleUrlParameters("key", "value");
    UrlParameters second = new SingleUrlParameters("key2");
    Assert.assertEquals("/some-url?key=value&key2", Utils.generateUrl("/some-url", first, second));
  }
}