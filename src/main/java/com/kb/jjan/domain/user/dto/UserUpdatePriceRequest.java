package com.kb.jjan.domain.user.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdatePriceRequest {

    private Long userId;
    private int price;

}
