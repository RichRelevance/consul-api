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
package net.nikore.consul.v1.session;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.nikore.consul.ConsulException;
import net.nikore.consul.json.JacksonFactory;
import net.nikore.consul.transport.RawResponse;
import net.nikore.consul.v1.ConsulRawClient;
import net.nikore.consul.v1.OperationException;
import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.session.model.NewSession;
import net.nikore.consul.v1.session.model.Session;

public final class SessionConsulClient implements SessionClient {

  private final ConsulRawClient rawClient;
  private final ObjectMapper mapper;

  public SessionConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public SessionConsulClient() {
    this(new ConsulRawClient());
  }

  public SessionConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public SessionConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<String> sessionCreate(NewSession newSession, QueryParams queryParams) throws IOException {
    String json = mapper.writeValueAsString(newSession);
    RawResponse rawResponse = rawClient.makePutRequest("/v1/session/create", json, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      Map<String, String> value = mapper.readValue(rawResponse.getContent(), new TypeReference<Map<String, String>>() {
      });
      return new Response<>(value.get("ID"), rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> sessionDestroy(String session, QueryParams queryParams) {
    RawResponse rawResponse = rawClient.makePutRequest("/v1/session/destroy/" + session, "", queryParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Session> getSessionInfo(String session, QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/session/info/" + session, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Session> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Session>>() {
      });

      if (value.isEmpty()) {
        return new Response<>(null, rawResponse);
      } else if (value.size() == 1) {
        return new Response<>(value.get(0), rawResponse);
      } else {
        throw new ConsulException("Strange response (list size=" + value.size() + ")");
      }
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Session>> getSessionNode(String node, QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/session/node/" + node, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Session> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Session>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Session>> getSessionList(QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/session/list", queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Session> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Session>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }
}
