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
package net.nikore.consul.v1;

import net.nikore.consul.UrlParameters;
import net.nikore.consul.Utils;
import net.nikore.consul.transport.DefaultHttpTransport;
import net.nikore.consul.transport.HttpTransport;
import net.nikore.consul.transport.RawResponse;

public class ConsulRawClient {

  private static final String DEFAULT_HOST = "localhost";
  private static final int DEFAULT_PORT = 8500;

  // one real HTTP client for all instances
  private static final HttpTransport DEFAULT_HTTP_TRANSPORT = new DefaultHttpTransport();

  private final HttpTransport httpTransport;
  private final String agentAddress;

  public ConsulRawClient() {
    this(DEFAULT_HOST);
  }

  public ConsulRawClient(String agentHost) {
    this(agentHost, DEFAULT_PORT);
  }

  public ConsulRawClient(String agentHost, int agentPort) {
    this(DEFAULT_HTTP_TRANSPORT, agentHost, agentPort);
  }

  // hidden constructor, for tests
  ConsulRawClient(HttpTransport httpTransport, String agentHost, int agentPort) {
    this.httpTransport = httpTransport;
    this.agentAddress = "http://" + agentHost + ":" + agentPort;
  }

  public RawResponse makeGetRequest(String endpoint, UrlParameters... urlParams) {
    String url = agentAddress + endpoint;
    url = Utils.generateUrl(url, urlParams);

    return httpTransport.makeGetRequest(url);
  }

  public RawResponse makePutRequest(String endpoint, String content, UrlParameters... urlParams) {
    String url = agentAddress + endpoint;
    url = Utils.generateUrl(url, urlParams);

    return httpTransport.makePutRequest(url, content);
  }

  public RawResponse makePutRequest(String endpoint, byte[] content, UrlParameters... urlParams) {
    String url = agentAddress + endpoint;
    url = Utils.generateUrl(url, urlParams);

    return httpTransport.makePutRequest(url, content);
  }

  public RawResponse makeDeleteRequest(String endpoint, UrlParameters... urlParams) {
    String url = agentAddress + endpoint;
    url = Utils.generateUrl(url, urlParams);

    return httpTransport.makeDeleteRequest(url);
  }

}
