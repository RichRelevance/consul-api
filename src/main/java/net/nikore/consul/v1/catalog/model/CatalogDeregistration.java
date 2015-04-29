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
package net.nikore.consul.v1.catalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogDeregistration {

  @JsonProperty("Datacenter")
  private String datacenter;
  @JsonProperty("Node")
  private String node;
  @JsonProperty("CheckID")
  private String checkId;
  @JsonProperty("ServiceID")
  private String serviceId;

  public String getDatacenter() {
    return datacenter;
  }

  public void setDatacenter(String datacenter) {
    this.datacenter = datacenter;
  }

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

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  @Override
  public String toString() {
    return "CatalogDeregistration{" +
      "datacenter='" + datacenter + '\'' +
      ", node='" + node + '\'' +
      ", checkId='" + checkId + '\'' +
      ", serviceId='" + serviceId + '\'' +
      '}';
  }
}
