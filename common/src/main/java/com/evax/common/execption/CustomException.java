package com.evax.common.execption;


import com.evax.common.response.ResultJson;

/**
 * 自定义项目内异常
 *
 * @author Lionel
 */
public class CustomException extends RuntimeException {


    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }

    public ResultJson getResultJson() {
        return resultJson;
    }

    public void setResultJson(ResultJson resultJson) {
        this.resultJson = resultJson;
    }
}
