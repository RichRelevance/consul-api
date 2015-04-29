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

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public final class DefaultHttpTransport implements HttpTransport {

  private final HttpClient httpClient;

  public DefaultHttpTransport() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(1000);
    connectionManager.setDefaultMaxPerRoute(500);

    this.httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
  }

  @Override
  public RawResponse makeGetRequest(String url) {
    HttpGet httpGet = new HttpGet(url);
    return executeRequest(httpGet);
  }

  @Override
  public RawResponse makePutRequest(String url, String content) {
    HttpPut httpPut = new HttpPut(url);
    httpPut.setEntity(new StringEntity(content, Charset.forName("UTF-8")));
    return executeRequest(httpPut);
  }

  @Override
  public RawResponse makePutRequest(String url, byte[] content) {
    HttpPut httpPut = new HttpPut(url);
    httpPut.setEntity(new ByteArrayEntity(content));
    return executeRequest(httpPut);
  }

  @Override
  public RawResponse makeDeleteRequest(String url) {
    HttpDelete httpDelete = new HttpDelete(url);
    return executeRequest(httpDelete);
  }

  private RawResponse executeRequest(HttpUriRequest httpRequest) {
    try {
      return httpClient.execute(httpRequest, response -> {
        int statusCode = response.getStatusLine().getStatusCode();
        String statusMessage = response.getStatusLine().getReasonPhrase();

        String content = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));

        Integer consulIndex = parseInteger(response.getFirstHeader("X-Consul-Index"));
        Boolean consulKnownLeader = parseBoolean(response.getFirstHeader("X-Consul-Knownleader"));
        Integer consulLastContact = parseInteger(response.getFirstHeader("X-Consul-Lastcontact"));

        return new RawResponse(statusCode, statusMessage, content, consulIndex, consulKnownLeader, consulLastContact);
      });
    } catch (IOException e) {
      throw new TransportException(e);
    }
  }

  private Integer parseInteger(Header header) {
    try {
      return Integer.parseInt(header.getValue());
    } catch (Exception e) {
      return null;
    }
  }

  private Boolean parseBoolean(Header header) {
    if (header == null) {
      return null;
    }

    if ("true".equals(header.getValue())) {
      return true;
    }

    if ("false".equals(header.getValue())) {
      return false;
    }

    return null;
  }

}
