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

public class Session {

  @JsonProperty("LockDelay")
  private long lockDelay;
  @JsonProperty("Checks")
  private List<String> checks;
  @JsonProperty("Node")
  private String node;
  @JsonProperty("ID")
  private String id;
  @JsonProperty("CreateIndex")
  private long createIndex;

  public long getLockDelay() {
    return lockDelay;
  }

  public void setLockDelay(long lockDelay) {
    this.lockDelay = lockDelay;
  }

  public List<String> getChecks() {
    return checks;
  }

  public void setChecks(List<String> checks) {
    this.checks = checks;
  }

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getCreateIndex() {
    return createIndex;
  }

  public void setCreateIndex(long createIndex) {
    this.createIndex = createIndex;
  }

  @Override
  public String toString() {
    return "Session{" +
      "lockDelay=" + lockDelay +
      ", checks=" + checks +
      ", node='" + node + '\'' +
      ", id='" + id + '\'' +
      ", createIndex=" + createIndex +
      '}';
  }
}
