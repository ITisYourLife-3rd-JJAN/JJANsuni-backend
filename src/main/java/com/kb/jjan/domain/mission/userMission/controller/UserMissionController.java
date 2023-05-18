package com.kb.jjan.domain.mission.userMission.controller;


import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionRequest;
import com.kb.jjan.domain.mission.userMission.service.UserMissionService;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kb.jjan.global.result.ResultCode.*;

@RequestMapping("api/v1/missions")
@RestController
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> registerUserMission(@RequestBody UserMissionRequest userMissionRequest)
            throws Exception {
        userMissionService.registerUserMission(userMissionRequest);
        ResultResponse<UserMission> resultResponse = new ResultResponse<>(USER_MISSION_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @GetMapping("/mission/{userId}")
    public ResponseEntity<ResultResponse> getMissionStatus(@PathVariable("userId") long userId)
            throws Exception {
        List<UserMission> userMissionList = userMissionService.getMissionStatus(userId);
        ResultResponse<List<UserMission>> resultResponse = new ResultResponse<>(GET_USER_MISSION_SUCCESS, userMissionList);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }
}
