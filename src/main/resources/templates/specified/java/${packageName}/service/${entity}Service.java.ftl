package ${packageName}.service;


import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.model.dto.request.PageRequest;
import ${packageName}.model.dto.request.${upperCamelName}Request;
import ${packageName}.model.dto.response.PageResponse;
import ${packageName}.model.dto.response.${upperCamelName}Response;
import ${packageName}.model.entity.${upperCamelName};

/**
*
* ${tableName} service
* @author ${author}
*/
public interface ${upperCamelName}Service extends IService<${upperCamelName}>{

    public PageResponse<${upperCamelName}Response> queryPage(PageRequest pageRequest);

    ${upperCamelName}Response getInfo(Long id);

    boolean save(${upperCamelName}Request ${lowerCamelName}Request);

    boolean updateById(${upperCamelName}Request ${lowerCamelName}Request);
}