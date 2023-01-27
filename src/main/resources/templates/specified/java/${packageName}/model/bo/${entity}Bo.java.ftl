package ${packageName}.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ${tableName} Bo
* @author ${author}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${upperCamelName}Bo {
    <#list columnSchemaList as columnSchema>
    <#if !columnSchema.ignoreBo>
    private ${columnSchema.dataType.javaType} ${columnSchema.lowerCamelName};
    </#if>

    </#list>
}
