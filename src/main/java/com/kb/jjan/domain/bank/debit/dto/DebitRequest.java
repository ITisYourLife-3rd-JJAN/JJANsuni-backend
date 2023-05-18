package com.kb.jjan.domain.bank.debit.dto;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.user.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DebitRequest {
    private Long sendUserId;
    private Long receivedUserId;
    private int price;
    private String dealMsg;

    public Debit toEntity(User sendUser, User receivedUser, int price, String dealMsg) {
        return Debit.builder()
                .sendUser(sendUser)
                .receivedUser(receivedUser)
                .price(price)
                .dealMsg(dealMsg)
                .build();
    }
}