package com.kb.jjan.domain.mission.userMission.controller;


import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionRequest;
import com.kb.jjan.domain.mission.userMission.service.UserMissionService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kb.jjan.global.result.ResultCode.USER_REGISTRATION_SUCCESS;

@RequestMapping("api/v1/missions")
@RestController
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PostMapping("/create-history")
    public ResponseEntity<ResultResponse> registerUser(@RequestBody UserMissionRequest userMissionRequest)
            throws Exception {
        userMissionService.registerUserMissionHistory(userMissionRequest);
        ResultResponse<UserMission> resultResponse = new ResultResponse<>(USER_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
}
