package com.kb.jjan.domain.user.exception;


import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class EmailExist extends BusinessException {
  public EmailExist() {
    super(ErrorCode.EXIST_EMAIL_ERROR);
  }
}