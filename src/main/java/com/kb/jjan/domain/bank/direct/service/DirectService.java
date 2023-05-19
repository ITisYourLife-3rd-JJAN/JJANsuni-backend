package com.kb.jjan.domain.bank.direct.service;


import com.kb.jjan.domain.bank.debit.exception.OverBalanceCode;
import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.repository.DirectRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DirectService {
    private final DirectRepository directRepository;
    private final UserRepository userRepository;

    private DirectRequest scheduledDirectRequest;

    @Value("${scheduled.job.cron}")
    private String cronExpression;

    public long registerDirect(DirectRequest directRequest) throws Exception {

        scheduledDirectRequest = directRequest;
        User autoSendUser = userRepository.getReferenceById(directRequest.getAutoSendUserId());

        int debitDate = directRequest.getDebitDate();
        int debitCycle = directRequest.getDebitCycle();


        if (debitCycle == 1) {
            cronExpression = "0 0 9 * * ?";
        } else if (debitCycle == 2) {
            if (debitDate >= 51 && debitDate <= 57) {
                cronExpression = "0 0 0 ? * " + (debitDate - 50);
            }
        } else if (debitCycle == 3) {
            if (debitDate == 99) {
                // 말일에 해당하는 크론 작성
                cronExpression = "0 0 0 L * ?";
            } else if (debitDate >= 1 && debitDate <= 31) {
                // 매월 크론 작성
                cronExpression = "0 0 0 " + debitDate + " * ?";
            }

        }


//        long autoSendUser = directDebit(directRequest);

        return autoSendUser.getUserId();
    }
    @Transactional
    @Scheduled(cron = "${scheduled.job.cron}")
    public void directDebit() throws Exception {
        DirectRequest directRequest = scheduledDirectRequest;
        User autoSendUser = userRepository.getReferenceById(directRequest.getAutoSendUserId());
        User autoReceivedUser = userRepository.getReferenceById(directRequest.getAutoReceivedUserId());

        int price = directRequest.getPrice();
        String debitMsg = directRequest.getDebitMsg();
        int debitDate = directRequest.getDebitDate();
        int debitCycle = directRequest.getDebitCycle();

        if (autoSendUser.getBalance() < price) {
            throw new OverBalanceCode();
        } else {
            int beforeBalance = autoSendUser.getBalance();
            int afterBalance = beforeBalance - price;
            autoSendUser.setBalance(afterBalance);
            beforeBalance = autoReceivedUser.getBalance();
            afterBalance = beforeBalance + price;
            autoReceivedUser.setBalance(afterBalance);

            Direct direct = directRequest.toEntity(autoSendUser, autoReceivedUser, price, debitMsg, debitDate, debitCycle);
            directRepository.save(direct);

        }


    }

}