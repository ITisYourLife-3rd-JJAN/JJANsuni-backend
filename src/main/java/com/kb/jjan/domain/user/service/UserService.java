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
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyCodeService familyCodeService;

    public void registerUser(UserRequest userRequest) throws Exception {
        User user = userRequest.toEntity();
        boolean isParent = Objects.equals(user.getIsParent(), "F");

        if (!isParent && familyCodeService.isCodeExists(user.getFamCode())) {
            throw new NotFoundFamCode();
        }

        String account = generateRandomAccount(user.getIsParent());
        user.setAccount(account);
        userRepository.save(user);
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

    @Transactional
    public void updateCheerUpMsg(CheerUpMsgRequest cheerUpMsgRequest) throws Exception {
        userRepository.updateCheerUpMsg(cheerUpMsgRequest.getUserId(), cheerUpMsgRequest.getCheerUpMsg());
    }


    public String generateRandomAccount(String isParent) {
        StringBuilder numberBuilder = new StringBuilder();
        Random random = new Random();

        if (isParent.equals("T")) numberBuilder.append("48607");
        else if (isParent.equals("F")) numberBuilder.append("48603");

        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(10);  // 0부터 9까지의 랜덤한 숫자 생성
            numberBuilder.append(randomNumber);
        }

        numberBuilder.append("1");
        String number = numberBuilder.toString();

        if (userRepository.existsByAccount(number)) return generateRandomAccount(isParent);
        return number;
    }

}
