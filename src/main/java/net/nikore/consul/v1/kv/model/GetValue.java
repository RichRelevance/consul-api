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
package net.nikore.consul.v1.kv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.nikore.consul.json.Base64Deserializer;

public class GetValue {

  @JsonProperty("CreateIndex")
  private long createIndex;

  @JsonProperty("ModifyIndex")
  private long modifyIndex;

  @JsonProperty("LockIndex")
  private Long lockIndex;

  @JsonProperty("Flags")
  private long flags;

  private String session;

  @JsonProperty("Key")
  private String key;

  @JsonProperty("Value")
  private String value;

  public long getCreateIndex() {
    return createIndex;
  }

  public void setCreateIndex(long createIndex) {
    this.createIndex = createIndex;
  }

  public long getModifyIndex() {
    return modifyIndex;
  }

  public void setModifyIndex(long modifyIndex) {
    this.modifyIndex = modifyIndex;
  }

  public Long getLockIndex() {
    return lockIndex;
  }

  public void setLockIndex(Long lockIndex) {
    this.lockIndex = lockIndex;
  }

  public long getFlags() {
    return flags;
  }

  public void setFlags(long flags) {
    this.flags = flags;
  }

  public String getSession() {
    return session;
  }

  public void setSession(String session) {
    this.session = session;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  @JsonDeserialize(using = Base64Deserializer.class)
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "GetValue{" +
      "createIndex=" + createIndex +
      ", modifyIndex=" + modifyIndex +
      ", lockIndex=" + lockIndex +
      ", flags=" + flags +
      ", session='" + session + '\'' +
      ", key='" + key + '\'' +
      ", value='" + value + '\'' +
      '}';
  }
}
