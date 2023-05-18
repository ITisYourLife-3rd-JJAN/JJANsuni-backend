package com.kb.jjan.domain.bank.debit.controller;


import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.service.DebitService;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.dto.UserUpdatePriceRequest;
import com.kb.jjan.domain.user.service.UserService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.kb.jjan.global.result.ResultCode.*;

@RequestMapping("api/v1/debits")
@RestController
@RequiredArgsConstructor
public class DebitController {

    private final DebitService debitService;
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<ResultResponse> registerDebit(@RequestBody DebitRequest debitRequest)
            throws Exception {
        long sendUserId = debitService.registerDebit(debitRequest);
        Map<String, Long> item = new HashMap<>();
        item.put("sendUserId", sendUserId);

        ResultResponse<Long> resultResponse = new ResultResponse<>(DEBIT_REGISTRATION_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<ResultResponse> showUserDebit(@PathVariable("userId") long userId)
            throws Exception{
        List<Debit> debitList = debitService.showDebitHistory(userId);
        ResultResponse<List<Debit>> resultResponse = new ResultResponse<>(DEBIT_HISTORY_FINDBYIDUSER_SUCCESS,debitList);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse); // 있으면 list 값 담아서 보내줘야함


    }

    @PatchMapping("/charge")
    public ResponseEntity<ResultResponse> chargeBalance(@RequestBody UserUpdatePriceRequest userUpdatePriceRequest)
            throws Exception {
        int balance = userService.updateUser(userUpdatePriceRequest);

        Map<String, Integer> item = new HashMap<>();
        item.put("balance", balance);

        ResultResponse<Integer> resultResponse = new ResultResponse<>( DEBIT_JJANPAY_CHARGE_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
}
