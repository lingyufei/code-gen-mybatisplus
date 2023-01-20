package ${packageName}.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ${tableName} Bo
* @author chenshun
* @email sunlightcs@gmail.com
* @date 2023-01-17 09:29:27
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
