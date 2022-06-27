package br.com.matheus.Person.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.Person.Models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
