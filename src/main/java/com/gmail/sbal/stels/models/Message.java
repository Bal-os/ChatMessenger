package com.gmail.sbal.stels.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Alex Balashov
 */
@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name="chat_id")
    private Chat chat;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
        this.date = date;
    }

    public Message(String text, Chat chat) {
        this.text = text;
        this.date = date;
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
