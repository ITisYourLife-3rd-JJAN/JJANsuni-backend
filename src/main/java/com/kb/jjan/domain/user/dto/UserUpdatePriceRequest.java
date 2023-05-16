package com.kb.jjan.domain.user.dto;


import com.kb.jjan.domain.user.User;
import com.sun.istack.NotNull;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdatePriceRequest {

    public User findUser;
    private Long userId;
    private int price;

}
