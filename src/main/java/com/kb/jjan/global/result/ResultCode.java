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
    USER_UPDATE_PHONE_SUCCESS("U004", "201", "사용자의 전화번호가 업데이트되었습니다."),

    // mission (admin)
    QUIZ_REGISTRATION_SUCCESS("A001", "201", "퀴즈가 정상적으로 등록되었습니다."),
    VOD_REGISTRATION_SUCCESS("A002", "201", "VOD가 정상적으로 등록되었습니다."),
    GET_MISSION_SUCCESS("A003", "200", "문제 정보를 정상적으로 불러왔습니다."),

    // user Mission
    USER_MISSION_REGISTRATION_SUCCESS("M001", "201", "문제 풀이 정보가 정상적으로 등록되었습니다."),
    GET_USER_MISSION_SUCCESS("M002", "200", "문제 풀이 정보가 정상적으로 등록되었습니다."),

    // debit
    DEBIT_REGISTRATION_SUCCESS("D001", "201", "이체가 정상적으로 수행되었습니다."),
    DEBIT_HISTORY_FINDBYIDUSER_SUCCESS("D002", "200", "이체 내역을 정상적으로 조회했습니다."),
    DEBIT_JJANPAY_CHARGE_SUCCESS("D003", "200", "짠페이 충전을 완료했습니다"),
    DEBIT_EVENT_CHARGE_SUCCESS("D004", "200", "이벤트에 대한 금액이 정상적으로 반영되었습니다."),


    // direct
    DIRECT_REGISTRATION_SUCCESS("E001", "201", "자동이체가 정상적으로 등록되었습니다."),
    DIRECT_FINDBYUSERID_SUCCESS("E002", "200", "자동이체 정보를 정상적으로 조회했습니다."),
    DIRECT_UPDATE_SUCCESS("E003", "200", "자동이체 정보가 정상적으로 수정되었습니다."),
    DIRECT_DELETE_SUCCESS("E004", "200", "자동이체가 정상적으로 삭제되었습니다."),

    // find by id user
    USER_FINDBYIDUSER_SUCCESS("U004", "200", "해당 ID의 회원정보를 정상적으로 불러왔습니다."),

    // login
    USER_LOGIN_SUCCESS("U005", "200", "로그인이 정상적으로 수행되었습니다."),

    // Email Exist
    USER_EXISTBYEMAIL_SUCCESS("U006", "200", "사용 가능한 Email 주소 입니다."),

    USER_FINDBYFAMCODE_SUCCESS("U007", "200", "해당 가족코드를 가진 User들을 정상적으로 불러왔습니다."),
    USER_FAMCODEEXIST_SUCCESS("U008", "200", "가족코드가 확인되었습니다.");

    private final String code;
    private final String status;
    private final String message;
}