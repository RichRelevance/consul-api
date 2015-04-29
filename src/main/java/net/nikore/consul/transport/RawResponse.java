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
package net.nikore.consul.transport;

public final class RawResponse {

  private final int statusCode;
  private final String statusMessage;

  private final String content;

  private final Integer consulIndex;
  private final Boolean consulKnownLeader;
  private final Integer consulLastContact;

  public RawResponse(int statusCode, String statusMessage, String content, Integer consulIndex, Boolean consulKnownLeader, Integer consulLastContact) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
    this.content = content;
    this.consulIndex = consulIndex;
    this.consulKnownLeader = consulKnownLeader;
    this.consulLastContact = consulLastContact;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public String getContent() {
    return content;
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
}
