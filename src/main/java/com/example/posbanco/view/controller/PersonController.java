package com.example.posbanco.view.controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.posbanco.model.Person;
import com.example.posbanco.model.exception.ResourceNotFound;
import com.example.posbanco.services.PersonService;
import com.example.posbanco.shared.PersonDTO;
import com.example.posbanco.view.model.PersonRequest;
import com.example.posbanco.view.model.PersonResponse;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * This method request the service the response of the persons in the database
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPersons(){
        List<PersonDTO> personDTOs = personService.getPersons();        
        return new ResponseEntity<>(personDTOs.stream().map(personDTO -> new ModelMapper().map(personDTO, PersonResponse.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * This method request the service the response of the person solicited
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PersonResponse>> getPersonById(@PathVariable Integer id){

        Optional<PersonDTO> dto = personService.getPersonById(id); //Here we need to attribute the personDTO to another variable
        ModelMapper map = new ModelMapper(); //then we cast into Response class
        PersonResponse person = map.map(dto, PersonResponse.class);            

        return new ResponseEntity<>(Optional.of(person), HttpStatus.OK);  //Don't forget it's optional, since its looking for its id
    }

    /**
     * This method request the service to add a person to the database
     * @param person
     * @return
     */
    @PostMapping
    public ResponseEntity<PersonResponse> addPerson(@RequestBody PersonRequest personReq){
        ModelMapper mapper = new ModelMapper();
        PersonDTO dto = mapper.map(personReq, PersonDTO.class);
        dto = personService.addPerson(dto);

        return new ResponseEntity<>(mapper.map(dto, PersonResponse.class), HttpStatus.CREATED);
    }

    /**
     * This method request to delete a person by its id
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * This method request to the service to update the person by its id
     * @param person
     * @param id
     * @return person updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> uptadePerson(@RequestBody PersonRequest person, @PathVariable Integer id){ 
        
        ModelMapper mapper = new ModelMapper();
        PersonDTO dto = mapper.map(person, PersonDTO.class);
        dto = personService.uptadePerson(dto, id);

        return new ResponseEntity<>(mapper.map(dto, PersonResponse.class), HttpStatus.OK);
    }
    
}
