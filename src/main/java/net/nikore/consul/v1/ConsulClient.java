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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import net.nikore.consul.v1.acl.AclClient;
import net.nikore.consul.v1.acl.AclConsulClient;
import net.nikore.consul.v1.acl.model.Acl;
import net.nikore.consul.v1.acl.model.NewAcl;
import net.nikore.consul.v1.acl.model.UpdateAcl;
import net.nikore.consul.v1.agent.AgentClient;
import net.nikore.consul.v1.agent.AgentConsulClient;
import net.nikore.consul.v1.agent.model.Member;
import net.nikore.consul.v1.agent.model.NewCheck;
import net.nikore.consul.v1.agent.model.NewService;
import net.nikore.consul.v1.agent.model.Self;
import net.nikore.consul.v1.agent.model.Service;
import net.nikore.consul.v1.catalog.CatalogClient;
import net.nikore.consul.v1.catalog.CatalogConsulClient;
import net.nikore.consul.v1.catalog.model.CatalogDeregistration;
import net.nikore.consul.v1.catalog.model.CatalogNode;
import net.nikore.consul.v1.catalog.model.CatalogRegistration;
import net.nikore.consul.v1.catalog.model.CatalogService;
import net.nikore.consul.v1.catalog.model.Node;
import net.nikore.consul.v1.event.EventClient;
import net.nikore.consul.v1.event.EventConsulClient;
import net.nikore.consul.v1.event.model.Event;
import net.nikore.consul.v1.event.model.EventParams;
import net.nikore.consul.v1.health.HealthClient;
import net.nikore.consul.v1.health.HealthConsulClient;
import net.nikore.consul.v1.health.model.Check;
import net.nikore.consul.v1.health.model.HealthService;
import net.nikore.consul.v1.kv.KeyValueClient;
import net.nikore.consul.v1.kv.KeyValueConsulClient;
import net.nikore.consul.v1.kv.model.GetBinaryValue;
import net.nikore.consul.v1.kv.model.GetValue;
import net.nikore.consul.v1.kv.model.PutParams;
import net.nikore.consul.v1.session.SessionClient;
import net.nikore.consul.v1.session.SessionConsulClient;
import net.nikore.consul.v1.session.model.NewSession;
import net.nikore.consul.v1.session.model.Session;
import net.nikore.consul.v1.status.StatusClient;
import net.nikore.consul.v1.status.StatusConsulClient;

public final class ConsulClient implements AclClient, AgentClient, CatalogClient, EventClient, HealthClient, KeyValueClient, SessionClient, StatusClient {

  private final AclClient aclClient;
  private final AgentClient agentClient;
  private final CatalogClient catalogClient;
  private final EventClient eventClient;
  private final HealthClient healthClient;
  private final KeyValueClient keyValueClient;
  private final SessionClient sessionClient;
  private final StatusClient statusClient;

  public ConsulClient(ConsulRawClient rawClient) {
    aclClient = new AclConsulClient(rawClient);
    agentClient = new AgentConsulClient(rawClient);
    catalogClient = new CatalogConsulClient(rawClient);
    eventClient = new EventConsulClient(rawClient);
    healthClient = new HealthConsulClient(rawClient);
    keyValueClient = new KeyValueConsulClient(rawClient);
    sessionClient = new SessionConsulClient(rawClient);
    statusClient = new StatusConsulClient(rawClient);
  }

  public ConsulClient() {
    this(new ConsulRawClient());
  }

  public ConsulClient(String agentHost) {
    this(new ConsulRawClient(agentHost));
  }

  public ConsulClient(String agentHost, int agentPort) {
    this(new ConsulRawClient(agentHost, agentPort));
  }

  @Override
  public Response<String> aclCreate(NewAcl newAcl, String token) throws IOException {
    return aclClient.aclCreate(newAcl, token);
  }

  @Override
  public Response<Void> aclUpdate(UpdateAcl updateAcl, String token) throws JsonProcessingException {
    return aclClient.aclUpdate(updateAcl, token);
  }

  @Override
  public Response<Void> aclDestroy(String aclId, String token) {
    return aclClient.aclDestroy(aclId, token);
  }

  @Override
  public Response<Acl> getAcl(String id) throws IOException {
    return aclClient.getAcl(id);
  }

  @Override
  public Response<String> aclClone(String aclId, String token) throws IOException {
    return aclClient.aclClone(aclId, token);
  }

  @Override
  public Response<List<Acl>> getAclList(String token) throws IOException {
    return aclClient.getAclList(token);
  }

  @Override
  public Response<Map<String, net.nikore.consul.v1.agent.model.Check>> getAgentChecks() throws IOException {
    return agentClient.getAgentChecks();
  }

  @Override
  public Response<Map<String, Service>> getAgentServices() throws IOException {
    return agentClient.getAgentServices();
  }

  @Override
  public Response<List<Member>> getAgentMembers() throws IOException {
    return agentClient.getAgentMembers();
  }

  @Override
  public Response<Self> getAgentSelf() throws IOException {
    return agentClient.getAgentSelf();
  }

  @Override
  public Response<Void> agentJoin(String address, boolean wan) {
    return agentClient.agentJoin(address, wan);
  }

  @Override
  public Response<Void> agentForceLeave(String node) {
    return agentClient.agentForceLeave(node);
  }

  @Override
  public Response<Void> agentCheckRegister(NewCheck newCheck) throws JsonProcessingException {
    return agentClient.agentCheckRegister(newCheck);
  }

  @Override
  public Response<Void> agentCheckDeregister(String checkId) {
    return agentClient.agentCheckDeregister(checkId);
  }

  @Override
  public Response<Void> agentCheckPass(String checkId) {
    return agentClient.agentCheckPass(checkId);
  }

  @Override
  public Response<Void> agentCheckPass(String checkId, String note) {
    return agentClient.agentCheckPass(checkId, note);
  }

  @Override
  public Response<Void> agentCheckWarn(String checkId) {
    return agentClient.agentCheckWarn(checkId);
  }

  @Override
  public Response<Void> agentCheckWarn(String checkId, String note) {
    return agentClient.agentCheckWarn(checkId, note);
  }

  @Override
  public Response<Void> agentCheckFail(String checkId) {
    return agentClient.agentCheckFail(checkId);
  }

  @Override
  public Response<Void> agentCheckFail(String checkId, String note) {
    return agentClient.agentCheckFail(checkId, note);
  }

  @Override
  public Response<Void> agentServiceRegister(NewService newService) throws JsonProcessingException {
    return agentClient.agentServiceRegister(newService);
  }

  @Override
  public Response<Void> agentServiceDeregister(String serviceId) {
    return agentClient.agentServiceDeregister(serviceId);
  }

  @Override
  public Response<Void> catalogRegister(CatalogRegistration catalogRegistration) throws JsonProcessingException {
    return catalogClient.catalogRegister(catalogRegistration);
  }

  @Override
  public Response<Void> catalogDeregister(CatalogDeregistration catalogDeregistration, CatalogDeregistration... catalogDeregistrations) throws JsonProcessingException {
    return catalogClient.catalogDeregister(catalogDeregistration, catalogDeregistrations);
  }

  @Override
  public Response<List<String>> getCatalogDatacenters() throws IOException {
    return catalogClient.getCatalogDatacenters();
  }

  @Override
  public Response<List<Node>> getCatalogNodes(QueryParams queryParams) throws IOException {
    return catalogClient.getCatalogNodes(queryParams);
  }

  @Override
  public Response<Map<String, List<String>>> getCatalogServices(QueryParams queryParams) throws IOException {
    return catalogClient.getCatalogServices(queryParams);
  }

  @Override
  public Response<List<CatalogService>> getCatalogService(String serviceName, QueryParams queryParams) throws IOException {
    return catalogClient.getCatalogService(serviceName, queryParams);
  }

  @Override
  public Response<List<CatalogService>> getCatalogService(String serviceName, String tag, QueryParams queryParams) throws IOException {
    return catalogClient.getCatalogService(serviceName, tag, queryParams);
  }

  @Override
  public Response<CatalogNode> getCatalogNode(String nodeName, QueryParams queryParams) throws IOException {
    return catalogClient.getCatalogNode(nodeName, queryParams);
  }

  @Override
  public Response<Event> eventFire(String event, String payload, EventParams eventParams, QueryParams queryParams) throws IOException {
    return eventClient.eventFire(event, payload, eventParams, queryParams);
  }

  @Override
  public Response<List<Event>> eventList(QueryParams queryParams) throws IOException {
    return eventClient.eventList(queryParams);
  }

  @Override
  public Response<List<Event>> eventList(String event, QueryParams queryParams) throws IOException {
    return eventClient.eventList(event, queryParams);
  }

  @Override
  public Response<List<Check>> getHealthChecksForNode(String nodeName, QueryParams queryParams) throws IOException {
    return healthClient.getHealthChecksForNode(nodeName, queryParams);
  }

  @Override
  public Response<List<Check>> getHealthChecksForService(String serviceName, QueryParams queryParams) throws IOException {
    return healthClient.getHealthChecksForService(serviceName, queryParams);
  }

  @Override
  public Response<List<HealthService>> getHealthServices(String serviceName, boolean onlyPassing, QueryParams queryParams) throws IOException {
    return healthClient.getHealthServices(serviceName, onlyPassing, queryParams);
  }

  @Override
  public Response<List<HealthService>> getHealthServices(String serviceName, String tag, boolean onlyPassing, QueryParams queryParams) throws IOException {
    return healthClient.getHealthServices(serviceName, tag, onlyPassing, queryParams);
  }

  @Override
  public Response<List<Check>> getHealthChecksState(QueryParams queryParams) throws IOException {
    return healthClient.getHealthChecksState(queryParams);
  }

  @Override
  public Response<List<Check>> getHealthChecksState(Check.CheckStatus checkStatus, QueryParams queryParams) throws IOException {
    return healthClient.getHealthChecksState(checkStatus, queryParams);
  }

  @Override
  public Response<GetValue> getKVValue(String key) throws IOException {
    return keyValueClient.getKVValue(key, null);
  }

  @Override
  public Response<GetValue> getKVValue(String key, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVValue(key, queryParams);
  }

  @Override
  public Response<GetValue> getKVValue(String key, String token, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVValue(key, token, queryParams);
  }

  @Override
  public Response<GetBinaryValue> getKVBinaryValue(String key, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVBinaryValue(key, queryParams);
  }

  @Override
  public Response<GetBinaryValue> getKVBinaryValue(String key, String token, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVBinaryValue(key, token, queryParams);
  }

  @Override
  public Response<List<GetValue>> getKVValues(String keyPrefix, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVValues(keyPrefix, queryParams);
  }

  @Override
  public Response<List<GetValue>> getKVValues(String keyPrefix, String token, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVValues(keyPrefix, token, queryParams);
  }

  @Override
  public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVBinaryValues(keyPrefix, queryParams);
  }

  @Override
  public Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVBinaryValues(keyPrefix, token, queryParams);
  }

  @Override
  public Response<List<String>> getKVKeysOnly(String keyPrefix, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVKeysOnly(keyPrefix, queryParams);
  }

  @Override
  public Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, QueryParams queryParams) throws IOException {
    return keyValueClient.getKVKeysOnly(keyPrefix, separator, token, queryParams);
  }

  @Override
  public Response<Boolean> setKVValue(String key, String value) throws IOException {
    return keyValueClient.setKVValue(key, value);
  }

  @Override
  public Response<Boolean> setKVValue(String key, String value, PutParams putParams) throws IOException {
    return keyValueClient.setKVValue(key, value, putParams);
  }

  @Override
  public Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams) throws IOException {
    return keyValueClient.setKVValue(key, value, token, putParams);
  }

  @Override
  public Response<Boolean> setKVBinaryValue(String key, byte[] value) throws IOException {
    return keyValueClient.setKVBinaryValue(key, value);
  }

  @Override
  public Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams) throws IOException {
    return keyValueClient.setKVBinaryValue(key, value, putParams);
  }

  @Override
  public Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams) throws IOException {
    return keyValueClient.setKVBinaryValue(key, value, token, putParams);
  }

  @Override
  public Response<Void> deleteKVValue(String key) {
    return keyValueClient.deleteKVValue(key);
  }

  @Override
  public Response<Void> deleteKVValue(String key, String token) {
    return keyValueClient.deleteKVValue(key, token);
  }

  @Override
  public Response<Void> deleteKVValues(String key) {
    return keyValueClient.deleteKVValues(key);
  }

  @Override
  public Response<Void> deleteKVValues(String key, String token) {
    return keyValueClient.deleteKVValues(key, token);
  }

  @Override
  public Response<String> sessionCreate(NewSession newSession, QueryParams queryParams) throws IOException {
    return sessionClient.sessionCreate(newSession, queryParams);
  }

  @Override
  public Response<Void> sessionDestroy(String session, QueryParams queryParams) {
    return sessionClient.sessionDestroy(session, queryParams);
  }

  @Override
  public Response<Session> getSessionInfo(String session, QueryParams queryParams) throws IOException {
    return sessionClient.getSessionInfo(session, queryParams);
  }

  @Override
  public Response<List<Session>> getSessionNode(String node, QueryParams queryParams) throws IOException {
    return sessionClient.getSessionNode(node, queryParams);
  }

  @Override
  public Response<List<Session>> getSessionList(QueryParams queryParams) throws IOException {
    return sessionClient.getSessionList(queryParams);
  }

  @Override
  public Response<String> getStatusLeader() throws IOException {
    return statusClient.getStatusLeader();
  }

  @Override
  public Response<List<String>> getStatusPeers() throws IOException {
    return statusClient.getStatusPeers();
  }
}
