package me.erickren.beans.factory.exception;

/**
 * Bean exception.
 * DateTime: 2023/09/14 - 22:14
 * Author: ErickRen
 */
public class BeanException extends RuntimeException {

    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
