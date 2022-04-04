package com.example.pupusa.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String userpw;

    public User(UserRequestDto urd){
        this.userid = urd.getUserID();
        this.userpw = urd.getUserPW();
    }

    public void update(UserRequestDto urd){
        this.userid = urd.getUserID();
        this.userpw = urd.getUserPW();
    }

}
