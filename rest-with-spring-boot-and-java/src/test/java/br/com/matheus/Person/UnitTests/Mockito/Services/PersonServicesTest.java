package br.com.matheus.Person.UnitTests.Mockito.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.matheus.Exceptions.RequiredObjectIsNullException;
import br.com.matheus.Person.Models.Person;
import br.com.matheus.Person.Repositories.PersonRepository;
import br.com.matheus.Person.Services.PersonServices;
import br.com.matheus.Person.Vo1.PersonVO;
import br.com.matheus.UnitTests.Mapper.Mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices personService;
	
	@Mock
	PersonRepository personRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
		
		when(personRepository.findAll()).thenReturn(list);
		
		var people = personService.findAll();
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var personOne = people.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Address Test1", personOne.getAddress());
		assertEquals("Female", personOne.getGender());
		
		var personFour = people.get(4);
		assertNotNull(personFour);
		assertNotNull(personFour.getKey());
		assertNotNull(personFour.getLinks());
		assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		assertEquals("First Name Test4", personFour.getFirstName());
		assertEquals("Last Name Test4", personFour.getLastName());
		assertEquals("Address Test4", personFour.getAddress());
		assertEquals("Male", personFour.getGender());
		
		var personSeven = people.get(7);
		assertNotNull(personSeven);
		assertNotNull(personSeven.getKey());
		assertNotNull(personSeven.getLinks());
		assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
		assertEquals("First Name Test7", personSeven.getFirstName());
		assertEquals("Last Name Test7", personSeven.getLastName());
		assertEquals("Address Test7", personSeven.getAddress());
		assertEquals("Female", personSeven.getGender());
	}

	@Test
	void testFindById() throws Exception {
		Person person = input.mockEntity(1);
		person.setId(1L);
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		
		var result = personService.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreate() throws Exception {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		when(personRepository.save(entity)).thenReturn(persisted);
		
		var result = personService.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullPerson() throws Exception {		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personService.create(null);
		});
		
		String expectedMessage = "Não é permitido persistir objeto nulo";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testCreateV2() throws Exception {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		when(personRepository.save(entity)).thenReturn(persisted);
		
		var result = personService.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateV2WithNullPerson() throws Exception {		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personService.create(null);
		});
		
		String expectedMessage = "Não é permitido persistir objeto nulo";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(personRepository.save(entity)).thenReturn(persisted);
		
		var result = personService.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Address Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateWithNullPerson() throws Exception {		
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			personService.update(null);
		});
		
		String expectedMessage = "Não é permitido persistir objeto nulo";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() throws Exception {
		Person person = input.mockEntity(1);
		person.setId(1L);
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(person));
		
		personService.delete(1L);
	}

}
