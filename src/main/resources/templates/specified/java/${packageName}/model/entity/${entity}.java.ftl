package ${packageName}.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
* ${tableName} entity
* @author ${author}
*/
@Data
@TableName("${tableName}")
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName} implements Serializable {
    private static final long serialVersionUID = 1L;

    <#-- 循环生成字段 ---------->
    <#list columnSchemaList as columnSchema>
    <#if !columnSchema.ignoreEntity>
    <#if columnSchema.columnComment!?length gt 0>
    /**
    * ${columnSchema.columnComment}
    */
    </#if>
    <#if columnSchema.isPrimaryKey()>
    @TableId
    </#if>
    private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};
    </#if>

    </#list>
}
