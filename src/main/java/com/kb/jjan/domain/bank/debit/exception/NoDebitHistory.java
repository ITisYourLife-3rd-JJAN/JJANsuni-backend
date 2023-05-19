package com.kb.jjan.domain.bank.debit.exception;


import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class NoDebitHistory extends BusinessException {
  public NoDebitHistory() {
    super(ErrorCode.NO_DEBIT_HISTORY);
  }
}
