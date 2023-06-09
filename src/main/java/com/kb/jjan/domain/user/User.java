package com.kb.jjan.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
    @SequenceGenerator(name = "seq_users", sequenceName = "SEQ_USERS", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 40)
    private String password;

    @Column(nullable = false, length = 9)
    private String birthday;

    @Column(name = "phone_num", length = 12)
    private String phoneNum;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(length = 15)
    private String account;

    @Column(name = "family_code", length = 20)
    private String famCode;

    @Column(length = 8, columnDefinition = "NUMBER(8)")
    private int balance;

    @Column(length = 3, columnDefinition = "NUMBER(3)")
    private int achieve;

    @Column(name = "cheerup_msg", length = 100)
    private String cheerUpMsg;

    @Column(name = "is_parents", length = 1)
    private String isParent;

    @JsonBackReference
    @OneToMany(mappedBy = "sendUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Debit> sentUsers;
    @JsonBackReference
    @OneToMany(mappedBy = "receivedUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Debit> receivedUsers;

    @JsonBackReference
    @OneToMany(mappedBy = "autoSendUser", cascade = CascadeType.ALL)
    private List<Direct> autoSentDebits;

    @JsonBackReference
    @OneToMany(mappedBy = "autoReceivedUser", cascade = CascadeType.ALL)
    private List<Direct> autoReceivedDebits;

    @JsonBackReference
    @OneToMany(mappedBy = "solvedUser", cascade = CascadeType.ALL)
    private List<UserMission> solvedUsers;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAchieve(int achieve) {
        this.achieve = achieve;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setCheerUpMsg(String cheerUpMsg) {
        this.cheerUpMsg = cheerUpMsg;
    }
}