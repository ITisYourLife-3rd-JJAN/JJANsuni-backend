package com.kb.jjan.domain.bank.debit.dto;


import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.user.User;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserDebitResponse {
    private long debitId;
    private int price;
    private String dealMsg;
    private long sendUserId;
    private String sendUserName;
    private long receivedUserId;
    private String receivedUserName;
    private LocalDateTime isCreated; //사용자의 계좌
    private int balance; //사용자의 잔액

    public UserDebitResponse(User user, Debit debit) {
        this.debitId = debit.getDebitId();
        this.price = debit.getPrice();
        this.dealMsg = debit.getDealMsg();
        this.sendUserId = debit.getSendUser().getUserId();
        this.receivedUserId=debit.getReceivedUser().getUserId();
        this.receivedUserName = debit.getReceivedUser().getName();
        this.sendUserName = debit.getSendUser().getName();
        this.isCreated = debit.getCreateAt();
        this.balance = user.getBalance();
    }
}
