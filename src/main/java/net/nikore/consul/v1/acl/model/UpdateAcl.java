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
package net.nikore.consul.v1.acl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateAcl {

  @JsonProperty("ID")
  private String id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Type")
  private AclType type;
  @JsonProperty("Rules")
  private String rules;

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

  public AclType getType() {
    return type;
  }

  public void setType(AclType type) {
    this.type = type;
  }

  public String getRules() {
    return rules;
  }

  public void setRules(String rules) {
    this.rules = rules;
  }

  @Override
  public String toString() {
    return "UpdateAcl{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", type=" + type +
      ", rules='" + rules + '\'' +
      '}';
  }
}
