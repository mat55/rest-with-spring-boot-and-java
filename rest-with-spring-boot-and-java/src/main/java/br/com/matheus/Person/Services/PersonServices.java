package br.com.matheus.Person.Services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.matheus.Exceptions.RequiredObjectIsNullException;
import br.com.matheus.Exceptions.ResourceNotFoundException;
import br.com.matheus.Person.Controllers.PersonController;
import br.com.matheus.Person.Mapper.DozerMapper;
import br.com.matheus.Person.Mapper.Custom.PersonMapper;
import br.com.matheus.Person.Models.Person;
import br.com.matheus.Person.Repositories.PersonRepository;
import br.com.matheus.Person.Vo1.PersonVO;
import br.com.matheus.Person.Vo2.PersonVO2;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonMapper personMapper;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people");			
		List<PersonVO> personsVO = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		personsVO
			.stream()
			.forEach(vo -> {
				try {
					vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		return personsVO;
	}

	public PersonVO findById(Long id) throws Exception {
		logger.info("Finding one person");
		
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa com este ID"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {
		logger.info("Creating one person");
		
		if(person == null) {
			throw new RequiredObjectIsNullException();
		}
		
		Person entity = DozerMapper.parseObject(person, Person.class);
		PersonVO vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO2 createV2(PersonVO2 person) {
		logger.info("Creating one person with v2");
		
		if(person == null) {
			throw new RequiredObjectIsNullException();
		}
		
		Person entity = personMapper.convertVOToEntity(person);
		PersonVO2 vo2 = personMapper.convertEntityToVO(personRepository.save(entity));
		return vo2;
	}
	
	public PersonVO update(PersonVO person) throws Exception {
		logger.info("Updating one person");

		if(person == null) {
			throw new RequiredObjectIsNullException();
		}
		
		Person entity = personRepository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa com este ID"));
				
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonVO vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");		
		Person entity  = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa com este ID"));
		personRepository.delete(entity);
	}
}
