package com.kb.jjan.domain.bank.direct.service;


import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.repository.DirectRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectService {
    private final DirectRepository directRepository;
    private final UserRepository userRepository;

    public void registerDirect(DirectRequest directRequest) {
        User autoSendUser = userRepository.getReferenceById(directRequest.getAutoSendUserId());
        User autoReceivedUser = userRepository.getReferenceById(directRequest.getAutoReceivedUserId());

        Direct direct = directRequest.toEntity(autoSendUser, autoReceivedUser);
//        DirectId directId = new DirectId(autoSendUser, autoReceivedUser);
        directRepository.save(direct);
    }

}
