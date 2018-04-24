package model.prank;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Message generateMessage(){
        Message m = new Message();
        m.setFrom(victimSender.getAddress());
        String[] recipient = new String[victimRecipient.size()];
        for(int i = 0; i < victimRecipient.size(); i++){
            recipient[i] = victimRecipient.get(i).getAddress();
        }
        m.setTo(recipient);
        String[] cut = message.split("\r\n");
        m.setSubject(cut[0]);
        m.setBody(cut[1]);
        return m;
    }
}
