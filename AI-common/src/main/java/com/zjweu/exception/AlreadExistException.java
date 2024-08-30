package com.zjweu.exception;

/**
 * 账号被锁定异常
 */
public class AlreadExistException extends BaseException {

    public AlreadExistException() {
    }

    public AlreadExistException(String msg) {
        super(msg);
    }

}
