package com.kb.jjan.domain.bank.direct.controller;


import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectDeleteRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectUpdateRequest;
import com.kb.jjan.domain.bank.direct.dto.DirectUserDTO;
import com.kb.jjan.domain.bank.direct.service.DirectService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kb.jjan.global.result.ResultCode.*;

@RequestMapping("api/v1/directs")
@RestController
@RequiredArgsConstructor
public class DirectController {
    private final DirectService directService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<ResultResponse> registerDirect(@RequestBody DirectRequest directRequest)
            throws Exception {

        long autoSendUserId = directService.registerDirect(directRequest);

        ResultResponse<Long> resultResponse = new ResultResponse<>(DIRECT_REGISTRATION_SUCCESS, autoSendUserId);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<ResultResponse> showDirect(@PathVariable("userId") long userId)
        throws Exception {
        List<DirectUserDTO> dtoList = directService.showDirect(userId);
        ResultResponse<List<Direct>> resultResponse = new ResultResponse<>(DIRECT_FINDBYUSERID_SUCCESS, dtoList);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping("/{userId}")
    public ResponseEntity<ResultResponse> updateDirect(@RequestBody DirectUpdateRequest directUpdateRequest)
        throws Exception {

        directService.updateDirect(directUpdateRequest);
        ResultResponse<?> resultResponse = new ResultResponse<>(DIRECT_UPDATE_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResultResponse> deleteDirect(@RequestBody DirectDeleteRequest directDeleteRequest)
        throws Exception {

        directService.deleteDirect(directDeleteRequest);
        ResultResponse<?> resultResponse = new ResultResponse<>(DIRECT_DELETE_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

}
