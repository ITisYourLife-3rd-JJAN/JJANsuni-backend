package com.kb.jjan.domain.user.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class NotFoundUser extends BusinessException {
    public NotFoundUser() {
        super(ErrorCode.NOT_FOUND_USER);
    }
}
