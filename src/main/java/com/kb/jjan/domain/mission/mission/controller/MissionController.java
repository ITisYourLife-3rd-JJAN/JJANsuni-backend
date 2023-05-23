package com.kb.jjan.domain.mission.mission.controller;

import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.dto.MissionInfoResponse;
import com.kb.jjan.domain.mission.mission.dto.MissionQuizRequest;
import com.kb.jjan.domain.mission.mission.dto.MissionVodRequest;
import com.kb.jjan.domain.mission.mission.service.MissionService;
import com.kb.jjan.global.result.ResultCode;
import com.kb.jjan.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/admin")
@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register-quiz")
    public ResponseEntity<ResultResponse> registerQuiz(@RequestBody MissionQuizRequest missionQuizRequest){
        missionService.registerQuiz(missionQuizRequest);
        ResultResponse<Mission> resultResponse = new ResultResponse<>(ResultCode.QUIZ_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register-vod")
    public ResponseEntity<ResultResponse> registerVod(@RequestBody MissionVodRequest missionVodRequest){
        missionService.registerVod(missionVodRequest);
        ResultResponse<Mission> resultResponse = new ResultResponse<>(ResultCode.VOD_REGISTRATION_SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/mission/{missionId}")
    public ResponseEntity<ResultResponse> getUserMissionInfoByMissionId(@PathVariable("missionId") long missionId){
        Mission mission = missionService.findMissionInfo(missionId);
        MissionInfoResponse missionInfoResponse = new MissionInfoResponse(mission);
        ResultResponse<MissionInfoResponse> resultResponse = new ResultResponse<>(ResultCode.GET_MISSION_SUCCESS, missionInfoResponse);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/map/{mapNum}/mission/{missionNum}")
    public ResponseEntity<ResultResponse> getMissionInfoByMapNumMissionNum(@PathVariable int mapNum, @PathVariable int missionNum){
        Mission mission = missionService.findMissionInfo(mapNum, missionNum);
        MissionInfoResponse missionInfoResponse = new MissionInfoResponse(mission);
        ResultResponse<MissionInfoResponse> resultResponse = new ResultResponse<>(ResultCode.GET_MISSION_SUCCESS, missionInfoResponse);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

}
