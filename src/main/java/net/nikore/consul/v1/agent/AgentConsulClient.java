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
package net.nikore.consul.v1.agent;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.nikore.consul.SingleUrlParameters;
import net.nikore.consul.UrlParameters;
import net.nikore.consul.json.JacksonFactory;
import net.nikore.consul.transport.RawResponse;
import net.nikore.consul.v1.ConsulRawClient;
import net.nikore.consul.v1.OperationException;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.agent.model.Check;
import net.nikore.consul.v1.agent.model.Member;
import net.nikore.consul.v1.agent.model.NewCheck;
import net.nikore.consul.v1.agent.model.NewService;
import net.nikore.consul.v1.agent.model.Self;
import net.nikore.consul.v1.agent.model.Service;

public final class AgentConsulClient implements AgentClient {

  private final ConsulRawClient rawClient;
  private final ObjectMapper mapper;

  public AgentConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public AgentConsulClient() {
    this(new ConsulRawClient());
  }

  public AgentConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public AgentConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<Map<String, Check>> getAgentChecks() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/checks");

    if (rawResponse.getStatusCode() == 200) {
      Map<String, Check> value = mapper.readValue(rawResponse.getContent(), new TypeReference<Map<String, Check>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Map<String, Service>> getAgentServices() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/services");

    if (rawResponse.getStatusCode() == 200) {
      Map<String, Service> agentServices = mapper.readValue(rawResponse.getContent(), new TypeReference<Map<String, Service>>() {
      });
      return new Response<>(agentServices, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Member>> getAgentMembers() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/members");

    if (rawResponse.getStatusCode() == 200) {
      List<Member> members = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Member>>() {
      });
      return new Response<>(members, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Self> getAgentSelf() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/self");

    if (rawResponse.getStatusCode() == 200) {
      Self self = mapper.readValue(rawResponse.getContent(), Self.class);
      return new Response<>(self, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentJoin(String address, boolean wan) {
    UrlParameters wanParams = wan ? new SingleUrlParameters("wan", "1") : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/join/" + address, wanParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentForceLeave(String node) {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/force-leave/" + node);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentCheckRegister(NewCheck newCheck) throws JsonProcessingException {
    String json = mapper.writeValueAsString(newCheck);
    RawResponse rawResponse = rawClient.makePutRequest("/v1/agent/check/register", json);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentCheckDeregister(String checkId) {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/deregister/" + checkId);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentCheckPass(String checkId) {
    return agentCheckPass(checkId, null);
  }

  @Override
  public Response<Void> agentCheckPass(String checkId, String note) {
    UrlParameters noteParams = note != null ? new SingleUrlParameters("note", note) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/pass/" + checkId, noteParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentCheckWarn(String checkId) {
    return agentCheckWarn(checkId, null);
  }

  @Override
  public Response<Void> agentCheckWarn(String checkId, String note) {
    UrlParameters noteParams = note != null ? new SingleUrlParameters("note", note) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/warn/" + checkId, noteParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentCheckFail(String checkId) {
    return agentCheckFail(checkId, null);
  }

  @Override
  public Response<Void> agentCheckFail(String checkId, String note) {
    UrlParameters noteParams = note != null ? new SingleUrlParameters("note", note) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/check/fail/" + checkId, noteParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentServiceRegister(NewService newService) throws JsonProcessingException {
    String json = mapper.writeValueAsString(newService);
    RawResponse rawResponse = rawClient.makePutRequest("/v1/agent/service/register", json);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> agentServiceDeregister(String serviceId) {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/agent/service/deregister/" + serviceId);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

}
