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
package net.nikore.consul.v1.kv.model;

import java.util.ArrayList;
import java.util.List;

import net.nikore.consul.UrlParameters;

public class PutParams implements UrlParameters {

  private long flags;
  private Long cas;
  private String acquireSession;
  private String releaseSession;

  public long getFlags() {
    return flags;
  }

  public void setFlags(long flags) {
    this.flags = flags;
  }

  public Long getCas() {
    return cas;
  }

  public void setCas(Long cas) {
    this.cas = cas;
  }

  public String getAcquireSession() {
    return acquireSession;
  }

  public void setAcquireSession(String acquireSession) {
    this.acquireSession = acquireSession;
  }

  public String getReleaseSession() {
    return releaseSession;
  }

  public void setReleaseSession(String releaseSession) {
    this.releaseSession = releaseSession;
  }

  @Override
  public List<String> toUrlParameters() {
    List<String> params = new ArrayList<>();

    if (flags != 0) {
      params.add("flags=" + flags);
    }
    if (cas != null) {
      params.add("cas=" + cas);
    }
    if (acquireSession != null) {
      params.add("acquire=" + acquireSession);
    }
    if (releaseSession != null) {
      params.add("release=" + releaseSession);
    }

    return params;
  }
}
