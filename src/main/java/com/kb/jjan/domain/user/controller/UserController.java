package com.kb.jjan.domain.user.controller;

import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.UserRequest;
import com.kb.jjan.domain.user.dto.UserUpdatePriceRequest;
import com.kb.jjan.domain.user.service.FamilyCodeService;
import com.kb.jjan.domain.user.service.UserService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


import static com.kb.jjan.global.result.ResultCode.USER_GENERATION_SUCCESS;
import static com.kb.jjan.global.result.ResultCode.USER_REGISTRATION_SUCCESS;
import static com.kb.jjan.global.result.ResultCode.USER_UPDATE_BALANCE_SUCCESS;
import static com.kb.jjan.global.result.ResultCode.USER_GENERATION_SUCCESS;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<ResultResponse> updateUser(@RequestBody UserUpdatePriceRequest userUpdatePriceRequest)
            throws Exception {
        int balance = userService.updateUser(userUpdatePriceRequest);

        Map<String, Integer> item = new HashMap<>();
        item.put("balance", balance);

        ResultResponse<Integer> resultResponse = new ResultResponse<>( USER_UPDATE_BALANCE_SUCCESS, item);
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

}
