package com.kb.jjan.domain.bank.debit.service;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.exception.OverBalanceCode;
import com.kb.jjan.domain.bank.debit.repository.DebitRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import com.kb.jjan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebitService {
    private final DebitRepository debitRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public long registerDebit(DebitRequest debitRequest) throws Exception {
        User sendUser = userRepository.getReferenceById(debitRequest.getSendUserId()); //송금자
        User receivedUser = userRepository.getReferenceById(debitRequest.getReceivedUserId()); //수신자

        int price = debitRequest.getPrice(); // 송금액
        String dealMsg= debitRequest.getDealMsg(); // 이체 메세지

        if(sendUser.getBalance() < price){ //송금자의 잔액이 보내는 돈보다 적다
            throw new OverBalanceCode();
        }
        else{
            int beforeBalance = sendUser.getBalance();
            int afterBalance = beforeBalance-price;
            sendUser.setBalance(afterBalance);
            beforeBalance = receivedUser.getBalance();
            afterBalance = beforeBalance+price;
            receivedUser.setBalance(afterBalance);

            Debit debit = debitRequest.toEntity(sendUser, receivedUser, price, dealMsg);
            debitRepository.save(debit);
            return sendUser.getUserId();
        }
    }
}