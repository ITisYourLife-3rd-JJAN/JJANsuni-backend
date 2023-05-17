package com.kb.jjan.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Global
  INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
  INPUT_INVALID_VALUE(409, "G002", "잘못된 입력입니다."),

  // User
  NOT_FOUND_FAM_CODE_ERROR(400, "U001", "잘못된 가족코드입니다.");


  private final int status;
  private final String code;
  private final String message;
}