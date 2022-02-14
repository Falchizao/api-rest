package com.example.posbanco.view.model;

public class ProductResponse {

    //#region Atributos 
  
    private String observation;

    private String name;

    private Integer quantity;

    private double value;
    //#endregion

    //#region GeteSet


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
