package com.gmail.sbal.stels.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Alex Balashov
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nickname should not be empty")
    @Size(min = 3, max = 30, message = "Nickname should be between 2 and 30 characters")
    @Column(name = "nickname")
    private String nickName;

    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    private String bio;

    @ManyToMany( mappedBy = "users" )
    private Set<Chat> chats = new HashSet<>();

    public User() {
    }

    public User(@NotEmpty(message = "Nickname should not be empty")
                @Size(min = 3, max = 30, message = "Nickname should be between 2 and 30 characters")
                        String nickName,
                        String name,
                @NotEmpty(message = "Email should not be empty")
                @Email(message = "Email should be valid")
                        String email,
                        String bio) {
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public void addChat(Chat chat){
        chats.add(chat);
        chat.getUsers().add(this);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                nickName.equals(user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName);
    }
}
