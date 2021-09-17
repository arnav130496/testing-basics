package com.arnavsaraf.testbasics.Employee.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@ResponseBody
public class NoDataException  extends RuntimeException {

    private static final long serialVersionUID = 1L;
    String message;

    @Override
    public String getMessage() {
        return message;
    }


    public NoDataException(String message) {
        this.message = message;
    }
}
