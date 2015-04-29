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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogService {

  @JsonProperty("Node")
  private String node;
  @JsonProperty("Address")
  private String address;
  @JsonProperty("ServiceID")
  private String serviceId;
  @JsonProperty("ServiceName")
  private String serviceName;
  @JsonProperty("ServiceTags")
  private List<String> serviceTags;
  @JsonProperty("ServicePort")
  private Integer servicePort;

  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public List<String> getServiceTags() {
    return serviceTags;
  }

  public void setServiceTags(List<String> serviceTags) {
    this.serviceTags = serviceTags;
  }

  public Integer getServicePort() {
    return servicePort;
  }

  public void setServicePort(Integer servicePort) {
    this.servicePort = servicePort;
  }

  @Override
  public String toString() {
    return "CatalogService{" +
      "node='" + node + '\'' +
      ", address='" + address + '\'' +
      ", serviceId='" + serviceId + '\'' +
      ", serviceName='" + serviceName + '\'' +
      ", serviceTags=" + serviceTags +
      ", servicePort=" + servicePort +
      '}';
  }
}
