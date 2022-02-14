package com.example.posbanco.view.model;

public class PersonResponse {

    // #region Atributes
 

    private String name;

    private String address;

    private Integer age;

    private String occupation;
    // #endregion

    // #region GettersAndSetters
   

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
    // #endregion
    
}
