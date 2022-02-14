package com.example.posbanco.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.posbanco.model.Person;
import com.example.posbanco.repository.PersonRepository;
import com.example.posbanco.repository.PersonRepositoryOld;
import com.example.posbanco.shared.PersonDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * This method request all persons in the database
     * @return persons in the DB
     */
    public List<PersonDTO> getPersons(){ //DTO is the tipe of model that we will work in the request and response
        List<Person> persons = personRepository.findAll(); //Here we got all the persons in a List 

        return persons.stream() //Now we will use the method map to cast theses persons into personsDTO(Other class)
        .map(person -> new ModelMapper().map(person, PersonDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * This method returns a person (if exists) by its unique id
     * @param id
     * @return the person requested
     */
    public Optional<PersonDTO> getPersonById(Integer id){
        Optional<Person> person = personRepository.findById(id);
        PersonDTO dto = new ModelMapper().map(person.get(), PersonDTO.class);
        return Optional.of(dto);
    }

    /**
     * This method adds a new person to the repository
     * @param person
     * @return the person added
     */
    public PersonDTO addPerson(Person person){
        return personRepository.save(person);
    }

    /**
     * This method request a delete to the person (if exists) in the db
     * @param id
     */ 
    public void deletePerson(Integer id){
        personRepository.deleteById(id);
    }

    /**
     * This method uptade the person in the db
     * @param person
     * @param id
     * @return person object
     */
    public PersonDTO uptadePerson(Person person, Integer id){
        person.setId(id);
        return personRepository.save(person);
    }

    
}
