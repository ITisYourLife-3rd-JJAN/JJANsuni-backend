package com.kb.jjan.domain.bank.direct.dto;


import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.user.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectRequest {
    private Long autoSendUserId;
    private Long autoReceivedUserId;
    private int price;
    private String debitMsg;
    private int debitDate;
    private int debitCycle;

    public Direct toEntity(User autoSendUser, User autoReceivedUser, int price, String debitMsg, int debitDate, int debitCycle) {
        return Direct.builder()
                .autoSendUser(autoSendUser)
                .autoReceivedUser(autoReceivedUser)
                .price(price)
                .debitMsg(debitMsg)
                .debitDate(debitDate)
                .debitCycle(debitCycle)
                .build();
    }
}
