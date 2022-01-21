package com.example;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Marcin Grzejszczak
 */
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext
public class BeerControllerKotlinTest extends AbstractTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	BeerController beerController;

	@RegisterExtension
	static StubRunnerExtension rule = new StubRunnerExtension()
			.downloadStub("com.example","beer-api-producer-kotlin")
			.stubsMode(StubRunnerProperties.StubsMode.LOCAL);

	@BeforeAll
	public static void beforeClass() {
		Assumptions.assumeTrue(atLeast210(), "Spring Cloud Contract must be in version at least 2.1.0");
		Assumptions.assumeTrue(StringUtils.isEmpty(System.getenv("OLD_PRODUCER_TRAIN")), "Env var OLD_PRODUCER_TRAIN must not be set");
	}

	@BeforeEach
	public void setupPort() {
		
		this.beerController.port = rule.findStubUrl("beer-api-producer-kotlin").getPort();
		
	}

	private static boolean atLeast210() {
		try {
			Class.forName("org.springframework.cloud.contract.verifier.util.ContractVerifierUtil");
		} catch (Exception ex) {
			return false;
		}
		return true;
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