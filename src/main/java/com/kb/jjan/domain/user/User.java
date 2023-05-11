package com.kb.jjan.domain.user;

import com.kb.jjan.domain.bank.autoDebit.AutoDebit;
import com.kb.jjan.domain.bank.history.History;
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

    @Column(nullable = false, length = 9, columnDefinition = "CHAR(9)")
    private String birthday;

    @Column(name = "phone_num", length = 11, columnDefinition = "CHAR(11)")
    private String phoneNum;

    @Column(nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String gender;

    @Column(length = 10, columnDefinition = "CHAR(10)")
    private String account;

    @Column(name = "family_code", length = 20)
    private String famCode;

    @Column(length = 8, columnDefinition = "NUMBER(8)")
    private int balance;

    @Column(length = 3, columnDefinition = "NUMBER(3)")
    private int achieve;

    @Column(name = "cheerup_msg", length = 100)
    private String cheerUpMsg;

    @Column(name = "is_parents", length = 1, columnDefinition = "CHAR(1)")
    private String isParent;


    @OneToMany(mappedBy = "sendUser", cascade = CascadeType.ALL)
    private List<History> sentHistories;

    @OneToMany(mappedBy = "receivedUser", cascade = CascadeType.ALL)
    private List<History> receivedHistories;

    @OneToMany(mappedBy = "autoSendUser", cascade = CascadeType.ALL)
    private List<AutoDebit> autoSentDebits;

    @OneToMany(mappedBy = "autoReceivedUser", cascade = CascadeType.ALL)
    private List<AutoDebit> autoReceivedDebits;

    @OneToMany(mappedBy = "solvedUserId", cascade = CascadeType.ALL)
    private List<UserMission> solvedMission;

}