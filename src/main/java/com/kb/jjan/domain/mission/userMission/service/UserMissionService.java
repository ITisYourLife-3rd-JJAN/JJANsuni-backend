package com.kb.jjan.domain.mission.userMission.service;

import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.repository.MissionRepository;
import com.kb.jjan.domain.mission.mission.service.MissionService;
import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionRequest;
import com.kb.jjan.domain.mission.userMission.dto.UserMissionResponse;
import com.kb.jjan.domain.mission.userMission.exception.InaccessibleRole;
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

        if (Objects.equals(solvedUser.getIsParent(), "P")) throw new InaccessibleRole();
        userService.updateUser(userMissionRequest.getSolvedUserId());

        UserMission userMission = userMissionRequest.toEntity(solvedMission, solvedUser);
        userMissionRepository.save(userMission);
    }

    public List<UserMissionResponse> getMissionStatus(long userId) throws Exception {
        List<UserMission> userMissionList = userMissionRepository.findBySolvedUserId(userId);
        List<UserMissionResponse> responseList = new ArrayList<>();

        for (UserMission userMission : userMissionList) {
            UserMissionResponse response = new UserMissionResponse(userMission);
            responseList.add(response);
        }

        return responseList;
    }



}
