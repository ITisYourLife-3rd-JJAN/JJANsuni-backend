package com.kb.jjan.domain.mission.userMission.service;

import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.service.MissionService;
import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionRequest;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionResponse;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionWithMapNumRequest;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionWithMapNumResponse;
import com.kb.jjan.domain.mission.userMission.exception.DuplicateUserMissionHistory;
import com.kb.jjan.domain.mission.userMission.exception.InaccessibleRole;
import com.kb.jjan.domain.mission.userMission.exception.NotFoundMissionHistory;
import com.kb.jjan.domain.mission.userMission.repository.UserMissionRepository;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    private final MissionService missionService;
    private final UserService userService;


    public void registerUserMission(@RequestBody UserMissionRequest userMissionRequest) throws Exception {
        User solvedUser = userService.findUserById(userMissionRequest.getSolvedUserId());
        Mission solvedMission = missionService.findMissionInfo(userMissionRequest.getSolvedMissionId());

        if (!Objects.equals(solvedUser.getIsParent(), "F")) throw new InaccessibleRole();        // 아이가 아닐 경우 미션 풀이 불가
        if (userMissionRepository.existsBySolvedMissionMissionIdAndSolvedUserUserId(solvedMission.getMissionId(), solvedUser.getUserId()))
            throw new DuplicateUserMissionHistory();        //  이미 존재하는 미션 수행 기록이 있다면, updateUserToAchieve 하지 않음

        userService.updateUserToAchieve(userMissionRequest.getSolvedUserId());

        UserMission userMission = userMissionRequest.toEntity(solvedMission, solvedUser);
        userMissionRepository.save(userMission);
    }

    public List<UserMissionResponse> getMissionStatus(long userId) throws Exception {
        List<UserMission> userMissionList = userMissionRepository.findBySolvedUserId(userId);
        if (userMissionList.isEmpty()) throw new NotFoundMissionHistory();

        List<UserMissionResponse> userMissionResponses = new ArrayList<>();
        for (UserMission userMission : userMissionList) {
            UserMissionResponse response = new UserMissionResponse(userMission);
            userMissionResponses.add(response);
        }

        return userMissionResponses;
    }

    public List<UserMissionWithMapNumResponse> getMissionStatusWithMapNum (UserMissionWithMapNumRequest userMissionWithMapNumRequest) throws Exception {
        long userId = userMissionWithMapNumRequest.getSolvedUserId();
        int mapNum = userMissionWithMapNumRequest.getMapNum();
        List<UserMission> userMissionList =  userMissionRepository.findUserMissionsBySolvedUserUserIdAndAndSolvedMission_MapNum(userId, mapNum);
        if (userMissionList.isEmpty()) {
            throw new NotFoundMissionHistory();
        }

        List<UserMissionWithMapNumResponse> userMissionWithMapNumResponseList = new ArrayList<>();
        for (UserMission userMission : userMissionList){
            UserMissionWithMapNumResponse response = new UserMissionWithMapNumResponse(userMission);
            userMissionWithMapNumResponseList.add(response);
        }

        return userMissionWithMapNumResponseList;
    }



}
