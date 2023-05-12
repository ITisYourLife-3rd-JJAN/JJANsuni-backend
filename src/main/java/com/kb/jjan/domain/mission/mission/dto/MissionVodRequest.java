package com.kb.jjan.domain.mission.mission.dto;

import com.kb.jjan.domain.mission.mission.Mission;
import lombok.*;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MissionVodRequest {
    private String missionType;
    private int mapNum;
    private int missionNum;
    private String vodUrl;

    public Mission toEntity() {
        return Mission.builder()
                .missionType(missionType)
                .mapNum(mapNum)
                .missionNum(missionNum)
                .vodUrl(vodUrl)
                .build();
    }
}
