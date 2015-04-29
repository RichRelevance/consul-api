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
package net.nikore.consul.v1.status;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import net.nikore.consul.json.JacksonFactory;
import net.nikore.consul.transport.RawResponse;
import net.nikore.consul.v1.ConsulRawClient;
import net.nikore.consul.v1.OperationException;
import net.nikore.consul.v1.Response;

public final class StatusConsulClient implements StatusClient {

  private final ConsulRawClient rawClient;

  public StatusConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
  }

  public StatusConsulClient() {
    this(new ConsulRawClient());
  }

  public StatusConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public StatusConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<String> getStatusLeader() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/status/leader");

    if (rawResponse.getStatusCode() == 200) {
      String value = JacksonFactory.getMapper().readValue(rawResponse.getContent(), String.class);
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<String>> getStatusPeers() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/status/peers");

    if (rawResponse.getStatusCode() == 200) {
      List<String> value = JacksonFactory.getMapper().readValue(rawResponse.getContent(), new TypeReference<List<String>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }
}
