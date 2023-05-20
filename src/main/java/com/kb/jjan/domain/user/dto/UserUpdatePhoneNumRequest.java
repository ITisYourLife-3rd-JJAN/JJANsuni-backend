package com.kb.jjan.domain.user.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserUpdatePhoneNumRequest {
    private long userId;
    private String phoneNum;
}
