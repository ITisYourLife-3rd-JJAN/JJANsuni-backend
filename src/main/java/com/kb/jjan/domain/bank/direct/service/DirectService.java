package com.kb.jjan.domain.bank.direct.service;


import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.exception.OverBalanceCode;
import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectUserDTO;
import com.kb.jjan.domain.bank.direct.repository.DirectRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import com.kb.jjan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectService {
    private final DirectRepository directRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    LocalDate localDate = LocalDate.now();

    @Transactional
    public long registerDirect(DirectRequest directRequest) throws Exception {

        User autoSendUser = userRepository.getReferenceById(directRequest.getAutoSendUserId());
        User autoReceivedUser = userRepository.getReferenceById(directRequest.getAutoReceivedUserId());

        int price = directRequest.getPrice();
        String debitMsg = directRequest.getDebitMsg();
        int debitDate = directRequest.getDebitDate();
        int debitCycle = directRequest.getDebitCycle();

        Direct direct = directRequest.toEntity(autoSendUser, autoReceivedUser, price, debitMsg, debitDate, debitCycle);
        directRepository.save(direct);
        return autoSendUser.getUserId();



    }
    @Transactional
    @Scheduled(cron = "0 00 16 * * *")
    public void directDebit() throws Exception {
        System.out.println("=====제발실행=====");

        for (int cycle = 1; cycle < 4; cycle++) {
//        int cycle = 3;
//           List<DirectUserDTO> directList = directRepository.findUsersByDebitCycle(cycle);
            List<Direct> directList = directRepository.findUsersByDebitCycle(cycle);
            List<DirectUserDTO> userList = new ArrayList<>();
            for (Direct direct : directList) {
                DirectUserDTO directUserDTO = new DirectUserDTO(direct);
                userList.add(directUserDTO);
            }
            System.out.println("list!!!!!!!!!!!!!" + userList.toString());

            for (DirectUserDTO user : userList) {


                User autoSendUser = userService.findUserById(user.getAutoSendUserId());
                User autoReceivedUser = userService.findUserById(user.getAutoReceivedUserId());
//               User autoSendUser = direct.getAutoSendUser();
//               User autoReceivedUser = direct.getAutoReceivedUser();
                int price = user.getPrice();
                int debitCycle = user.getDebitCycle();
                int debitDate = user.getDebitDate();
                System.out.println(price);
                System.out.println(debitDate);
                System.out.println(debitCycle);

                if (debitCycle == 1) {
                    System.out.println("=====1실행=====");
                    if (autoSendUser.getBalance() < price) {
                        throw new OverBalanceCode();
                    } else {
                        int beforeBalance = autoSendUser.getBalance();
                        int afterBalance = beforeBalance - price;
                        autoSendUser.setBalance(afterBalance);
                        beforeBalance = autoReceivedUser.getBalance();
                        afterBalance = beforeBalance + price;
                        autoReceivedUser.setBalance(afterBalance);
                    }
                } else if (debitCycle == 2) {
                    System.out.println("=====2실행=====");
                    int week = localDate.getDayOfWeek().getValue();
                    if (week == debitDate) {
                        if (autoSendUser.getBalance() < price) {
                            throw new OverBalanceCode();
                        } else {
                            int beforeBalance = autoSendUser.getBalance();
                            int afterBalance = beforeBalance - price;
                            autoSendUser.setBalance(afterBalance);
                            beforeBalance = autoReceivedUser.getBalance();
                            afterBalance = beforeBalance + price;
                            autoReceivedUser.setBalance(afterBalance);
                        }
                    }
                } else if (debitCycle == 3) {
                    System.out.println("=====3실행=====");
                    int day = localDate.getDayOfMonth();
                    if (day == debitDate) {
                        if (autoSendUser.getBalance() < price) {
                            throw new OverBalanceCode();
                        } else {
                            int beforeBalance = autoSendUser.getBalance();
                            int afterBalance = beforeBalance - price;
                            autoSendUser.setBalance(afterBalance);
                            beforeBalance = autoReceivedUser.getBalance();
                            afterBalance = beforeBalance + price;
                            autoReceivedUser.setBalance(afterBalance);
                        }
                    }
                }
            }
        }
    }

    //    @Scheduled(fixedDelay = 1000)
    public List<Direct> dire() throws Exception {
        int cycle = 3;
        List<Direct> directList = directRepository.findByDebitCycle(cycle);
        return directList;
//System.out.println("제발되라구");

    }

}