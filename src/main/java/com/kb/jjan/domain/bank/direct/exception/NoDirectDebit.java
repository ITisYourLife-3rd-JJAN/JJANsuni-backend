package com.kb.jjan.domain.bank.direct.exception;

import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class NoDirectDebit extends BusinessException {

    public NoDirectDebit() {
        super(ErrorCode.NO_DIRECT_DEBIT);
    }
}
