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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewService {

  @JsonProperty("ID")
  private String id;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("Tags")
  private List<String> tags;
  @JsonProperty("Port")
  private Integer port;
  @JsonProperty("Check")
  private Check check;

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

  public Check getCheck() {
    return check;
  }

  public void setCheck(Check check) {
    this.check = check;
  }

  @Override
  public String toString() {
    return "NewService{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", tags=" + tags +
      ", port=" + port +
      ", check=" + check +
      '}';
  }

  public static class Check {

    @JsonProperty("Script")
    private String script;
    @JsonProperty("Interval")
    private String interval;
    @JsonProperty("TTL")
    private String ttl;

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
      return "Check{" +
        "script='" + script + '\'' +
        ", interval=" + interval +
        ", ttl=" + ttl +
        '}';
    }
  }
}
