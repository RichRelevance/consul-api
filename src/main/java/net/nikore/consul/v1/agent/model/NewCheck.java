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
package net.nikore.consul.v1.agent.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCheck {

  @JsonProperty("ID")
  private String id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Notes")
  private String notes;
  @JsonProperty("Script")
  private String script;
  @JsonProperty("Interval")
  private String interval;
  @JsonProperty("TTL")
  private String ttl;

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

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getScript() {
    return script;
  }

  public void setScript(String script) {
    this.script = script;
  }

  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }

  public String getTtl() {
    return ttl;
  }

  public void setTtl(String ttl) {
    this.ttl = ttl;
  }

  @Override
  public String toString() {
    return "NewCheck{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", notes='" + notes + '\'' +
      ", script='" + script + '\'' +
      ", interval=" + interval +
      ", ttl=" + ttl +
      '}';
  }
}
