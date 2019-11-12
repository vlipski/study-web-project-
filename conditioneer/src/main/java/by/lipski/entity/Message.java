package by.lipski.entity;

import java.util.Date;

public class Message {


    private final String idThing = "conditioner0001";
    private String body;
    private Date date;

    public Message() {
    }

    public Message(String body) {
        this.body = body;
        date = new Date();
    }

    public String getIdThing() {
        return idThing;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", idThing='" + idThing + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}
