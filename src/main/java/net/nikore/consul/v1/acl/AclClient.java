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
package net.nikore.consul.v1.acl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.acl.model.Acl;
import net.nikore.consul.v1.acl.model.NewAcl;
import net.nikore.consul.v1.acl.model.UpdateAcl;

public interface AclClient {
  Response<String> aclCreate(NewAcl newAcl, String token) throws IOException;

  Response<Void> aclUpdate(UpdateAcl updateAcl, String token) throws JsonProcessingException;

  Response<Void> aclDestroy(String aclId, String token);

  Response<Acl> getAcl(String id) throws IOException;

  Response<String> aclClone(String aclId, String token) throws IOException;

  Response<List<Acl>> getAclList(String token) throws IOException;
}
