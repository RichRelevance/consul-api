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
package net.nikore.consul.v1.catalog.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogRegistration {

  @JsonProperty("Datacenter")
  private String datacenter;
  @JsonProperty("Node")
  private String node;
  @JsonProperty("Address")
  private String address;
  @JsonProperty("Service")
  private Service service;
  @JsonProperty("Check")
  private Check check;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public Check getCheck() {
    return check;
  }

  public void setCheck(Check check) {
    this.check = check;
  }

  @Override
  public String toString() {
    return "CatalogRegistration{" +
      "datacenter='" + datacenter + '\'' +
      ", node='" + node + '\'' +
      ", address='" + address + '\'' +
      ", service=" + service +
      ", check=" + check +
      '}';
  }

  public enum CheckStatus {
    UNKNOWN,
    PASSING,
    WARNING,
    CRITICAL
  }

  public static class Service {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("Service")
    private String service;
    @JsonProperty("Tags")
    private List<String> tags;
    @JsonProperty("Port")
    private Integer port;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getService() {
      return service;
    }

    public void setService(String service) {
      this.service = service;
    }

    public List<String> getTags() {
      return tags;
    }

    public void setTags(List<String> tags) {
      this.tags = tags;
    }

    public Integer getPort() {
      return port;
    }

    public void setPort(Integer port) {
      this.port = port;
    }

    @Override
    public String toString() {
      return "Service{" +
        "id='" + id + '\'' +
        ", service='" + service + '\'' +
        ", tags=" + tags +
        ", port=" + port +
        '}';
    }
  }

  public static class Check {

    @JsonProperty("Node")
    private String node;
    @JsonProperty("CheckID")
    private String checkId;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Notes")
    private String notes;
    @JsonProperty("Status")
    private CheckStatus status;
    @JsonProperty("ServiceID")
    private String serviceId;

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

    public String getNotes() {
      return notes;
    }

    public void setNotes(String notes) {
      this.notes = notes;
    }

    public CheckStatus getStatus() {
      return status;
    }

    public void setStatus(CheckStatus status) {
      this.status = status;
    }

    public String getServiceId() {
      return serviceId;
    }

    public void setServiceId(String serviceId) {
      this.serviceId = serviceId;
    }

    @Override
    public String toString() {
      return "Check{" +
        "node='" + node + '\'' +
        ", checkId='" + checkId + '\'' +
        ", name='" + name + '\'' +
        ", notes='" + notes + '\'' +
        ", status=" + status +
        ", serviceId='" + serviceId + '\'' +
        '}';
    }
  }
}
