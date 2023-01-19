package ${packageName}.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ${tableName} Request
* @author chenshun
* @email sunlightcs@gmail.com
* @date 2023-01-17 09:29:27
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName}Request {

    <#list columnSchemaList as columnSchema>
    <#if !columnSchema.ignoreRequest>
        private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};
    </#if>

    </#list>
}