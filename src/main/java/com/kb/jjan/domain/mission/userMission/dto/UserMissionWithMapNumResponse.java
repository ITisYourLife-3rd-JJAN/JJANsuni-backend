package com.kb.jjan.domain.mission.userMission.dto;

import com.kb.jjan.domain.mission.userMission.UserMission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMissionWithMapNumResponse {
    private Long userMissionId;
    private int missionNum;
    private int status;

    public UserMissionWithMapNumResponse(UserMission userMission) {
        this.userMissionId = userMission.getUserMissionId();
        this.status = userMission.getStatus();
        this.missionNum = userMission.getSolvedMission().getMissionNum();
    }
}
