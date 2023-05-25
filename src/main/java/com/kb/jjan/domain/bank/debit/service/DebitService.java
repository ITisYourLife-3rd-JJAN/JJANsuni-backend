package com.kb.jjan.domain.bank.debit.service;

import com.google.zxing.common.BitMatrix;
import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.exception.NoDebitHistory;
import com.kb.jjan.domain.bank.debit.exception.OverBalanceCode;
import com.kb.jjan.domain.bank.debit.repository.DebitRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.bank.debit.dto.UserDebitResponse;
import com.kb.jjan.domain.user.repository.UserRepository;
import com.kb.jjan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.*;

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
        if (dealMsg == null || dealMsg.isEmpty()) {
            dealMsg = sendUser.getName()+"님이송금";
        }

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

    public List<UserDebitResponse> showDebitHistory(long userId) throws Exception {
        List<Debit> debitTransactions = debitRepository.findBySendUserUserIdOrReceivedUserUserId(userId, userId);
        List<UserDebitResponse> userDebitResponses = new ArrayList<>();

        for (Debit debit : debitTransactions) {
            UserDebitResponse userDebitResponse = new UserDebitResponse(userRepository.getReferenceById(userId), debit);
            userDebitResponses.add(userDebitResponse);
        }
        if(userDebitResponses.isEmpty()){
            throw new NoDebitHistory();
        }
        return userDebitResponses;
    }

    public BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }
}