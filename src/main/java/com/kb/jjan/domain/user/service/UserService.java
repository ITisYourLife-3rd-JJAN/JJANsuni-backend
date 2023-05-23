package com.kb.jjan.domain.user.service;

import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.*;
import com.kb.jjan.domain.user.exception.EmailExist;
import com.kb.jjan.domain.user.exception.NotFoundFamCode;
import com.kb.jjan.domain.user.exception.NotFoundUser;
import com.kb.jjan.domain.user.exception.NotFoundFamilyList;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyCodeService familyCodeService;

    public User registerUser(UserRequest userRequest) throws Exception {
        User user = userRequest.toEntity();
        if (Objects.equals(user.getIsParent(), "F")) {
            boolean check = familyCodeService.isCodeExists(user.getFamCode());
            if (check) {
                userRepository.save(user);
            } else throw new NotFoundFamCode();
        } userRepository.save(user);

        return user;
    }

    @Transactional
    public int updateUserToDeposit(long userId,  int price) throws Exception {
        User findUser = findUserById(userId);

        int afterBalance = findUser.getBalance() + price;
        findUser.setBalance(afterBalance);
        return afterBalance;
    }

    @Transactional
    public int updateUserToDeposit(UserUpdatePriceRequest userUpdatePriceRequest) throws Exception {
        User findUser = findUserById(userUpdatePriceRequest.getUserId());

        int afterBalance = findUser.getBalance() + userUpdatePriceRequest.getPrice();
        findUser.setBalance(afterBalance);
        return afterBalance;
    }

    @Transactional
    public void updateUserToAchieve(long userId) throws Exception {
        User findUser = findUserById(userId);
        findUser.setAchieve(findUser.getAchieve() + 1);
    }

    public User findUserById(long userId) throws Exception{
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) throw new NotFoundUser();
        return user;
    }

    public User login(UserLoginRequest userLoginRequest) throws Exception{
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        return userRepository.login(email, password);
    }

    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public void checkEmailExist(UserEmailExistRequest userEmailExistRequest) throws Exception {
        String email = userEmailExistRequest.getEmail();
        boolean check = isEmailExist(email);
        if (check) throw new EmailExist();
    }

    public List<UserFamilyResponse> showFamilyList(long userId) throws Exception{
        String famCode = userRepository.getReferenceById(userId).getFamCode();

        List<User> familyList = userRepository.findByFamCode(famCode, userId);

        List<UserFamilyResponse> userFamilyResponses = new ArrayList<>();

        for (User user : familyList) {
            UserFamilyResponse userFamilyResponse = new UserFamilyResponse(userRepository.getReferenceById(userId), user);
            userFamilyResponses.add(userFamilyResponse);
        }
        if(userFamilyResponses.isEmpty()){
            throw new NotFoundFamilyList();
        }
        return userFamilyResponses;
    }

    @Transactional
    public int updateUserPhoneNum(UserUpdatePhoneNumRequest userUpdatePhoneNumRequest) throws Exception{
        String phoneNum = userUpdatePhoneNumRequest.getPhoneNum();
        long userId = userUpdatePhoneNumRequest.getUserId();
        return userRepository.updatePhoneNum(phoneNum, userId);
    }

}
