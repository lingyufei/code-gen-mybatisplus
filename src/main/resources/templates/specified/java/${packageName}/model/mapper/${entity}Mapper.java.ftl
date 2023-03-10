package ${packageName}.model.mapper;

import ${packageName}.model.bo.${upperCamelName}Bo;
import ${packageName}.model.dto.request.${upperCamelName}Request;
import ${packageName}.model.dto.response.${upperCamelName}Response;
import ${packageName}.model.entity.${upperCamelName};
import org.mapstruct.Mapper;

/**
* ${tableName} Mapper
* @author ${author}
*/
@Mapper(componentModel = "spring")
public interface ${upperCamelName}Mapper {

    ${upperCamelName}Response ${lowerCamelName}2${upperCamelName}Response(${upperCamelName} source);
    
    ${upperCamelName} ${lowerCamelName}Request2${upperCamelName}(${upperCamelName}Request source);
    
    ${upperCamelName}Bo ${lowerCamelName}Request2${upperCamelName}Bo(${upperCamelName}Request source);
    
    ${upperCamelName}Bo ${lowerCamelName}2${upperCamelName}Bo(${upperCamelName} source);
    
    ${upperCamelName}Response ${lowerCamelName}Bo2${upperCamelName}Response(${upperCamelName}Bo source);
}
