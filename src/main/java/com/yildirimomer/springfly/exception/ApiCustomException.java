package com.yildirimomer.springfly.exception;

/**
 * Created by Omer YILDIRIM on 9.05.2020.
 * I-Luxus GmbH.
 * omer@i-luxus.de
 */
public class ApiCustomException  extends RuntimeException {

    public ApiCustomException(String param) {
        super("ApiCustomException :"+ param);
    }
}
