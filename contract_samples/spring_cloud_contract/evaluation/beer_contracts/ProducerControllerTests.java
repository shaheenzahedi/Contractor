package com.example;

import com.example.model.PersonToCheck;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.wiremock.restdocs.SpringCloudContractRestDocs;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Marcin Grzejszczak
 */
@SpringBootTest(classes = ProducerControllerTests.Config.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DirtiesContext
public class ProducerControllerTests {

	@Autowired private MockMvc mockMvc;

	private JacksonTester<PersonToCheck> json;

	@BeforeEach
	public void setup() {
		ObjectMapper objectMappper = new ObjectMapper();
		// Possibly configure the mapper
		JacksonTester.initFields(this, objectMappper);
	}

	@Test
	public void should_grant_a_beer_when_person_is_old_enough() throws Exception {
		PersonToCheck personToCheck = new PersonToCheck(34);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/check")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json.write(personToCheck).getJson()))
				.andExpect(jsonPath("$.status").value("OK"))
				.andDo(WireMockRestDocs.verify()
						.jsonPath("$[?(@.age >= 20)]")
						.contentType(MediaType.valueOf("application/json"))
						.stub("shouldGrantABeerIfOldEnough"))
				.andDo(MockMvcRestDocumentation.document("shouldGrantABeerIfOldEnough",
						SpringCloudContractRestDocs.dslContract()));
		
	}

	@Test
	public void should_reject_a_beer_when_person_is_too_young() throws Exception {
		PersonToCheck personToCheck = new PersonToCheck(10);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/check")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.json.write(personToCheck).getJson()))
				.andExpect(jsonPath("$.status").value("NOT_OK"))
				.andDo(WireMockRestDocs.verify()
						.jsonPath("$[?(@.age < 20)]")
						.contentType(MediaType.valueOf("application/json"))
						.stub("shouldRejectABeerIfTooYoung"))
				.andDo(MockMvcRestDocumentation.document("shouldRejectABeerIfTooYoung",
						SpringCloudContractRestDocs.dslContract()));
		
	}

	@Configuration
	@EnableAutoConfiguration
	static class Config {
		//remove:start[]
		@Bean
		PersonCheckingService personCheckingService() {
			return personToCheck -> personToCheck.age >= 20;
		}
		

		@Bean
		ProducerController producerController(PersonCheckingService service) {
			return new ProducerController(service);
		}
	}
}
