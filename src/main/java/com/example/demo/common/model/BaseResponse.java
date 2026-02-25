package com.example.demo.common.model;

/*
* {Success: true, code: 1000, message: '아이디 비밀번호를 확인해주세요', result: 실제 응답 객체}
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.example.demo.common.model.BaseResponseStatus.SUCCESS;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T result;

    public static <T> BaseResponse success(T result) {
        return new BaseResponse(
                SUCCESS.isSuccess(),
                SUCCESS.getCode(),
                SUCCESS.getMessage(),
                result);
    }

    public static <T> BaseResponse fail(BaseResponseStatus status, T result) {
        return new BaseResponse(
                status.isSuccess(),
                status.getCode(),
                status.getMessage(),
                result
        );
    }
}
