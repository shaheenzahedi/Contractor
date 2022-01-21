package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcin Grzejszczak
 */
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
// example of usage with fixed port
//@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.example:beer-api-producer:+:stubs:8090")
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.example:beer-api-producer-java")

@DirtiesContext
public class BeerControllerTest extends AbstractTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	BeerController beerController;

	@StubRunnerPort("beer-api-producer-java")
	int producerPort;

	@BeforeEach
	public void setupPort() {
		this.beerController.port = this.producerPort;
	}

	
	@Test
	public void should_give_me_a_beer_when_im_old_enough() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json.write(new Person("marcin", 22)).getJson()))
				.andExpect(status().isOk())
				.andExpect(content().string("THERE YOU GO"));
		
	}

	@Test
	public void should_reject_a_beer_when_im_too_young() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json.write(new Person("marcin", 17)).getJson()))
				.andExpect(status().isOk())
				.andExpect(content().string("GET LOST"));
		
	}
	
}

/*



	@Test public void should_give_me_a_beer_when_im_old_enough() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(new Person("marcin", 22)).getJson()))
				.andExpect(status().isOk())
				.andExpect(content().string("THERE YOU GO"));
	}

	@Test public void should_reject_a_beer_when_im_too_young() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(new Person("marcin", 17)).getJson()))
				.andExpect(status().isOk())
				.andExpect(content().string("GET LOST"));
	}

 */

package com.example;

		import java.util.HashMap;
		import java.util.Map;

		import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
		import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
		import au.com.dius.pact.consumer.junit5.PactTestFor;
		import au.com.dius.pact.core.model.RequestResponsePact;
		import au.com.dius.pact.core.model.annotations.Pact;
		import org.junit.jupiter.api.Test;
		import org.junit.jupiter.api.extension.ExtendWith;

		import org.springframework.web.client.RestTemplate;

		import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Marcin Grzejszczak
 */
//@org.junit.jupiter.api.Disabled;
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "beer-api-producer-pact", port = "8083")
public class BeerControllerTest {

	BeerController controller = new BeerController(new RestTemplate());

	@Pact(consumer="beer-api-consumer-pact")
	public RequestResponsePact beerNotOk(PactDslWithProvider builder) {
		return builder
				.given("")
				.uponReceiving("Represents a successful scenario of getting a beer")
				.path("/check")
				.method("POST")
				.headers("Content-Type", "application/json;charset=UTF-8")
				.body("{\"name\":\"marcin\",\"age\":25}")
				.willRespondWith()
				.status(200)
				.body("{\"status\":\"OK\"}")
				.headers(responseHeaders())
				.given("")
				.uponReceiving("Represents an unsuccessful scenario of getting a beer")
				.path("/check")
				.method("POST")
				.headers("Content-Type", "application/json;charset=UTF-8")
				.body("{\"name\":\"marcin\",\"age\":10}")
				.willRespondWith()
				.status(200)
				.body("{\"status\":\"NOT_OK\"}")
				.headers(responseHeaders())
				.toPact();
	}

	@Test
	public void runTestBeer() {
		// OK
		assertEquals(this.controller.gimmeABeer(new Person("marcin", 25)), "THERE YOU GO");

		// NOT_OK
		assertEquals(this.controller.gimmeABeer(new Person("marcin", 10)), "GET LOST");
	}

	private Map<String, String> responseHeaders() {
		Map<String, String> map = new HashMap<>();
		map.put("Content-Type", "application/json;charset=UTF-8");
		return map;
	}
}