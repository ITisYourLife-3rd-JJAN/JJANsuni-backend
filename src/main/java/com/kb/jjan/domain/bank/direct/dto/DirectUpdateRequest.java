package com.kb.jjan.domain.bank.direct.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectUpdateRequest {
    private Long autoSendUserId;
    private Long autoReceivedUserId;
    private int price;
    private String debitMsg;
    private int debitDate;
    private int debitCycle;
}
