package ${packageName}.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* user_info Response
* @author ${author}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName}Response {
    <#list columnSchemaList as columnSchema>
    <#if !columnSchema.ignoreResponse>
    private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};
    </#if>

    </#list>
}
