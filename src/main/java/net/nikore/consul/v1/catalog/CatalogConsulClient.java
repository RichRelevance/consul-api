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
package net.nikore.consul.v1.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.catalog.model.CatalogDeregistration;
import net.nikore.consul.v1.catalog.model.CatalogNode;
import net.nikore.consul.v1.catalog.model.CatalogRegistration;
import net.nikore.consul.v1.catalog.model.CatalogService;
import net.nikore.consul.v1.catalog.model.Node;

public final class CatalogConsulClient implements CatalogClient {

  private final ConsulRawClient rawClient;
  private final ObjectMapper mapper;

  public CatalogConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public CatalogConsulClient() {
    this(new ConsulRawClient());
  }

  public CatalogConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public CatalogConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<Void> catalogRegister(CatalogRegistration catalogRegistration) throws JsonProcessingException {
    String json = mapper.writeValueAsString(catalogRegistration);

    RawResponse rawResponse = rawClient.makePutRequest("/v1/catalog/register", json);
    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration, CatalogDeregistration... catalogDeregistrations) throws JsonProcessingException {
    List<CatalogDeregistration> items = new ArrayList<>();
    items.add(catalogDeregistration);
    if (catalogDeregistrations != null) {
      items.addAll(Arrays.asList(catalogDeregistrations));
    }
    String json = mapper.writeValueAsString(items);

    RawResponse rawResponse = rawClient.makePutRequest("/v1/catalog/deregister", json);
    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<String>> getCatalogDatacenters() throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/catalog/datacenters");

    if (rawResponse.getStatusCode() == 200) {
      List<String> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<String>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Node>> getCatalogNodes(QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/catalog/nodes", queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Node> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Node>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/catalog/services", queryParams);

    if (rawResponse.getStatusCode() == 200) {
      Map<String, List<String>> value = mapper.readValue(rawResponse.getContent(), new TypeReference<Map<String, List<String>>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<CatalogService>> getCatalogService(String serviceName, QueryParams queryParams) throws IOException {
    return getCatalogService(serviceName, null, queryParams);
  }

  @Override
  public Response<List<CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams) throws IOException {
    UrlParameters tagParam = tag != null ? new SingleUrlParameters("tag", tag) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/catalog/service/" + serviceName, tagParam, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<CatalogService> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<CatalogService>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<CatalogNode> getCatalogNode(String nodeName, QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/catalog/node/" + nodeName, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      CatalogNode catalogNode = mapper.readValue(rawResponse.getContent(), CatalogNode.class);
      return new Response<>(catalogNode, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

}
