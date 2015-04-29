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
package net.nikore.consul.v1.event;

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
import net.nikore.consul.v1.event.model.Event;
import net.nikore.consul.v1.event.model.EventParams;

public final class EventConsulClient implements EventClient {

  private final ConsulRawClient rawClient;

  private final ObjectMapper mapper;

  public EventConsulClient(ConsulRawClient rawClient) {
    this.rawClient = rawClient;
    this.mapper = JacksonFactory.getMapper();
  }

  public EventConsulClient() {
    this(new ConsulRawClient());
  }

  public EventConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public EventConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<Event> eventFire(String event, String payload, EventParams eventParams, QueryParams queryParams) throws IOException {
    RawResponse rawResponse = rawClient.makePutRequest("/v1/event/fire/" + event, payload, eventParams, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      Event value = mapper.readValue(rawResponse.getContent(), Event.class);
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }

  @Override
  public Response<List<Event>> eventList(QueryParams queryParams) throws IOException {
    return eventList(null, queryParams);
  }

  @Override
  public Response<List<Event>> eventList(String event, QueryParams queryParams) throws IOException {
    UrlParameters eventParams = event != null ? new SingleUrlParameters("name", event) : null;
    RawResponse rawResponse = rawClient.makeGetRequest("/v1/event/list", eventParams, queryParams);

    if (rawResponse.getStatusCode() == 200) {
      List<Event> value = mapper.readValue(rawResponse.getContent(), new TypeReference<List<Event>>() {
      });
      return new Response<>(value, rawResponse);
    } else {
      throw new OperationException(rawResponse);
    }
  }
}
