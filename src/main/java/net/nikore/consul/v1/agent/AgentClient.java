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
package net.nikore.consul.v1.agent;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.agent.model.Check;
import net.nikore.consul.v1.agent.model.Member;
import net.nikore.consul.v1.agent.model.NewCheck;
import net.nikore.consul.v1.agent.model.NewService;
import net.nikore.consul.v1.agent.model.Self;
import net.nikore.consul.v1.agent.model.Service;

public interface AgentClient {

  Response<Map<String, Check>> getAgentChecks() throws IOException;

  Response<Map<String, Service>> getAgentServices() throws IOException;

  Response<List<Member>> getAgentMembers() throws IOException;

  Response<Self> getAgentSelf() throws IOException;

  Response<Void> agentJoin(String address, boolean wan);

  Response<Void> agentForceLeave(String node);

  Response<Void> agentCheckRegister(NewCheck newCheck) throws JsonProcessingException;

  Response<Void> agentCheckDeregister(String checkId);

  Response<Void> agentCheckPass(String checkId);

  Response<Void> agentCheckPass(String checkId, String note);

  Response<Void> agentCheckWarn(String checkId);

  Response<Void> agentCheckWarn(String checkId, String note);

  Response<Void> agentCheckFail(String checkId);

  Response<Void> agentCheckFail(String checkId, String note);

  Response<Void> agentServiceRegister(NewService newService) throws JsonProcessingException;

  Response<Void> agentServiceDeregister(String serviceId);
}
