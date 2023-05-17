package com.kb.jjan.domain.bank.debit.controller;


import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.service.DebitService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.kb.jjan.global.result.ResultCode.DEBIT_REGISTRATION_SUCCESS;
import static com.kb.jjan.global.result.ResultCode.USER_UPDATE_BALANCE_SUCCESS;

@RequestMapping("api/v1/debits")
@RestController
@RequiredArgsConstructor
public class DebitController {

    private final DebitService debitService;

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
}
