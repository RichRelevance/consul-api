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
package net.nikore.consul.v1.kv;

import java.io.IOException;
import java.util.List;

import net.nikore.consul.v1.QueryParams;
import net.nikore.consul.v1.Response;
import net.nikore.consul.v1.kv.model.GetBinaryValue;
import net.nikore.consul.v1.kv.model.GetValue;
import net.nikore.consul.v1.kv.model.PutParams;

public interface KeyValueClient {

  Response<GetValue> getKVValue(String key) throws IOException;

  Response<GetValue> getKVValue(String key, QueryParams queryParams) throws IOException;

  Response<GetValue> getKVValue(String key, String token, QueryParams queryParams) throws IOException;

  Response<GetBinaryValue> getKVBinaryValue(String key, QueryParams queryParams) throws IOException;

  Response<GetBinaryValue> getKVBinaryValue(String key, String token, QueryParams queryParams) throws IOException;

  Response<List<GetValue>> getKVValues(String keyPrefix, QueryParams queryParams) throws IOException;

  Response<List<GetValue>> getKVValues(String keyPrefix, String token, QueryParams queryParams) throws IOException;

  Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, QueryParams queryParams) throws IOException;

  Response<List<GetBinaryValue>> getKVBinaryValues(String keyPrefix, String token, QueryParams queryParams) throws IOException;

  Response<List<String>> getKVKeysOnly(String keyPrefix, QueryParams queryParams) throws IOException;

  Response<List<String>> getKVKeysOnly(String keyPrefix, String separator, String token, QueryParams queryParams) throws IOException;

  Response<Boolean> setKVValue(String key, String value) throws IOException;

  Response<Boolean> setKVValue(String key, String value, PutParams putParams) throws IOException;

  Response<Boolean> setKVValue(String key, String value, String token, PutParams putParams) throws IOException;

  Response<Boolean> setKVBinaryValue(String key, byte[] value) throws IOException;

  Response<Boolean> setKVBinaryValue(String key, byte[] value, PutParams putParams) throws IOException;

  Response<Boolean> setKVBinaryValue(String key, byte[] value, String token, PutParams putParams) throws IOException;

  Response<Void> deleteKVValue(String key);

  Response<Void> deleteKVValue(String key, String token);

  Response<Void> deleteKVValues(String key);

  Response<Void> deleteKVValues(String key, String token);

}
