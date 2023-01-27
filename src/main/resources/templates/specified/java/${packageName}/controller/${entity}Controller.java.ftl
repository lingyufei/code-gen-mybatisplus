package ${packageName}.controller;

import ${packageName}.model.dto.request.PageRequest;
import ${packageName}.model.dto.request.${upperCamelName}Request;
import ${packageName}.model.dto.response.PageResponse;
import ${packageName}.model.dto.response.${upperCamelName}Response;
import ${packageName}.service.${upperCamelName}Service;
import ${packageName}.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
* ${tableName} controller
* @author ${author}
*/
@RestController
@RequestMapping("${lowerCamelName}")
public class ${upperCamelName}Controller {
@Autowired
private ${upperCamelName}Service ${lowerCamelName}Service;

    /**
    * 列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageRequest pageRequest = new PageRequest(params);
        PageResponse<${upperCamelName}Response> page = ${lowerCamelName}Service.queryPage(pageRequest);

        return R.ok().put("data", page);
    }


    /**
    * 信息
    */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ${upperCamelName}Response ${lowerCamelName}Response = ${lowerCamelName}Service.getInfo(id);

        return R.ok().put("data", ${lowerCamelName}Response);
    }

    /**
    * 保存
    */
    @PutMapping("/save")
    public R save(@RequestBody ${upperCamelName}Request ${lowerCamelName}Request){
        ${lowerCamelName}Service.save(${lowerCamelName}Request);

        return R.ok();
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    public R update(@RequestBody ${upperCamelName}Request ${lowerCamelName}Request){
        ${lowerCamelName}Service.updateById(${lowerCamelName}Request);

        return R.ok();
    }

    /**
    * 删除
    */
    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable Long id){
        ${lowerCamelName}Service.removeById(id);

        return R.ok();
    }

    /**
    * 删除
    */
    @DeleteMapping("/deleteBatch")
    public R delete(@RequestParam Long[] ids){
        ${lowerCamelName}Service.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
