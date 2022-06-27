package br.com.matheus.UnitTests.Mapper.Mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.matheus.Person.Models.Person;
import br.com.matheus.Person.Vo1.PersonVO;

public class MockPerson {

	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}

	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}
	
	public List<PersonVO> mockVOList() {
		List<PersonVO> personsVO = new ArrayList<PersonVO>();
		for (int i = 0; i < 14; i++) {
			personsVO.add(mockVO(i));
		}
		return personsVO;
	}
	
	public Person mockEntity(Integer number) {
		Person person = new Person();
		person.setId(number.longValue());
		person.setFirstName("First Name Test" + number);
		person.setLastName("Last Name Test" + number);
		person.setAddress("Address Test" + number);
		person.setGender(((number % 2) == 0) ? "Male" : "Female");
		return person;
	}

	public PersonVO mockVO(Integer number) {
		PersonVO personVO = new PersonVO();
		personVO.setKey(number.longValue());
		personVO.setFirstName("First Name Test" + number);
		personVO.setLastName("Last Name Test" + number);
		personVO.setAddress("Address Test" + number);
		personVO.setGender(((number % 2) == 0) ? "Male" : "Female");
		return personVO;
	}

}
