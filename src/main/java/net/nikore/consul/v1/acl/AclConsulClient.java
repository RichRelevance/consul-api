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
package net.nikore.consul.v1.acl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.nikore.consul.ConsulException;
import net.nikore.consul.SingleUrlParameters;
import net.nikore.consul.UrlParameters;
import net.nikore.consul.json.JacksonFactory;
import net.nikore.consul.transport.RawResponse;
import net.nikore.consul.v1.ConsulRawClient;
import net.nikore.consul.v1.OperationException;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.acl.model.Acl;
import net.nikore.consul.v1.acl.model.NewAcl;
import net.nikore.consul.v1.acl.model.UpdateAcl;

public final class AclConsulClient implements AclClient {

  private final ConsulRawClient rawClient;

  private final ObjectMapper mapper;

  public AclConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public AclConsulClient() {
    this(new ConsulRawClient());
  }

  public AclConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public AclConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<String> aclCreate(NewAcl newAcl, String token) throws IOException {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    String json = mapper.writeValueAsString(newAcl);
    RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/create", json, tokenParams);

    if (rawResponse.getStatusCode() == 200) {
      Map<String, String> value = JacksonFactory.getMapper().readValue(rawResponse.getContent(), new TypeReference<Map<String, String>>() {
      });
      return new Response<>(value.get("ID"), rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> aclUpdate(UpdateAcl updateAcl, String token) throws JsonProcessingException {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    String json = mapper.writeValueAsString(updateAcl);
    RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/update", json, tokenParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> aclDestroy(String aclId, String token) {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/destroy/" + aclId, "", tokenParams);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Acl> getAcl(String id) throws IOException {
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/acl/info/" + id);

    if (rawResponse.getStatusCode() == 200) {
      List<Acl> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Acl>>() {
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
  public Response<String> aclClone(String aclId, String token) throws IOException {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makePutRequest("/v1/acl/clone/" + aclId, "", tokenParams);

    if (rawResponse.getStatusCode() == 200) {
      Map<String, String> value = mapper.readValue(rawResponse.getContent(), new TypeReference<Map<String, String>>() {
      });
      return new Response<>(value.get("ID"), rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Acl>> getAclList(String token) throws IOException {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/acl/list", tokenParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Acl> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Acl>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }
}
