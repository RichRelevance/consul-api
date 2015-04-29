/**
 * Copyright (C) 2015 Matt Christiansen (matt@nikore.net)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package net.nikore.consul;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {

  public static String generateUrl(String baseUrl, UrlParameters... params) {
    if (params == null) {
      return baseUrl;
    }

    List<String> allParams = new ArrayList<>();
    for (UrlParameters item : params) {
      if (item != null) {
        allParams.addAll(item.toUrlParameters());
      }
    }

    // construct the whole url
    StringBuilder result = new StringBuilder(baseUrl);

    Iterator<String> paramsIterator = allParams.iterator();
    if (paramsIterator.hasNext()) {
      result.append("?").append(paramsIterator.next());
      while (paramsIterator.hasNext()) {
        result.append("&").append(paramsIterator.next());
      }
    }
    return result.toString();
  }

}
