package com.kb.jjan.domain.bank.debit.service;

import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.repository.DebitRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebitService {
    private final DebitRepository debitRepository;
    private final UserRepository userRepository;

    public void registerDebit(DebitRequest debitRequest) throws Exception {
        User sendUser = userRepository.getReferenceById(debitRequest.getSendUserId());
        User receivedUser = userRepository.getReferenceById(debitRequest.getReceivedUserId());

        Debit debit = debitRequest.toEntity(sendUser, receivedUser);
        debitRepository.save(debit);
    }
}
