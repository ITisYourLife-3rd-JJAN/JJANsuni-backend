package com.kb.jjan.domain.mission.mission.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

import static com.kb.jjan.global.error.ErrorCode.DUPLICATE_MISSION;

public class DuplicateMission extends BusinessException {
    public DuplicateMission() {
        super(DUPLICATE_MISSION);
    }
}
