package com.kb.jjan.domain.bank.direct.service;


import com.kb.jjan.domain.bank.debit.exception.OverBalanceCode;
import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectDeleteRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectUpdateRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectUserDTO;
import com.kb.jjan.domain.bank.direct.exception.NoDirectDebit;
import com.kb.jjan.domain.bank.direct.repository.DirectRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import com.kb.jjan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
    @Scheduled(cron = "0 0 10 * * *")
    public void directDebit() throws Exception {
        System.out.println("=====제발실행=====");

        for (int cycle = 1; cycle < 4; cycle++) {
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

    @Transactional
    public List<DirectUserDTO> showDirect(long userId) throws Exception {
//        System.out.println("=====잘들어가니?=====");
        List<Direct> directList = directRepository.findByAutoSendUser(userId);
//        System.out.println(directList);
        List<DirectUserDTO> userDTOList = new ArrayList<>();

        for (Direct direct : directList) {
            DirectUserDTO directUserDTO = new DirectUserDTO(direct);
            userDTOList.add(directUserDTO);
        }
        if (userDTOList.isEmpty()) {
            throw new NoDirectDebit();
        }
        return userDTOList;
    }

    @Transactional
    public int updateDirect(DirectUpdateRequest directUpdateRequest) throws Exception {

        long autoSendUser = directUpdateRequest.getAutoSendUserId();
        long autoReceivedUser = directUpdateRequest.getAutoReceivedUserId();

        int price = directUpdateRequest.getPrice();
        String debitMsg = directUpdateRequest.getDebitMsg();
        int debitDate = directUpdateRequest.getDebitDate();
        int debitCycle = directUpdateRequest.getDebitCycle();

        return directRepository.updateDirect(autoSendUser, autoReceivedUser, price, debitMsg, debitDate, debitCycle);
    }

    @Transactional
    public int deleteDirect(DirectDeleteRequest directDeleteRequest) throws Exception {

        long autoSendUser = directDeleteRequest.getAutoSendUserId();
        long autoReceivedUser = directDeleteRequest.getAutoReceivedUserId();

        return directRepository.deleteDirect(autoSendUser, autoReceivedUser);
    }

}