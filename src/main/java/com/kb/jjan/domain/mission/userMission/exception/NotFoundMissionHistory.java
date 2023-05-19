package com.kb.jjan.domain.mission.userMission.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

import static com.kb.jjan.global.error.ErrorCode.NOT_FOUND_MISSION_HISTORY;

public class NotFoundMissionHistory extends BusinessException {

    public NotFoundMissionHistory() {
        super(NOT_FOUND_MISSION_HISTORY);
    }
}
