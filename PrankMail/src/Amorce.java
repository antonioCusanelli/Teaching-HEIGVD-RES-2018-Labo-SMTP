import config.Configurations;
import model.mail.Message;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

public class Amorce {

    public static void main(String[] args) throws IOException{
        Configurations config = new Configurations("config.properties", "victims.utf8","messages.utf8");
        SmtpClient client = new SmtpClient(config.getSmtpServerAdress(), config.getSmtpServerPort());

        try{
            client.connect();
            Message m;
            PrankGenerator pg = new PrankGenerator(config);
            List<Prank> pranks = pg.generatePranks();

            for(Prank p : pranks){
                m = p.generateMessage();
                client.sendMessage(m);
            }

        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }
}
