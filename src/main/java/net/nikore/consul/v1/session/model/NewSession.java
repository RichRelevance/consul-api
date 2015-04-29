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
package net.nikore.consul.v1.session.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewSession {

  @JsonProperty("LockDelay")
  private long lockDelay;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Node")
  private String node;
  @JsonProperty("Checks")
  private List<String> checks;

  public long getLockDelay() {
    return lockDelay;
  }

  public void setLockDelay(long lockDelayInSeconds) {
    this.lockDelay = lockDelayInSeconds;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public List<String> getChecks() {
    return checks;
  }

  public void setChecks(List<String> checks) {
    this.checks = checks;
  }
}
