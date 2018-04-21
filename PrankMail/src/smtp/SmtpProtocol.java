package smtp;

public class SmtpProtocol {

    public static final String EHLO = "EHLO hello";
    public static final String MAIL_FROM = "MAIL FROM: ";
    public static final String RCPT_TO = "RCPT TO:";
    public static final String EOL = "\r\n";
    public static final String DATA = "DATA";
    public static final String QUIT = "QUIT";
    public static final String END_OF_DATA = EOL + "." +EOL;
}
