package com.kb.jjan.domain.bank.direct.controller;


import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.service.DirectService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kb.jjan.global.result.ResultCode.DIRECT_REGISTRATION_SUCCESS;

@RequestMapping("api/v1/directs")
@RestController
@RequiredArgsConstructor
public class DirectController {
    private final DirectService directService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> registerDirect(@RequestBody DirectRequest directRequest)
            throws Exception {
        directService.registerDirect(directRequest);
        ResultResponse<Direct> resultResponse = new ResultResponse<>(DIRECT_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
}
