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
package net.nikore.consul.v1.kv;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.nikore.consul.ConsulException;
import net.nikore.consul.SingleUrlParameters;
import net.nikore.consul.UrlParameters;
import net.nikore.consul.json.JacksonFactory;
import net.nikore.consul.transport.RawResponse;
import net.nikore.consul.v1.ConsulRawClient;
import net.nikore.consul.v1.OperationException;
import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.kv.model.GetBinaryValue;
import net.nikore.consul.v1.kv.model.GetValue;
import net.nikore.consul.v1.kv.model.PutParams;

public final class KeyValueConsulClient implements KeyValueClient {

  private final ConsulRawClient rawClient;
  private final ObjectMapper mapper;

  public KeyValueConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public KeyValueConsulClient() {
    this(new ConsulRawClient());
  }

  public KeyValueConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public KeyValueConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<GetValue> getKVValue(String key) throws IOException {
    return getKVValue(key, null, null);
  }

  @Override
  public Response<GetValue> getKVValue(String key, QueryParams queryParams) throws IOException {
    return getKVValue(key, null, queryParams);
  }

  @Override
  public Response<GetValue> getKVValue(String key, String token, QueryParams queryParams) throws IOException {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + key, tokenParams, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<GetValue> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<GetValue>>() {
      });

      if (value.size() == 0) {
        return new Response<>(null, rawResponse);
      } else if (value.size() == 1) {
        return new Response<>(value.get(0), rawResponse);
      } else {
        throw new ConsulException("Strange response (list size=" + value.size() + ")");
      }
    } else if (rawResponse.getStatusCode() == 404) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<GetBinaryValue> getKVBinaryValue(String key, QueryParams queryParams) throws IOException {
    return getKVBinaryValue(key, null, queryParams);
  }

  @Override
  public Response<GetBinaryValue> getKVBinaryValue(String key, String token, QueryParams queryParams) throws IOException {
    UrlParameters tokenParams = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + key, tokenParams, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<GetBinaryValue> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<GetBinaryValue>>() {
      });

      if (value.size() == 0) {
        return new Response<>(null, rawResponse);
      } else if (value.size() == 1) {
        return new Response<>(value.get(0), rawResponse);
      } else {
        throw new ConsulException("Strange response (list size=" + value.size() + ")");
      }
    } else if (rawResponse.getStatusCode() == 404) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<GetValue>> getKVValues(String keyPrefix, QueryParams queryParams) throws IOException {
    return getKVValues(keyPrefix, null, queryParams);
  }

  @Override
  public Response<List<GetValue>> getKVValues(String keyPrefix, String token, QueryParams queryParams) throws IOException {
    UrlParameters recurseParam = new SingleUrlParameters("recurse");
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, recurseParam, tokenParam, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<GetValue> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<GetValue>>() {
      });
      return new Response<>(value, rawResponse);
    } else if (rawResponse.getStatusCode() == 404) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, QueryParams queryParams) throws IOException {
    return getKVBinaryValues(keyPrefix, null, queryParams);
  }

  @Override
  public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, QueryParams queryParams) throws IOException {
    UrlParameters recurseParam = new SingleUrlParameters("recurse");
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, recurseParam, tokenParam, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<GetBinaryValue> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<GetBinaryValue>>() {
      });
      return new Response<>(value, rawResponse);
    } else if (rawResponse.getStatusCode() == 404) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<String>> getKVKeysOnly(String keyPrefix, QueryParams queryParams) throws IOException {
    return getKVKeysOnly(keyPrefix, null, null, queryParams);
  }

  @Override
  public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, QueryParams queryParams) throws IOException {
    UrlParameters separatorParam = separator != null ? new SingleUrlParameters("separator", separator) : null;
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/kv/" + keyPrefix, separatorParam, tokenParam, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<String> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<String>>() {
      });
      return new Response<>(value, rawResponse);
    } else if (rawResponse.getStatusCode() == 404) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Boolean> setKVValue(String key, String value) throws IOException {
    return setKVValue(key, value, null, null);
  }

  @Override
  public Response<Boolean> setKVValue(String key, String value, PutParams putParams) throws IOException {
    return setKVValue(key, value, null, putParams);
  }

  @Override
  public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams) throws IOException {
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makePutRequest("/v1/kv/" + key, value, putParams, tokenParam);

    if (rawResponse.getStatusCode() == 200) {
      boolean result = mapper.readValue(rawResponse.getContent(), boolean.class);
      return new Response<>(result, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Boolean> setKVBinaryValue(String key, byte[] value) throws IOException {
    return setKVBinaryValue(key, value, null, null);
  }

  @Override
  public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams) throws IOException {
    return setKVBinaryValue(key, value, null, putParams);
  }

  @Override
  public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams) throws IOException {
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makePutRequest("/v1/kv/" + key, value, putParams, tokenParam);

    if (rawResponse.getStatusCode() == 200) {
      boolean result = mapper.readValue(rawResponse.getContent(), boolean.class);
      return new Response<>(result, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> deleteKVValue(String key) {
    return deleteKVValue(key, null);
  }

  @Override
  public Response<Void> deleteKVValue(String key, String token) {
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeDeleteRequest("/v1/kv/" + key, tokenParam);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<Void> deleteKVValues(String key) {
    return deleteKVValues(key, null);
  }

  @Override
  public Response<Void> deleteKVValues(String key, String token) {
    UrlParameters recurseParam = new SingleUrlParameters("recurse");
    UrlParameters tokenParam = token != null ? new SingleUrlParameters("token", token) : null;
    RawResponse rawResponse = rawClient.makeDeleteRequest("/v1/kv/" + key, tokenParam, recurseParam);

    if (rawResponse.getStatusCode() == 200) {
      return new Response<>(null, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }
}
