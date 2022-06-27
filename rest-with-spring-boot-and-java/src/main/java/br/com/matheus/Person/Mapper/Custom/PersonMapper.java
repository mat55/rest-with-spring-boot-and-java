package br.com.matheus.Person.Mapper.Custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.matheus.Person.Models.Person;
import br.com.matheus.Person.Vo2.PersonVO2;

@Service
public class PersonMapper {
	
	public PersonVO2 convertEntityToVO(Person person) {
		PersonVO2 vo = new PersonVO2();
		vo.setId(person.getId());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		
		return vo;
	}
	
	public Person convertVOToEntity(PersonVO2 vo) {
		Person entity = new Person();
		entity.setId(vo.getId());
		entity.setAddress(vo.getAddress());
		entity.setFirstName(vo.getFirstName());
		entity.setLastName(vo.getLastName());
		entity.setGender(vo.getGender());
		
		return entity;
	}
}
