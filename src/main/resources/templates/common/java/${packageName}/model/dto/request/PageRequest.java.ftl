package ${packageName}.model.dto.request;

import ${packageName}.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Map;

import static ${packageName}.utils.ExceptionCodeEnum.FORM_VALID_EXCEPTION;

/**
* 分页请求
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    //当前页码
    private Integer page;
    //每页条数
    private Integer limit;
    
    private Boolean desc;
    
    public PageRequest(Map<String, Object> params){
        try {
            //分页参数
            this.page = Integer.parseInt(params.get("page").toString());
            this.limit = Integer.parseInt(params.get("limit").toString());
            this.desc = Boolean.parseBoolean(params.getOrDefault("desc", false).toString());
        } catch (Exception e) {
            throw new BusinessException(null, FORM_VALID_EXCEPTION, params.toString() + " 分页请求参数不合法:" + e.getMessage());
        }
            this.valid();
    }
    
    public void valid(){
        if(ObjectUtils.isEmpty(page) || ObjectUtils.isEmpty(limit) || page <= 0 || limit <= 0){
            throw new BusinessException(null, FORM_VALID_EXCEPTION, "分页请求参数不全:" + this.toString());
        }
    }
}
