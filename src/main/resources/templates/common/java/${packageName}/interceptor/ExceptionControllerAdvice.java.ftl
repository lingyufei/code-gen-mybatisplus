package ${packageName}.interceptor;

import ${packageName}.exception.BusinessException;
import ${packageName}.constant.enums.ExceptionCodeEnum;
import ${packageName}.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static ${packageName}.constant.enums.ExceptionCodeEnum.DB_DUPLICATE_KEY_EXCEPTION;
import static ${packageName}.constant.enums.ExceptionCodeEnum.SERVER_EXCEPTION;

/**
* @author LYF_
* @create 2022-05-09 12:04
*/
@RestControllerAdvice(basePackages = "${packageName}")
@Slf4j
public class ExceptionControllerAdvice {
    //业务异常处理
    @ExceptionHandler(value = BusinessException.class)
    public R handleBusinessException(BusinessException e) {
        log.info("{}异常，异常代码：{}，异常原因：{}, context: {}，详细：\r", e.getBusinessType(), e.getCode(), e.getMsg(), e.getLogMsg(), e);
        return R.error(e);
    }
    
    //表单验证异常处理
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach(error -> {
        errorMap.put(error.getField(), error.getDefaultMessage());
        });
        log.info("数据校验异常: {},异常类型: {}, 校验异常详细信息: {}", e.getMessage(), e.getClass(), errorMap);
        
        return R.error(ExceptionCodeEnum.FORM_VALID_EXCEPTION).put("data", errorMap);
    }
    
    //数据库唯一键冲突处理
    @ExceptionHandler(value = DuplicateKeyException.class)
        public R handleLoginUniqueException(DuplicateKeyException e) {
        log.warn("提交的数据与数据库数据存在冲突,原因如下：\r" + e);
        return R.error(DB_DUPLICATE_KEY_EXCEPTION);
    }
    
    //其他异常处理
    @ExceptionHandler(value = Exception.class)
    public R handleLoginException(Exception e) {
        log.warn("业务不可控异常: " + e.getMessage() + " 具体如下：\r" + e);
        return R.error(SERVER_EXCEPTION);
    }
}
