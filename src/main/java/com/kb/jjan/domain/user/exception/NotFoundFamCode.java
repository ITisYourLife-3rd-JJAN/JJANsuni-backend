package com.kb.jjan.domain.user.exception;


import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class NotFoundFamCode extends BusinessException {
  public NotFoundFamCode() {
    super(ErrorCode.NOT_FOUND_FAM_CODE_ERROR);
  }
}
