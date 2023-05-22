package com.kb.jjan.domain.mission.userMission.dto;

import com.kb.jjan.domain.mission.mission.dto.SolvedMissionResponse;
import com.kb.jjan.domain.mission.userMission.UserMission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserMissionResponse {
    private Long userMissionId;
    private SolvedMissionResponse solvedMission;
    private int status;
    @DateTimeFormat
    private LocalDateTime createAt;

    public UserMissionResponse(UserMission userMission) {
        this.userMissionId = userMission.getUserMissionId();
        this.status = userMission.getStatus();
        this.createAt = userMission.getCreateAt();

        if (userMission.getSolvedUser() != null) {
            this.solvedMission = new SolvedMissionResponse(userMission.getSolvedMission());
        }
    }
}