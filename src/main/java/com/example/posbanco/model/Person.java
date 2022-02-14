package com.example.posbanco.model;

import javax.persistence.*;

@Entity
public class Person {
    //#region Atributes

    @Id //Will turn this atribute into a primary key in db
    @GeneratedValue(strategy = GenerationType.AUTO)  //Generate the id automatically
    private Integer id;

    private String name;

    private String address;

    private Integer age;

    private String occupation;
    //#endregion

    //#region GettersAndSetters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    //#endregion
    
}
