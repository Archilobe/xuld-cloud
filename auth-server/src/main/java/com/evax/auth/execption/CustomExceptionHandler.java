
package com.evax.auth.execption;

import com.evax.common.execption.CustomException;
import com.evax.common.response.ResultJson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一的异常类进行处理(把默认的异常返回信息改成自定义的异常返回信息)
 * 当GlobalController抛出HospitalException异常时，将自动找到此类中对应的方法执行，并返回json数据给前台
 *
 * @author Lionel
 */
@ControllerAdvice
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

//    /**
//     * 认证异常
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler({AuthenticationException.class})
//    public String handleException(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {
//        try {
//            response.sendRedirect("/auth/authentication");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 权限异常
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    @org.springframework.web.bind.annotation.ExceptionHandler({UnauthorizedException.class})
//    public String handleException(HttpServletRequest request, HttpServletResponse response, UnauthorizedException ex) {
//        try {
//            response.sendRedirect("/auth/authorization");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}