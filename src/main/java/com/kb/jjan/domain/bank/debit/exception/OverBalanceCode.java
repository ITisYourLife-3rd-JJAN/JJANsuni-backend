package com.kb.jjan.domain.bank.debit.exception;


import com.kb.jjan.global.error.ErrorCode;
import com.kb.jjan.global.error.exception.BusinessException;

public class OverBalanceCode extends BusinessException {
  public OverBalanceCode() {
    super(ErrorCode.OVER_BALANCE_DEBIT_ERROR);
  }
}