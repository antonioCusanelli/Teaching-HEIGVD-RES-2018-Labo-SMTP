package model.mail;

import org.omg.CORBA.PERSIST_STORE;

public class Person {

    private String lastName;
    private String firstName;
    private String address;

    public Person(String lastName, String firstName, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
    }

    public Person(String address){
        this.lastName = "Toto";
        this.firstName = "Tata";
        this.address = address;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
