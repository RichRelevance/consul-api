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
package net.nikore.consul.v1.event.model;

import java.util.ArrayList;
import java.util.List;

import net.nikore.consul.UrlParameters;

public class EventParams implements UrlParameters {

  private String name;
  private String service;
  private String tag;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public List<String> toUrlParameters() {
    List<String> result = new ArrayList<>();

    if (name != null) {
      result.add("name=" + name);
    }

    if (service != null) {
      result.add("service=" + service);
    }

    if (tag != null) {
      result.add("tag=" + tag);
    }

    return result;
  }
}
