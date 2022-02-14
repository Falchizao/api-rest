package com.example.posbanco.model;

import javax.persistence.*;

@Entity
public class Product {
    //#region Atributos

    @Id //Will turn this atribute into a primary key in db
    @GeneratedValue(strategy = GenerationType.AUTO) //Generate the id automatically
    private Integer id;

    private String observation;

    private String name;

    private Integer quantity;

    private double value;
    //#endregion

    //#region GeteSet
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    //#endregion


    
}
