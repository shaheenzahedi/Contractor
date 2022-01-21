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

package binary

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.cloud.contract.spec.internal.HttpMethods

contract {
	request {
		url = url("/1")
		method = PUT
		headers {
			contentType = APPLICATION_OCTET_STREAM
		}
		body = bodyFromFileAsBytes("request.pdf")
	}
	response {
		status = OK
		body = bodyFromFileAsBytes("response.pdf")
		headers {
			contentType = APPLICATION_OCTET_STREAM
		}
	}
}
