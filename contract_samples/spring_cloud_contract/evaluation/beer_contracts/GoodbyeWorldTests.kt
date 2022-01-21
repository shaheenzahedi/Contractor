/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.loan;


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject



@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = ["com.example:producer-kotlin-ftw"],
		repositoryRoot = "stubs://classpath:contractsAtRuntime/",
		stubsMode = StubRunnerProperties.StubsMode.LOCAL,
		generateStubs = true)
class GoodbyeWorldTests {



	@StubRunnerPort("producer-kotlin-ftw")
	var port: Int? = null

	@Test
	fun shouldGenerateStubsAtRuntime() {
		// when:
		val response: String = RestTemplate().getForObject("http://localhost:" + this.port + "/goodbye", String::class)
		// then:
		assertThat(response).isEqualTo("Goodbye World!")
	}
}
