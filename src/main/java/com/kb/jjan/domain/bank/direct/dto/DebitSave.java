package com.kb.jjan.domain.bank.direct.dto;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.user.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DebitSave {
    private Long sendUserId;
    private Long receivedUserId;
    private int price;
    private String dealMsg;

    public static Debit toEntity(User autoSendUser, User autoReceivedUser, int price, String dealMsg) {
        return Debit.builder()
                .sendUser(autoSendUser)
                .receivedUser(autoReceivedUser)
                .price(price)
                .dealMsg(dealMsg)
                .build();
    }
}
