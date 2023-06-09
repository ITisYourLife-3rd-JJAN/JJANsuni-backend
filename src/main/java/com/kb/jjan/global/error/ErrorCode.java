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
  NOT_FOUND_FAM_CODE_ERROR(400, "U001", "잘못된 가족코드입니다."),
  EXIST_EMAIL_ERROR(400, "U002", "이미 존재하는 Email입니다."),
  NOT_FOUND_USER(400, "U003", "존재하지 않는 User 입니다."),
  NOT_FOUND_FAMILY(400, "U004", "등록된 자녀가 없습니다."),

  // Debit
  OVER_BALANCE_DEBIT_ERROR(500,"D001","잔액이 부족합니다."),
  NO_DEBIT_HISTORY(200,"D002","이체내역이 없습니다."),

  // Direct
  NO_DIRECT_DEBIT(200, "E001", "자동이체 내역이 없습니다."),

  // Mission
  NOT_FOUND_MISSION(400, "A001", "존재하지 않는 미션입니다."),
  DUPLICATE_MISSION(400, "A002", "이미 존재하는 미션입니다."),
  INACCESSIBLE_ROLE(400, "M001", "접근 불가한 회원입니다."),
  NOT_FOUND_MISSION_HISTORY(200, "M002", "미션을 수행한 이력이 없습니다."),
  DUPLICATE_MISSION_HISTORY(400, "M003", "이미 존재하는 미션 수행기록이 있습니다.");

  private final int status;
  private final String code;
  private final String message;
}