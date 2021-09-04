package com.tangerinespecter.javabaseutils.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 全局异常
 *
 * @author TangerineSpecter
 */
@Getter
@Setter
public class GlobalException extends RuntimeException {

    private Integer code = 500;
    private String message;

    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public GlobalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalException(Integer code) {
        this.code = code;
    }
}
