package com.kb.jjan.domain.bank.history.controller;


import com.kb.jjan.domain.bank.history.History;
import com.kb.jjan.domain.bank.history.dto.HistoryRequest;
import com.kb.jjan.domain.bank.history.service.HistoryService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kb.jjan.global.result.ResultCode.DEBIT_REGISTRATION_SUCCESS;

@RequestMapping("api/v1/debits")
@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping("/register-debit")
    public ResponseEntity<ResultResponse> registerDebit(@RequestBody HistoryRequest historyRequest)
            throws Exception {
        historyService.registerDebit(historyRequest);
        ResultResponse<History> resultResponse = new ResultResponse<>(DEBIT_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
}
