package com.kb.jjan.domain.mission.mission.controller;

import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.dto.MissionQuizRequest;
import com.kb.jjan.domain.mission.mission.dto.MissionVodRequest;
import com.kb.jjan.domain.mission.mission.service.MissionService;
import com.kb.jjan.global.result.ResultCode;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/admin")
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/register-quiz")
    public ResponseEntity<ResultResponse> registerQuiz(@RequestBody MissionQuizRequest missionQuizRequest){
        missionService.registerQuiz(missionQuizRequest);
        ResultResponse<Mission> resultResponse = new ResultResponse<>(ResultCode.QUIZ_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @PostMapping("/register-vod")
    public ResponseEntity<ResultResponse> registerVod(@RequestBody MissionVodRequest missionVodRequest){
        missionService.registerVod(missionVodRequest);
        ResultResponse<Mission> resultResponse = new ResultResponse<>(ResultCode.VOD_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

}
