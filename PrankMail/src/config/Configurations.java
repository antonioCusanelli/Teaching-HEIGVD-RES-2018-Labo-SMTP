package config;

import model.mail.Message;
import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Configurations {
	private String smtpServerAdress;
	private int smtpServerPort;
	private int nbOfgroups;
	private Person prankToCc;
	private List<String> listOfPrankMessage = new ArrayList<>();
	private List<Person> listOfVictims = new ArrayList<>();

	public Configurations() {
		Properties properties = new Properties();
		InputStream file = null;
		try{
			file = new FileInputStream("configuration/config.properties");
			properties.load(file);

			smtpServerAdress = properties.getProperty("smtpServerAdress");
			smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
			nbOfgroups = Integer.parseInt(properties.getProperty("nbOfgroups"));
			prankToCc = new Person(properties.getProperty("prankToCc"));

			BufferedReader reader = new BufferedReader(new FileReader("configuration/victims.utf8"));

			String email;
			while ((email = reader.readLine()) != null){
				if(validate(email)){
					listOfVictims.add(new Person(email));
				}
			}

			reader.close();

			reader = new BufferedReader(new FileReader("configuration/messages.utf8"));

			String body = "";
			String subject = "";
			boolean firstLine = true;
			String line;
			while ((line = reader.readLine()) != null){
				//-- is the end of message marker
				if(!line.equals("--")){
					if(firstLine){
						subject = line;
						firstLine = false;
					}else{
						body += line;
					}
				}else{
					listOfPrankMessage.add(subject + "\r\n" + body);
					firstLine = true;
					subject = "";
					body = "";
				}
			}



		}catch(IOException e){
			e.printStackTrace();
		}finally {
			if(file != null){
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	//pattern to check if an email is correctly written
	//function not made by ourselves,
	//origin : https://stackoverflow.com/questions/8204680/java-regex-email
	private static boolean validate(String emailStr) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}

	public String getSmtpServerAdress() {
		return smtpServerAdress;
	}

	public int getSmtpServerPort() {
		return smtpServerPort;
	}

	public int getNbOfgroups() {
		return nbOfgroups;
	}

	public Person getPrankToCc() {
		return prankToCc;
	}

	public List<String> getListOfPrankMessage() {
		return listOfPrankMessage;
	}

	public List<Person> getListOfVictims() {
		return listOfVictims;
	}
}
