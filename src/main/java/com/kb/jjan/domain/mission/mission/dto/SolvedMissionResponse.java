package com.kb.jjan.domain.mission.mission.dto;

import com.kb.jjan.domain.mission.mission.Mission;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SolvedMissionResponse {
    private int missionId;
    private String missionType;
    private int mapNum;
    private int missionNum;
    private String title;
    private String vodUrl;
    private String answer;

    public SolvedMissionResponse(Mission mission) {
        this.missionId = mission.getMissionNum();
        this.missionType = mission.getMissionType();
        this.mapNum = mission.getMapNum();
        this.missionNum = mission.getMissionNum();
        this.title = mission.getTitle();
        this.vodUrl = mission.getVodUrl();
        this.answer = mission.getAnswer();
    }
}
