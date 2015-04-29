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
package net.nikore.consul.v1;

import java.util.ArrayList;
import java.util.List;

import net.nikore.consul.UrlParameters;

public final class QueryParams implements UrlParameters {

  public static final QueryParams DEFAULT = new QueryParams(ConsistencyMode.DEFAULT);

  private final String datacenter;
  private final ConsistencyMode consistencyMode;

  private final int waitTime;
  private final int index;

  public QueryParams(String datacenter) {
    this.datacenter = datacenter;
    this.consistencyMode = ConsistencyMode.DEFAULT;
    this.waitTime = -1;
    this.index = -1;
  }

  public QueryParams(ConsistencyMode consistencyMode) {
    this.datacenter = null;
    this.consistencyMode = consistencyMode;
    this.waitTime = -1;
    this.index = -1;
  }

  public QueryParams(String datacenter, ConsistencyMode consistencyMode) {
    this.datacenter = datacenter;
    this.consistencyMode = consistencyMode;
    this.waitTime = -1;
    this.index = -1;
  }

  public QueryParams(int waitTime, int index) {
    this.datacenter = null;
    this.consistencyMode = ConsistencyMode.DEFAULT;
    this.waitTime = waitTime;
    this.index = index;
  }

  public String getDatacenter() {
    return datacenter;
  }

  public ConsistencyMode getConsistencyMode() {
    return consistencyMode;
  }

  public int getWaitTime() {
    return waitTime;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public List<String> toUrlParameters() {
    List<String> params = new ArrayList<>();

    // add basic params
    if (datacenter != null) {
      params.add("dc=" + datacenter);
    }

    if (consistencyMode != ConsistencyMode.DEFAULT) {
      params.add(consistencyMode.name().toLowerCase());
    }

    if (waitTime != -1) {
      params.add("wait=" + waitTime + "s");
    }
    if (index != -1) {
      params.add("index=" + index);
    }

    return params;
  }
}
