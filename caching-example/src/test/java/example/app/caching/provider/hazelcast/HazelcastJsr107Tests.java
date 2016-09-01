/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package example.app.caching.provider.hazelcast;

import org.junit.runner.RunWith;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import example.app.caching.provider.support.AbstractJsr107CachingProviderTests;
import example.app.caching.provider.support.Calculator;

/**
 * The HazelcastJsr107Tests class...
 *
 * @author John Blum
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HazelcastJsr107Tests.TestConfiguration.class, webEnvironment = WebEnvironment.NONE)
public class HazelcastJsr107Tests extends AbstractJsr107CachingProviderTests {

  @Configuration
  @SuppressWarnings("unused")
  static class HazelcastConfiguration {

    @Bean
    HazelcastInstance hazelcastInstance() {
      return Hazelcast.newHazelcastInstance(new Config(HazelcastJsr107Tests.class.getSimpleName()));
    }
  }

  @EnableAutoConfiguration
  @EnableCaching
  @Import({ HazelcastConfiguration.class, Calculator.class })
  static class TestConfiguration {
  }
}