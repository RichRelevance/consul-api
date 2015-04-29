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
package net.nikore.consul.v1.health;

import java.io.IOException;
import java.util.List;

import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.health.model.Check;
import net.nikore.consul.v1.health.model.HealthService;

public interface HealthClient {

  Response<List<Check>> getHealthChecksForNode(String nodeName, QueryParams queryParams) throws IOException;

  Response<List<Check>> getHealthChecksForService(String serviceName, QueryParams queryParams) throws IOException;

  Response<List<HealthService>> getHealthServices(String serviceName, boolean onlyPassing, QueryParams queryParams) throws IOException;

  Response<List<HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, QueryParams queryParams) throws IOException;

  Response<List<Check>> getHealthChecksState(QueryParams queryParams) throws IOException;

  Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, QueryParams queryParams) throws IOException;
}
