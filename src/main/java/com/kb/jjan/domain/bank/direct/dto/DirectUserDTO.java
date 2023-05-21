package com.kb.jjan.domain.bank.direct.dto;

import com.kb.jjan.domain.bank.direct.Direct;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectUserDTO {
    private long autoSendUserId;
    private long autoReceivedUserId;
    private int price;
    private String debitMsg;
    private int debitDate;
    private int debitCycle;

    public DirectUserDTO(Direct direct) {
        this.autoSendUserId = direct.getAutoSendUser().getUserId();
        this.autoReceivedUserId = direct.getAutoReceivedUser().getUserId();
        this.price = direct.getPrice();
        this.debitMsg = direct.getDebitMsg();
        this.debitDate = direct.getDebitDate();
        this.debitCycle = direct.getDebitCycle();

    }
}

