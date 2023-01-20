package ${packageName}.service;


import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.model.dto.request.PageRequest;
import ${packageName}.dto.request.${upperCamelName}Request;
import ${packageName}.dto.response.PageResponse;
import ${packageName}.dto.response.${upperCamelName}Response;
import ${packageName}.entity.${upperCamelName};

/**
*
* ${tableName} service
* @author LYF
* @email 1187112693@qq.com
* @date 2023-01-17 09:29:27
*/
public interface ${upperCamelName}Service extends IService<${upperCamelName}>{

    public PageResponse<${upperCamelName}Response> queryPage(PageRequest pageRequest);

    ${upperCamelName}Response getInfo(Long id);

    boolean save(${upperCamelName}Request ${lowerCamelName}Request);

    boolean updateById(${upperCamelName}Request ${lowerCamelName}Request);
}