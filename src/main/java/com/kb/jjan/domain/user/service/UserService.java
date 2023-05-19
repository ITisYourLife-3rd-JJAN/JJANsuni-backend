package com.kb.jjan.domain.user.service;


import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.UserLoginRequest;
import com.kb.jjan.domain.user.dto.UserRequest;
import com.kb.jjan.domain.user.exception.EmailExist;
import com.kb.jjan.domain.user.exception.NotFoundFamCode;
import com.kb.jjan.domain.user.exception.NotFoundUser;
import com.kb.jjan.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyCodeService familyCodeService;

    public void registerUser(UserRequest userRequest) throws Exception {
        User user = userRequest.toEntity();
        if (Objects.equals(user.getIsParent(), "F")) {
            boolean check = familyCodeService.isCodeExists(user.getFamCode());
            if (check) {
                userRepository.save(user);
            } else throw new NotFoundFamCode();
        } userRepository.save(user);
    }

    @Transactional
    public int updateUser(long userId,  int price) throws Exception {
        User findUser = findUserById(userId);

        int afterBalance = findUser.getBalance() + price;
        findUser.setBalance(afterBalance);
        return afterBalance;
    }

    @Transactional
    public void updateUser(long userId) throws Exception {
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

    public void checkEmailExist(String email) throws Exception {
        boolean check = isEmailExist(email);
        if (check) throw new EmailExist();
    }
}
