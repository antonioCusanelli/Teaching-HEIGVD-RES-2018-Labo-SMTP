package model.prank;

import config.Configurations;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PrankGenerator {
    private Configurations config;

    public PrankGenerator(Configurations config){
        this.config = config;
    }

    public List<Prank> generatePranks(){
        List<Prank> pranks = new ArrayList<>();
        List<Person> victims = config.getListOfVictims();
        int numberOfGroups = config.getNbOfgroups();
        List<String> messages = config.getListOfPrankMessage();
        int messageIndex = 0;

        if(victims.size() / numberOfGroups < 3){
            numberOfGroups = victims.size() / 3;
        }

        List<Group> groups = generateGroups(victims, numberOfGroups);
        for(Group g : groups){
            Prank prank = new Prank();

            List<Person> groupVictims = g.getMembers();
            Collections.shuffle(groupVictims);
            Person sender = groupVictims.remove(0);
            prank.setVictimSender(sender);
            for(Person p : groupVictims){
                prank.addVictimRecipient(p);
            }
            prank.addVictimRecipient(config.getPrankToCc());
            prank.setMessage(messages.get(messageIndex));
            messageIndex = (messageIndex + 1) % messages.size();

            pranks.add(prank);

        }

        return pranks;
    }

    public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
        List<Person> availableVictims = new ArrayList<>(victims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();

        for(int i = 0; i < numberOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }
        int counter = 0;
        Group actualGroup;
        while (counter < availableVictims.size()){
            actualGroup = groups.get(counter);
            actualGroup.addMember(availableVictims.remove(0));
            counter = (counter + 1) % groups.size();
        }
        return groups;
    }
}
