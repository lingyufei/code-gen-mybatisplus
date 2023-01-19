package ${packageName}.utils;

/**
* 异常枚举类
* @author LYF_
* @create 2022-06-10 14:28
*/
public enum ExceptionCodeEnum {
    SUCCESS(200, "success"),
    //服务器错误
    SERVER_EXCEPTION(500, "服务器发生异常，请联系管理员"),

    //10开头，表单相关异常
    FORM_VALID_EXCEPTION(1000, "您填写的表单格式不合法或未填写完整"),//表单校验异常

    //20开头，数据库通用异常
    DB_DUPLICATE_KEY_EXCEPTION(2001, "您要添加的数据已存在");

    private final Integer code;
    private final String msg;

    ExceptionCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
