package com.example.pupusa.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPhoneNumber;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    private boolean userSmsChk;

    @Column(nullable = false)
    private boolean userPushChk;

    @Column(nullable = false)
    private int userGrade;

    @Column(nullable = false)
    private boolean deleted;

}
