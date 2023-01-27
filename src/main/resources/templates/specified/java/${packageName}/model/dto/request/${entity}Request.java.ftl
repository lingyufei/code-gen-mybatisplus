package ${packageName}.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ${tableName} Request
* @author ${author}
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