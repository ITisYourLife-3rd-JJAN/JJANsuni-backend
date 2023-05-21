package com.kb.jjan.domain.bank.direct.controller;


import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.service.DirectService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kb.jjan.global.result.ResultCode.DIRECT_REGISTRATION_SUCCESS;

@RequestMapping("api/v1/directs")
@RestController
@RequiredArgsConstructor
public class DirectController {
    private final DirectService directService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> registerDirect(@RequestBody DirectRequest directRequest)
            throws Exception {

        long autoSendUserId = directService.registerDirect(directRequest);
        Map<String, Long> item = new HashMap<>();
        item.put("autoSendUserId", autoSendUserId);

        ResultResponse<Long> resultResponse = new ResultResponse<>(DIRECT_REGISTRATION_SUCCESS, item);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @GetMapping("/test")
    public List<Direct> dire() throws Exception{
        List<Direct> list = directService.dire();
        return list;
    }
}
