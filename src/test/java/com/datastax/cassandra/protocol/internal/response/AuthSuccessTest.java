/*
 * Copyright (C) 2017-2017 DataStax Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datastax.cassandra.protocol.internal.response;

import com.datastax.cassandra.protocol.internal.Message;
import com.datastax.cassandra.protocol.internal.MessageTest;
import com.datastax.cassandra.protocol.internal.TestDataProviders;
import com.datastax.cassandra.protocol.internal.binary.MockBinaryString;
import com.datastax.cassandra.protocol.internal.util.Bytes;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class AuthSuccessTest extends MessageTest<AuthSuccess> {

  protected AuthSuccessTest() {
    super(AuthSuccess.class);
  }

  @Override
  protected Message.Codec newCodec(int protocolVersion) {
    return new AuthSuccess.Codec(protocolVersion);
  }

  @Test(dataProviderClass = TestDataProviders.class, dataProvider = "protocolV3OrAbove")
  public void should_decode(int protocolVersion) {
    AuthSuccess authSuccess = decode(new MockBinaryString().bytes("0xcafebabe"), protocolVersion);

    Assertions.assertThat(Bytes.toHexString(authSuccess.token)).isEqualTo("0xcafebabe");
  }
}
