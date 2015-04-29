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
package net.nikore.consul.v1.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

  @JsonProperty("ID")
  private String id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Payload")
  private String payload;
  @JsonProperty("NodeFilter")
  private String nodeFilter;
  @JsonProperty("ServiceFilter")
  private String serviceFilter;
  @JsonProperty("TagFilter")
  private String tagFilter;
  @JsonProperty("Version")
  private int version;
  @JsonProperty("LTime")
  private int lTime;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getNodeFilter() {
    return nodeFilter;
  }

  public void setNodeFilter(String nodeFilter) {
    this.nodeFilter = nodeFilter;
  }

  public String getServiceFilter() {
    return serviceFilter;
  }

  public void setServiceFilter(String serviceFilter) {
    this.serviceFilter = serviceFilter;
  }

  public String getTagFilter() {
    return tagFilter;
  }

  public void setTagFilter(String tagFilter) {
    this.tagFilter = tagFilter;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public int getlTime() {
    return lTime;
  }

  public void setlTime(int lTime) {
    this.lTime = lTime;
  }

  @Override
  public String toString() {
    return "Event{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", payload='" + payload + '\'' +
      ", nodeFilter='" + nodeFilter + '\'' +
      ", serviceFilter='" + serviceFilter + '\'' +
      ", tagFilter='" + tagFilter + '\'' +
      ", version=" + version +
      ", lTime=" + lTime +
      '}';
  }
}
