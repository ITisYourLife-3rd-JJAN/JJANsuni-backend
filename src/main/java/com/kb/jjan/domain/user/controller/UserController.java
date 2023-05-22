package com.kb.jjan.domain.user.controller;


import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.*;
import com.kb.jjan.domain.user.exception.NotFoundFamCode;
import com.kb.jjan.domain.user.service.FamilyCodeService;
import com.kb.jjan.domain.user.service.UserService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.kb.jjan.global.result.ResultCode.USER_GENERATION_SUCCESS;
import static com.kb.jjan.global.result.ResultCode.USER_REGISTRATION_SUCCESS;
import static com.kb.jjan.global.result.ResultCode.USER_UPDATE_BALANCE_SUCCESS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kb.jjan.global.result.ResultCode.*;

@RequestMapping("api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FamilyCodeService familyCodeService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/join")
    public ResponseEntity<ResultResponse> registerUser(@RequestBody UserRequest userRequest)
            throws Exception {
        userService.registerUser(userRequest);
        ResultResponse<User> resultResponse = new ResultResponse<>(USER_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/debit")
    public ResponseEntity<ResultResponse<Map<String, Integer>>> updateUserToDeposit(@RequestBody UserUpdatePriceRequest userUpdatePriceRequest)
            throws Exception {
        int balance = userService.updateUserToDeposit(userUpdatePriceRequest);

        Map<String, Integer> item = new HashMap<>();
        item.put("balance", balance);

        ResultResponse<Map<String, Integer>> resultResponse = new ResultResponse<Map<String, Integer>>( USER_UPDATE_BALANCE_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/family-code")
    public ResponseEntity<ResultResponse> generateFamilyCode() {
        String famCode = familyCodeService.generateFamilyCode();

        Map<String, String> item = new HashMap<>();
        item.put("famCode", famCode);

        ResultResponse<String> resultResponse = new ResultResponse<>(USER_GENERATION_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/{userId}")
          public ResponseEntity<ResultResponse> findByIdUser(@PathVariable("userId") long userId)
              throws Exception {
          User user = userService.findUserById(userId);
          ResultResponse<User> resultResponse = new ResultResponse<>(USER_FINDBYIDUSER_SUCCESS, user);
          return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
      }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<ResultResponse> login(@RequestBody UserLoginRequest userLoginRequest)
              throws Exception {
          User user = userService.login(userLoginRequest);
          ResultResponse<User> resultResponse = new ResultResponse<>(USER_LOGIN_SUCCESS, user);
          return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
   }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/email-check")
    public ResponseEntity<ResultResponse> checkEmailExist(@RequestBody UserEmailExistRequest userEmailExistRequest)
            throws Exception {
        userService.checkEmailExist(userEmailExistRequest);
        ResultResponse<?> resultResponse = new ResultResponse<>(USER_EXISTBYEMAIL_SUCCESS); // 중복되는 이메일이 없을 경우 성공했다는 response
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/family-list/{userId}")
    public ResponseEntity<ResultResponse> showFamilyList(@PathVariable("userId") long userId)
            throws Exception{
        List<UserFamilyResponse> userfamilyResponses = userService.showFamilyList(userId);

        ResultResponse<List<User>> resultResponse = new ResultResponse<>(USER_FINDBYFAMCODE_SUCCESS, userfamilyResponses);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
  
    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/info-edit")
    public ResponseEntity<ResultResponse> updatePhoneNum(@RequestBody UserUpdatePhoneNumRequest userUpdatePhoneNumRequest)
            throws Exception {
        userService.updateUserPhoneNum(userUpdatePhoneNumRequest);
        ResultResponse<?> resultResponse = new ResultResponse<>(USER_UPDATE_PHONE_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/check/{famCode}")
    public ResponseEntity<ResultResponse> famCodeExist(@PathVariable("famCode") String famCode)
            throws Exception{
        boolean isCodeExists = familyCodeService.isCodeExists(famCode);
        if (isCodeExists) {
            ResultResponse<?> resultResponse = new ResultResponse<>(USER_FINDBYFAMCODE_SUCCESS);
            return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
        } else throw new NotFoundFamCode();
    }

}