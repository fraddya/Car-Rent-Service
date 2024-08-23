package com.unreallabss.carrent.domain.base;


import com.unreallabss.carrent.enums.RestApiResponseStatus;

public class ExceptionResponseWrapper extends BaseResponseWrapper {
    public ExceptionResponseWrapper(String message) {
        super(RestApiResponseStatus.NOT_FOUND);
        this.message=message;
    }
}
