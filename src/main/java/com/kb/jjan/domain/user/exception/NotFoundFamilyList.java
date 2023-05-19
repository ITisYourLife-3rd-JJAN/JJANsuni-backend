package com.kb.jjan.domain.user.exception;



import com.kb.jjan.global.error.exception.BusinessException;


import static com.kb.jjan.global.error.ErrorCode.NOT_FOUND_FAMILY;


public class NotFoundFamilyList extends BusinessException {
  public NotFoundFamilyList() {
    super(NOT_FOUND_FAMILY);
  }
}