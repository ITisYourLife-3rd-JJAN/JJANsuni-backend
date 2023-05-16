package com.kb.jjan.global.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ResultResponse<T> {

    private final String status;
    private final String message;
    private Object data;
    private long sendUserId;


    @JsonCreator
    public ResultResponse(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

    @JsonCreator
    public ResultResponse(String status, ResultCode resultCode, Object data) {
        this.status = status;
        this.message = resultCode.getMessage();
        this.data = data;
    }

    @JsonCreator
    public ResultResponse(ResultCode resultCode, long sendUserId) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
        this.sendUserId = sendUserId;
    }
}
