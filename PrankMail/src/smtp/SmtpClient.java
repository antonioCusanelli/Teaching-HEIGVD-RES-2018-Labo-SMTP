package smtp;

import model.mail.Message;
import sun.net.smtp.SmtpProtocolException;

import java.io.*;
import java.net.Socket;

public class SmtpClient implements ISmtpClient{

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    String line;
    String server;
    int port;
    boolean connected = true;

    public SmtpClient(String server, int port){
        this.server = server;
        this.port = port;
    }

    public void connect() throws IOException {
        try{
            if(!connected) {
                socket = new Socket(server, port);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

                //waiting for first message from server
                line = reader.readLine();
                System.out.println(line);

                //Sending EHLO
                writer.write(SmtpProtocol.EHLO + SmtpProtocol.EOL);
                writer.flush();

                //Waiting for the server to be ready
                while ((line = reader.readLine()).startsWith("250-")) {
                    System.out.println(line);
                }

                System.out.println(line);
                connected = true;
            }
        } catch (IOException e){
            System.out.println(e.getStackTrace());
            connected = false;
        }



    }

    public void sendMessage(Message message) throws IOException{
        if(connected){
            writer.write(SmtpProtocol.MAIL_FROM + message.getFrom() + SmtpProtocol.EOL);
            writer.flush();

            line = reader.readLine();
            System.out.println(line);

            for(String reciever : message.getTo()){
                writer.write(SmtpProtocol.RCPT_TO + reciever + SmtpProtocol.EOL);
                writer.flush();
                line = reader.readLine();
                System.out.println(line);
            }

            writer.write(SmtpProtocol.DATA + SmtpProtocol.EOL);
            writer.flush();

            line = reader.readLine();
            System.out.println(line);

            writer.write("From: " + message.getFrom() + SmtpProtocol.EOL);
            writer.flush();

            writer.write("To: ");
            for (String reciever : message.getTo()){
                writer.write(reciever+", ");
            }
            writer.write(SmtpProtocol.EOL);
            writer.flush();

            //Subject and message

            writer.write(SmtpProtocol.END_OF_DATA);
            writer.flush();

            writer.write(SmtpProtocol.QUIT + SmtpProtocol.EOL);
            writer.flush();
        }

    }


}
