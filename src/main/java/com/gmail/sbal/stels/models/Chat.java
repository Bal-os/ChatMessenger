package com.gmail.sbal.stels.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * @author Alex Balashov
 */
@Entity
@Table(name="chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nickname should not be empty")
    @Size(min = 3, max = 30, message = "Nickname should be between 2 and 30 characters")
    @Column(name = "chat_name")
    private String chatName;

    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    public Chat(@NotEmpty(message = "Nickname should not be empty")
                @Size(min = 3, max = 30, message = "Nickname should be between 2 and 30 characters")
                        String chatName,
                        String title) {
        this.chatName = chatName;
        this.title = title;
    }

    public Chat(@NotEmpty(message = "Nickname should not be empty")
                @Size(min = 3, max = 30, message = "Nickname should be between 2 and 30 characters")
                        String chatName,
                        String title,
                        List<Message> messages) {
        this.chatName = chatName;
        this.title = title;
        this.users = users;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addUser(User user){
        users.add(user);
        user.getChats().add(this);
    }

    public void addMessage(Message message){
        if(!messages.contains(message)){
            messages.add(message);
            message.setChat(this);
        }
    }

    public void clearMessages(){
        messages.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) &&
                chatName.equals(chat.chatName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatName);
    }
}
