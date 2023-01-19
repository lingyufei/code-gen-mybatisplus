package ${packageName}.model.vo;

import ${packageName}.model.dto.request.${upperCamelName}Request;
import ${packageName}.model.entity.${upperCamelName};
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ${tableName} Vo
* @author chenshun
* @email sunlightcs@gmail.com
* @date 2023-01-17 09:29:27
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName}Vo {
    <#list columnSchemaList as columnSchema>
    <#if !columnSchema.ignoreVo>
    private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};
    </#if>

    </#list>
    
    public ${upperCamelName}Vo(${upperCamelName} source){
    <#list columnSchemaList as columnSchema>
        <#if !columnSchema.ignoreVo && !columnSchema.ignoreEntity>
        this.${columnSchema.lowerCamelName} = source.get${columnSchema.upperCamelName}();
        </#if>
    </#list>
    }

    public ${upperCamelName}Vo(${upperCamelName}Request source){
    <#list columnSchemaList as columnSchema>
        <#if !columnSchema.ignoreVo && !columnSchema.ignoreRequest>
        this.${columnSchema.lowerCamelName} = source.get${columnSchema.upperCamelName}();
        </#if>
    </#list>
    }
}
