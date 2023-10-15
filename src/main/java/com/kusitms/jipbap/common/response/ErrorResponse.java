package com.kusitms.jipbap.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> {
    private Boolean isSuccess;
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 오류 발생
    public ErrorResponse(ErrorCode errorCode) {
        this.isSuccess = errorCode.getIsSuccess();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode, T result) {
        this.isSuccess = errorCode.getIsSuccess();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result = result;
    }
}
