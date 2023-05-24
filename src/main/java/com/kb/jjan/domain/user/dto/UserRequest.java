package com.kb.jjan.domain.user.dto;


import com.kb.jjan.domain.user.User;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserRequest {
    private String email;
    private String password;
    private String name;
    private String phoneNum;
    private String gender;
    private String birthday;
    private String famCode;
    private int balance;
    private String isParent;


    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .balance(balance)
                .gender(gender)
                .famCode(famCode)
                .isParent(isParent)
                .birthday(birthday)
                .phoneNum(phoneNum)
                .build();
    }
}
