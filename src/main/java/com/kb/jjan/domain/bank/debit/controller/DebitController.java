package com.kb.jjan.domain.bank.debit.controller;


import com.kb.jjan.domain.bank.debit.Debit;
import com.kb.jjan.domain.bank.debit.dto.DebitRequest;
import com.kb.jjan.domain.bank.debit.service.DebitService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kb.jjan.global.result.ResultCode.DEBIT_REGISTRATION_SUCCESS;

@RequestMapping("api/v1/debits")
@RestController
@RequiredArgsConstructor
public class DebitController {

    private final DebitService debitService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<ResultResponse> registerDebit(@RequestBody DebitRequest debitRequest)
            throws Exception {
        debitService.registerDebit(debitRequest);
        ResultResponse<Debit> resultResponse = new ResultResponse<>(DEBIT_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
}
