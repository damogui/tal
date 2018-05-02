package com.netsharp.rest.common.exception;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 常规业务异常
 * @date 2018/4/16 15:51
 */
@SuppressWarnings("serial")
public class BaseException extends RuntimeException{

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
    }
}
