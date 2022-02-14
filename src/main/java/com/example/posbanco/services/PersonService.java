package com.example.posbanco.services;

import java.util.List;
import java.util.Optional;

import com.example.posbanco.model.Person;
import com.example.posbanco.repository.PersonRepositoryOld;
import com.example.posbanco.repository.ProductRepositoryOld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepositoryOld personRepository;

    /**
     * This method request all persons in the database
     * @return persons in the DB
     */
    public List<Person> getPersons(){
        return personRepository.getPersons();
    }

    /**
     * This method returns a person (if exists) by its unique id
     * @param id
     * @return the person requested
     */
    public Optional<Person> getPersonById(Integer id){
        return personRepository.getPersonById(id);
    }

    /**
     * This method adds a new person to the repository
     * @param person
     * @return the person added
     */
    public Person addPerson(Person person){
        return personRepository.addPerson(person);
    }

    /**
     * This method request a delete to the person (if exists) in the db
     * @param id
     */ 
    public void deletePerson(Integer id){
        personRepository.deletePerson(id);
    }

    /**
     * This method uptade the person in the db
     * @param person
     * @param id
     * @return person object
     */
    public Person uptadePerson(Person person, Integer id){
        person.setId(id);
        return personRepository.uptadePerson(person, id);
    }

    
}
