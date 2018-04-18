package model.prank;

import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {

    private Person victimSender;
    private List<Person> victimRecipient = new ArrayList<>();
    private List<Person> witnessRecipient = new ArrayList<>();
    private String message;


    public Person getVictimSender() {
        return victimSender;
    }

    public List<Person> getVictimRecipient() {
        return victimRecipient;
    }

    public List<Person> getWitnessRecipient() {
        return witnessRecipient;
    }

    public String getMessage() {
        return message;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addVictimRecipient(Person p){
        victimRecipient.add(p);
    }

    public void addWitnessRecipient(Person p){
        witnessRecipient.add(p);
    }
}
