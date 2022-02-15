package com.example.posbanco.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.posbanco.model.Person;
import com.example.posbanco.model.exception.ResourceNotFound;
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
    public PersonDTO addPerson(PersonDTO person){
        ModelMapper map = new ModelMapper();
        Person personNormal = map.map(person, Person.class);
        personRepository.save(personNormal);
        person.setId(personNormal.getId());

        return person;
    }

    /**
     * This method request a delete to the person (if exists) in the db
     * @param id
     */ 
    public void deletePerson(Integer id){
        Optional<Person> person = personRepository.findById(id);

        if(person.isEmpty()){ //If not found, we throw a exception
            throw new ResourceNotFound("Person by id Not found!");
        }

        personRepository.deleteById(id);        
    }

    /**
     * This method uptade the person in the db
     * @param person
     * @param id
     * @return person object
     */
    public PersonDTO uptadePerson(PersonDTO person, Integer id){
        //Passar o id pro Banco, depois deletar o objeto da DB, e adicionar a nova com o body att
        person.setId(id); //Se o spring recebe um objeto com id, significa que é para att, caso não, é pra cadastrar
        deletePerson(id);           
        return addPerson(person);
    }

    
}
