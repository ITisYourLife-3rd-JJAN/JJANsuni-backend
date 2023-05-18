package com.kb.jjan.domain.mission.mission.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class NotFoundMission extends BusinessException {
    public NotFoundMission() {
        super(ErrorCode.NOT_FOUND_MISSION);
    }
}
