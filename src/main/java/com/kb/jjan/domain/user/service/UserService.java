package com.kb.jjan.domain.user.service;


import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.UserRequest;
import com.kb.jjan.domain.user.dto.UserUpdatePriceRequest;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(UserRequest userRequest) throws Exception {
        User user = userRequest.toEntity();
        userRepository.save(user);
    }

    @Transactional
    public int updateUser(UserUpdatePriceRequest userUpdatePriceRequest) {
        User findUser = userRepository.getReferenceById(userUpdatePriceRequest.getUserId());
        if(findUser != null) {
            int beforeBalance = findUser.getBalance();
            int afterBalance = beforeBalance - userUpdatePriceRequest.getPrice();
            findUser.setBalance(afterBalance);
            return afterBalance;
        }
        return 0;
    }




}
