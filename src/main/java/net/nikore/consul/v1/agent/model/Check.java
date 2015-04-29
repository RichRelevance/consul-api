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
package net.nikore.consul.v1.agent.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Check {

  @JsonProperty("Node")
  private String node;
  @JsonProperty("CheckID")
  private String checkId;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Status")
  private CheckStatus status;
  @JsonProperty("Notes")
  private String notes;
  @JsonProperty("Output")
  private String output;
  @JsonProperty("ServiceID")
  private String serviceId;
  @JsonProperty("ServiceName")
  private String serviceName;

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public String getCheckId() {
    return checkId;
  }

  public void setCheckId(String checkId) {
    this.checkId = checkId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CheckStatus getStatus() {
    return status;
  }

  public void setStatus(CheckStatus status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  @Override
  public String toString() {
    return "Check{" +
      "node='" + node + '\'' +
      ", checkId='" + checkId + '\'' +
      ", name='" + name + '\'' +
      ", status=" + status +
      ", notes='" + notes + '\'' +
      ", output='" + output + '\'' +
      ", serviceId='" + serviceId + '\'' +
      ", serviceName='" + serviceName + '\'' +
      '}';
  }

  public static enum CheckStatus {
    UNKNOWN,
    PASSING,
    WARNING,
    CRITICAL
  }
}
