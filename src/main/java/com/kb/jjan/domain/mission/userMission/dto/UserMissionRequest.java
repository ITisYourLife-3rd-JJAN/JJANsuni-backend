package com.kb.jjan.domain.mission.userMission.dto;

import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.user.User;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMissionRequest {
    private Mission solvedMissionId;
    private User solvedUserId;
    private int status;

    public UserMission toEntity() {
        return UserMission.builder()
                .solvedMissionId(solvedMissionId)
                .solvedUserId(solvedUserId)
                .status(status)
                .build();
    }

}
