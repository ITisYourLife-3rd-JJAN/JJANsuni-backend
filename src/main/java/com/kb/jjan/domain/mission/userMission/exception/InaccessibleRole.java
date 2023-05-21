package com.kb.jjan.domain.mission.userMission.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class InaccessibleRole extends BusinessException {
    public InaccessibleRole() {
        super(ErrorCode.INACCESSIBLE_ROLE);
    }
}
