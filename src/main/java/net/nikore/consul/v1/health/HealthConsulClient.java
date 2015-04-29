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
package net.nikore.consul.v1.health;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.nikore.consul.SingleUrlParameters;
import net.nikore.consul.UrlParameters;
import net.nikore.consul.json.JacksonFactory;
import net.nikore.consul.transport.RawResponse;
import net.nikore.consul.v1.ConsulRawClient;
import net.nikore.consul.v1.OperationException;
import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.health.model.Check;
import net.nikore.consul.v1.health.model.HealthService;

public final class HealthConsulClient implements HealthClient {

  private final ConsulRawClient rawClient;
  private final ObjectMapper mapper;

  public HealthConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public HealthConsulClient() {
    this(new ConsulRawClient());
  }

  public HealthConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public HealthConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<List<Check>> getHealthChecksForNode(String nodeName, QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/health/node/" + nodeName, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Check> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Check>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Check>> getHealthChecksForService(String serviceName, QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/health/checks/" + serviceName, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Check> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Check>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<HealthService>> getHealthServices(String serviceName, boolean onlyPassing, QueryParams queryParams) throws IOException {
    return getHealthServices(serviceName, null, onlyPassing, queryParams);
  }

  @Override
  public Response<List<HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, QueryParams queryParams) throws IOException {
    UrlParameters tagParams = tag != null ? new SingleUrlParameters("tag", tag) : null;
    UrlParameters passingParams = onlyPassing ? new SingleUrlParameters("passing") : null;

    RawResponse rawResponse = rawClient.makeGetRequest("/v1/health/service/" + serviceName, tagParams, passingParams, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<HealthService> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<HealthService>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Check>> getHealthChecksState(QueryParams queryParams) throws IOException {
    return getHealthChecksState(null, queryParams);
  }

  @Override
  public Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, QueryParams queryParams) throws IOException {
    String status = checkStatus == null ? "ANY" : checkStatus.name().toLowerCase();
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/health/state/" + status, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Check> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Check>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

}
