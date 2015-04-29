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
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.catalog.model.CatalogDeregistration;
import net.nikore.consul.v1.catalog.model.CatalogNode;
import net.nikore.consul.v1.catalog.model.CatalogRegistration;
import net.nikore.consul.v1.catalog.model.CatalogService;
import net.nikore.consul.v1.catalog.model.Node;

public interface CatalogClient {

  public Response<Void> catalogRegister(CatalogRegistration catalogRegistration) throws JsonProcessingException;

  public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration, CatalogDeregistration... catalogDeregistrations) throws JsonProcessingException;

  public Response<List<String>> getCatalogDatacenters() throws IOException;

  public Response<List<Node>> getCatalogNodes(QueryParams queryParams) throws IOException;

  public Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams) throws IOException;

  public Response<List<CatalogService>> getCatalogService(String serviceName, QueryParams queryParams) throws IOException;

  public Response<List<CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams) throws IOException;

  public Response<CatalogNode> getCatalogNode(String nodeName, QueryParams queryParams) throws IOException;
}
