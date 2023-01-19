package com.evax.common.execption;

import com.evax.common.response.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一的异常类进行处理(把默认的异常返回信息改成自定义的异常返回信息)
 * 当GlobalController抛出HospitalException异常时，将自动找到此类中对应的方法执行，并返回json数据给前台
 *
 * @author Lionel
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 异常处理器，处理CustomException异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResultJson handleException(CustomException e) {
        return e.getResultJson();
    }
}