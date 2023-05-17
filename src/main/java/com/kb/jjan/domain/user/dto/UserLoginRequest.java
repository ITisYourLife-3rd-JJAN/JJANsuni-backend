package com.kb.jjan.domain.user.dto;

import com.kb.jjan.domain.user.User;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserLoginRequest {
    private String email;
    private String password;
}
