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
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogNode {

  @JsonProperty("Node")
  private Node node;
  @JsonProperty("Services")
  private Map<String, Service> services;

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public Map<String, Service> getServices() {
    return services;
  }

  public void setServices(Map<String, Service> services) {
    this.services = services;
  }

  @Override
  public String toString() {
    return "CatalogNode{" +
      "node=" + node +
      ", services=" + services +
      '}';
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
}


