package com.xmy.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author xmy
 * @date 2021/3/7 8:31 下午
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFounfExcetion extends RuntimeException {
    public NotFounfExcetion() {
    }

    public NotFounfExcetion(String message) {
        super(message);
    }

    public NotFounfExcetion(String message, Throwable cause) {
        super(message, cause);
    }
}
