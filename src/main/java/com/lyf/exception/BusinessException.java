package com.lyf.exception;

import com.lyf.constant.ExceptionCodeEnum;

/**
 * @author LYF_
 * @create 2022-06-10 14:28
 */
public class BusinessException extends RuntimeException {
    //业务类型
    private String businessType;

    //错误代码
    private Integer code;

    //返回前端的错误显示
    private String msg;

    //后端查看的实际错误
    private String logMsg;

    public BusinessException(String businessType, Integer code, String msg, String logMsg) {
        this.businessType = businessType;
        this.code = code;
        this.msg = msg;
        this.logMsg = logMsg;
    }

    public BusinessException(String businessType, ExceptionCodeEnum exceptionCodeEnum, String logMsg) {
        this.businessType = businessType;
        this.code = exceptionCodeEnum.getCode();
        this.msg = exceptionCodeEnum.getMsg();
        this.logMsg = logMsg;
    }

    public BusinessException(String businessType, ExceptionCodeEnum exceptionCodeEnum, Throwable throwable) {
        this.businessType = businessType;
        this.code = exceptionCodeEnum.getCode();
        this.msg = exceptionCodeEnum.getMsg();
        this.logMsg = throwable.getMessage();
    }

    protected BusinessException() {
    }

    public String getBusinessType() {
        return businessType;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getLogMsg() {
        return logMsg;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "businessType='" + businessType + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", logMsg='" + logMsg + '\'' +
                '}';
    }
}
