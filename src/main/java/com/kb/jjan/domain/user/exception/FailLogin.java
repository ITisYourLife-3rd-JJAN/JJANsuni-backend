package com.kb.jjan.domain.user.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class FailLogin extends BusinessException {
    public FailLogin() {
        super(ErrorCode.FAIL_LOGIN);
    }
}
