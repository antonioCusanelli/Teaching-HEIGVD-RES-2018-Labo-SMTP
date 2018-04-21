package smtp;

import model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {

    public void connect() throws IOException;
    public void sendMessage(Message message) throws IOException;
}
