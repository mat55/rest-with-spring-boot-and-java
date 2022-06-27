package br.com.matheus.IntegrationTests.Controller.WithJson;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.matheus.Configs.TestConfigs;
import br.com.matheus.IntegrationTests.VO.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest {
	
	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;
	
	private static PersonVO personVO;
	
	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		personVO = new PersonVO();
	}
	
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockPerson();
		
		specification = new RequestSpecBuilder()
			.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "https://matheus.com.br")
			.setBasePath("/api/person/v1")
			.setPort(TestConfigs.SERVER_PORT)
			.addFilter(new RequestLoggingFilter(LogDetail.ALL))
			.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
			.build();
		
		var content = given()
					.spec(specification)
						.contentType(TestConfigs.CONTENT_TYPE_JSON)
						.body(personVO)
						.when()
							.post()
					.then()
						.statusCode(200)
					.extract()
						.body()
							.asString();
		
		PersonVO createdPersonVO = objectMapper.readValue(content, PersonVO.class);
		personVO = createdPersonVO;
		
		assertNotNull(createdPersonVO);
		assertNotNull(createdPersonVO.getId());
		assertNotNull(createdPersonVO.getFirstName());
		assertNotNull(createdPersonVO.getLastName());
		assertNotNull(createdPersonVO.getAddress());
		assertNotNull(createdPersonVO.getGender());
		
		assertTrue(createdPersonVO.getId() > 0);
		
		assertEquals("Richard", createdPersonVO.getFirstName());
		assertEquals("Stallman", createdPersonVO.getLastName());
		assertEquals("New York, US", createdPersonVO.getAddress());
		assertEquals("Male", createdPersonVO.getGender());
	}

	private void mockPerson() {
		personVO.setFirstName("´Richard");		
		personVO.setLastName("Stallman");		
		personVO.setAddress("New York, US");		
		personVO.setGender("Male");		
	}
}
