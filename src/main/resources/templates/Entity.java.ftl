package ${packageName}.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyf.model.dto.request.UserInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
* ${tableName} entity
* @author LYF
* @email 1187112693@qq.com
* @date 2023-01-17 09:29:27
*/
@Data
@TableName("${tableName}")
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName} implements Serializable {
    private static final long serialVersionUID = 1L;

    <#-- 循环生成字段 ---------->
    <#list columnSchemaList as columnSchema>
    <#if columnSchema.columnComment!?length gt 0>
    /**
    * ${columnSchema.columnComment}
    */
    </#if>
    <#if columnSchema.isPrimaryKey()>
    @TableId
    </#if>
    private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};

    </#list>

    public ${upperCamelName}(${upperCamelName}Request source){
    <#list columnSchemaList as columnSchema>
        <#if !columnSchema.ignoreResponse>
        set${columnSchema.upperCamelName}(source.get${columnSchema.upperCamelName}());
        </#if>
    </#list>
    }
}
