package model.mail;

public class Person {

    private String lastName;
    private String firstName;
    private String adress;

    public Person(String lastName, String firstName, String adress) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.adress = adress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
