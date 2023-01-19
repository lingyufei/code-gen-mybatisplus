package ${packageName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ${packageName}.dao.${upperCamelName}Dao;
import ${packageName}.model.dto.request.PageRequest;
import ${packageName}.model.dto.request.${upperCamelName}Request;
import ${packageName}.model.dto.response.PageResponse;
import ${packageName}.model.dto.response.${upperCamelName}Response;
import ${packageName}.model.entity.${upperCamelName};
import ${packageName}.service.${upperCamelName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("${lowerCamelName}Service")
public class ${upperCamelName}ServiceImpl extends ServiceImpl<${upperCamelName}Dao, ${upperCamelName}> implements ${upperCamelName}Service {
    @Autowired
    ${upperCamelName}Dao ${lowerCamelName}Dao;
    
    public PageResponse<${upperCamelName}Response> queryPage(PageRequest pageRequest) {
        try(Page<?> page = PageHelper.startPage(pageRequest.getPage(), pageRequest.getLimit())){
        List<${upperCamelName}> ${lowerCamelName}s = this.list(new QueryWrapper<${upperCamelName}>()
                .orderBy(true, !pageRequest.getDesc(), "id"));
    
        //类型转换
        List<${upperCamelName}Response> ${lowerCamelName}ResponseList = ${lowerCamelName}s.stream().map(${upperCamelName}Response::new).collect(Collectors.toList());
        return new PageResponse<>(${lowerCamelName}ResponseList, page.getTotal(), pageRequest.getLimit(), pageRequest.getPage(), pageRequest.getDesc()); 
    }
    
    @Override
    public ${upperCamelName}Response getInfo(Long id) {
        ${upperCamelName} ${lowerCamelName} = super.getById(id);
        return new ${upperCamelName}Response(${lowerCamelName});
    }

    @Override
    public boolean save(${upperCamelName}Request ${lowerCamelName}Request) {
        ${upperCamelName} ${lowerCamelName} = new ${upperCamelName}(${lowerCamelName}Request);
        return super.save(${lowerCamelName});
    }

    @Override
    public boolean updateById(${upperCamelName}Request ${lowerCamelName}Request) {
        ${upperCamelName} ${lowerCamelName} = new ${upperCamelName}(${lowerCamelName}Request);
        return super.updateById(${lowerCamelName});
    }
}