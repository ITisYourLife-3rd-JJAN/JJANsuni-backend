package com.kb.jjan.domain.mission.userMission.exception;

import com.kb.jjan.global.error.exception.BusinessException;

import static com.kb.jjan.global.error.ErrorCode.DUPLICATE_MISSION_HISTORY;

public class DuplicateUserMissionHistory extends BusinessException {
    public DuplicateUserMissionHistory() {
        super(DUPLICATE_MISSION_HISTORY);
    }
}
