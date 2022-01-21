package com.example.intoxication;

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

import static com.example.intoxication.DrunkLevel.DRUNK;
import static com.example.intoxication.DrunkLevel.SOBER;
import static com.example.intoxication.DrunkLevel.TIPSY;
import static com.example.intoxication.DrunkLevel.WASTED;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcin Grzejszczak
 */
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.example:beer-api-producer-java")

@DirtiesContext
public class IntoxicationControllerTest extends AbstractTest {

	@Autowired MockMvc mockMvc;
	@Autowired IntoxicationController intoxicationController;

	@StubRunnerPort("beer-api-producer-java") int producerPort;

	@BeforeEach
	public void setupPort() {
		this.intoxicationController.port = this.producerPort;
	}

	@Test public void should_eventually_get_completely_wasted() throws Exception {
		
		sendARequestAndExpectStatuses(SOBER, TIPSY);
		sendARequestAndExpectStatuses(TIPSY, DRUNK);
		sendARequestAndExpectStatuses(DRUNK, WASTED);
		
	}

	private void sendARequestAndExpectStatuses(DrunkLevel previousStatus, DrunkLevel currentStatus) throws Exception {
		
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/wasted")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json.write(new Person("marcin")).getJson()))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"previousStatus\":\"" + previousStatus.name() +
						"\",\"currentStatus\":\"" + currentStatus.name() + "\"}"));
		
		
	}
}


