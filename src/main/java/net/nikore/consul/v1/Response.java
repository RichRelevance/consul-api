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
package net.nikore.consul.v1;

import net.nikore.consul.transport.RawResponse;

public final class Response<T> {

  private final T value;

  private final Integer consulIndex;
  private final Boolean consulKnownLeader;
  private final Integer consulLastContact;

  public Response(T value, Integer consulIndex, Boolean consulKnownLeader, Integer consulLastContact) {
    this.value = value;
    this.consulIndex = consulIndex;
    this.consulKnownLeader = consulKnownLeader;
    this.consulLastContact = consulLastContact;
  }

  public Response(T value, RawResponse rawResponse) {
    this(value, rawResponse.getConsulIndex(), rawResponse.isConsulKnownLeader(), rawResponse.getConsulLastContact());
  }

  public T getValue() {
    return value;
  }

  public Integer getConsulIndex() {
    return consulIndex;
  }

  public Boolean isConsulKnownLeader() {
    return consulKnownLeader;
  }

  public Integer getConsulLastContact() {
    return consulLastContact;
  }

  @Override
  public String toString() {
    return "Response{" +
      "value=" + value +
      ", consulIndex=" + consulIndex +
      ", consulKnownLeader=" + consulKnownLeader +
      ", consulLastContact=" + consulLastContact +
      '}';
  }
}
