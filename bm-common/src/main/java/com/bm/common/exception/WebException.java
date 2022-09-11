package com.bm.common.exception;

import com.bm.common.numeration.ExceptionEnum;

public class WebException extends RuntimeException{

    private Integer code;

    public WebException( String message) {
        super(message);
    }

    public WebException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }


    public WebException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
