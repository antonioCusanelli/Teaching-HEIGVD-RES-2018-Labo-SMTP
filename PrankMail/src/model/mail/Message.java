package model.mail;

public class Message {

    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String body;

    public Message() {
        this.to = new String[0];
        this.cc = new String[0];
        this.bcc = new String[0];
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
