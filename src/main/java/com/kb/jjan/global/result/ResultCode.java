package com.kb.jjan.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // 도메인 별로 나눠서 관리(ex: User 도메인, UserMission 도메인 ..)
    // user
    USER_REGISTRATION_SUCCESS("U001", "201", "사용자가 정상적으로 등록되었습니다."),
    USER_GENERATION_SUCCESS("U002", "201", "가족 코드가 생성되었습니다."),
    USER_UPDATE_BALANCE_SUCCESS("U003", "201", "사용자의 잔액정보가 업데이트되었습니다."),

    // mission (admin)
    QUIZ_REGISTRATION_SUCCESS("A001", "201", "퀴즈가 정상적으로 등록되었습니다."),
    VOD_REGISTRATION_SUCCESS("A002", "201", "VOD가 정상적으로 등록되었습니다."),

    // user Mission
    USER_MISSION_REGISTRATION_SUCCESS("M002", "201", "문제 풀이 정보가 정상적으로 등록되었습니다."),

    // debit
    DEBIT_REGISTRATION_SUCCESS("D001", "201", "이체가 정상적으로 수행되었습니다."),
    DEBIT_HISTORY_FINDBYIDUSER_SUCCESS("D002", "200", "결제 내역을 정상적으로 조회했습니다."),
    DEBIT_JJANPAY_CHARGE_SUCCESS("D003", "200", "짠페이 충전을 완료했습니다"),

    // direct
    DIRECT_REGISTRATION_SUCCESS("E002", "201", "자동이체가 정상적으로 등록되었습니다."),

    // find by id user
    FINDBYIDUSER_REGISTRATION_SUCCESS("U004", "201", "해당 ID의 회원정보를 정상적으로 불러왔습니다."),

    // login
    USER_LOGIN_SUCCESS("U005", "201", "로그인이 정상적으로 수행되었습니다.");


    private final String code;
    private final String status;
    private final String message;
}