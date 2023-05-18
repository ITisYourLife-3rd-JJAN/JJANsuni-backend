package com.kb.jjan.domain.mission.mission.dto;

import com.kb.jjan.domain.mission.mission.Mission;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MissionInfoResponse {
    private Long missionId;
    private String missionType;
    private int mapNum;
    private int missionNum;
    private String title;
    private String vodUrl;
    private String explain;
    private String answer;

    public MissionInfoResponse(Mission mission) {
        this.missionId = mission.getMissionId();
        this.missionType = mission.getMissionType();
        this.mapNum = mission.getMapNum();
        this.missionNum = mission.getMissionNum();
        this.title = mission.getTitle();
        this.vodUrl = mission.getVodUrl();
        this.explain = mission.getExplain();
        this.answer = mission.getAnswer();
    }
}