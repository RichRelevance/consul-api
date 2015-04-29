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
package net.nikore.consul.v1.session;

import java.io.IOException;
import java.util.List;

import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.session.model.NewSession;
import net.nikore.consul.v1.session.model.Session;

public interface SessionClient {

  Response<String> sessionCreate(NewSession newSession, QueryParams queryParams) throws IOException;

  Response<Void> sessionDestroy(String session, QueryParams queryParams);

  Response<Session> getSessionInfo(String session, QueryParams queryParams) throws IOException;

  Response<List<Session>> getSessionNode(String node, QueryParams queryParams) throws IOException;

  Response<List<Session>> getSessionList(QueryParams queryParams) throws IOException;
}
