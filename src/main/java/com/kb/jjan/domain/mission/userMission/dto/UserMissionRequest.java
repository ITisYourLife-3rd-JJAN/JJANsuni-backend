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
    private Long solvedMissionId;
    private Long solvedUserId;
    private int status;

    public UserMission toEntity(Mission solvedMission, User solvedUser) {
        return UserMission.builder()
                .solvedMission(solvedMission)
                .solvedUser(solvedUser)
                .status(status)
                .build();
    }
}
