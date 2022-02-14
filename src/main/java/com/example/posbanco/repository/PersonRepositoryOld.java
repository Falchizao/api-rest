package com.example.posbanco.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.example.posbanco.model.Person;
import com.example.posbanco.model.exception.ResourceNotFound;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryOld {
    private ArrayList<Person> persons = new ArrayList<Person>();
    private Integer lastId = 0;


    /**
     * This Method returns the persons list 
     * @return all persons
     */
    public List<Person> getPersons(){
        return persons;
    }

    /**
     * This method returns a requested person object
     * @param person id 
     * @return the person (if exists)
     */
    public Optional<Person> getPersonById(Integer id){
        return persons.stream().filter(person -> person.getId() == id).findFirst();
    }

    /**
     * This method adds a new person to the list
     * @param person object
     * @return confirm statement
     */
    public Person addPerson(Person person){    
        lastId++;
        person.setId(lastId);    
        persons.add(person);    
        return person;          
    }
    


    /**
     * This method delete a person object based in its unique id
     * @param person id
     */
    public void deletePerson(Integer id){
        persons.removeIf(person -> person.getId() == id);
    }


    public Person uptadePerson(Person person, Integer id){
        Optional<Person> encontered = getPersonById(id);
        
        if(encontered.isEmpty()){
            throw new ResourceNotFound("Person not found");
        }

        deletePerson(id);
        addPerson(person);

        return person;
    }




    
}
