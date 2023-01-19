package ${packageName}.model.dto.response;

import ${packageName}.model.dto.request.${upperCamelName}Request;
import ${packageName}.model.entity.${upperCamelName};
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* user_info Response
* @author chenshun
* @email sunlightcs@gmail.com
* @date 2023-01-17 09:29:27
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName}Response {
    <#list columnSchemaList as columnSchema>
    <#if !columnSchema.ignoreResponse>private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};
    </#if>

    </#list>

    public ${upperCamelName}Response(${upperCamelName} source){
        <#list columnSchemaList as columnSchema>
        <#if !columnSchema.ignoreEntity && !columnSchema.ignoreResponse>
        this.${columnSchema.lowerCamelName} = source.get${columnSchema.upperCamelName}();
        </#if>
        </#list>
    }
}
