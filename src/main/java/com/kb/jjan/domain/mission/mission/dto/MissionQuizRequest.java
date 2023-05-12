package com.kb.jjan.domain.mission.mission.dto;

import com.kb.jjan.domain.mission.mission.Mission;
import lombok.*;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MissionQuizRequest {
    private String missionType;
    private int mapNum;
    private int missionNum;
    private String title;
    private String explain;
    private String answer;

    public Mission toEntity() {
        return Mission.builder()
                .missionType(missionType)
                .mapNum(mapNum)
                .missionNum(missionNum)
                .title(title)
                .explain(explain)
                .answer(answer)
                .build();
    }
}
