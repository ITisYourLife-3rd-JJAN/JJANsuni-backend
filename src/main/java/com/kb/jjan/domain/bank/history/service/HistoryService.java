package com.kb.jjan.domain.bank.history.service;

import com.kb.jjan.domain.bank.history.History;
import com.kb.jjan.domain.bank.history.dto.HistoryRequest;
import com.kb.jjan.domain.bank.history.repository.HistoryRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.UserRequest;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    public void registerDebit(HistoryRequest historyRequest) throws Exception {
        User sendUser = userRepository.getReferenceById(historyRequest.getSendUserId());
        User receivedUser = userRepository.getReferenceById(historyRequest.getReceivedUserId());

        History history = historyRequest.toEntity(sendUser, receivedUser);
        historyRepository.save(history);
    }

}
