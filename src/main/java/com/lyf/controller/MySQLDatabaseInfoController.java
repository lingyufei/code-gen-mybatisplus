package com.lyf.controller;

import com.alibaba.fastjson.JSON;
import com.lyf.constant.ExceptionCodeEnum;
import com.lyf.exception.BusinessException;
import com.lyf.model.dto.request.ConfigRequest;
import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.service.MySQLDatabaseInfoService;
import com.lyf.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.lyf.constant.ExceptionCodeEnum.FORM_VALID_EXCEPTION;

@RestController
@RequestMapping("/db")
public class MySQLDatabaseInfoController {

    @Resource
    MySQLDatabaseInfoService mySQLDatabaseInfoService;

    @GetMapping("/table/list")
    public R queryTableList(@RequestParam(name = "tableName", required = false) String fuzzy){
        List<TableInfoResponse> tableInfoResponses = mySQLDatabaseInfoService.queryAllTable(fuzzy);
        return R.ok().put("data", tableInfoResponses);
    }

    @GetMapping("/column/list")
    public R queryColumnOfAllTables(@RequestParam(name = "tables", required = false) List<String> tables){
        Map<String, List<ColumnInfoResponse>> map = mySQLDatabaseInfoService.queryColumnsOfAllTables(tables);
        return R.ok().put("data", map);
    }

    @PostMapping("/generate")
    public R generate(@RequestBody ConfigRequest configRequest, HttpServletResponse response){
        if(CollectionUtils.isEmpty(configRequest.getTableConfigRequestList())){
            throw new BusinessException("", FORM_VALID_EXCEPTION, JSON.toJSONString(configRequest));
        }
        byte[] result = mySQLDatabaseInfoService.generate(configRequest);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"defaultCode.zip\"");
        response.addHeader("Content-Length", "" + result.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        try {
            IOUtils.write(result, response.getOutputStream());
        }catch (IOException e) {
            response.reset();
            throw new BusinessException("", ExceptionCodeEnum.GEN_FILE_OUTPUT_EXCEPTION, e);
        }

        return R.ok();
    }

    @GetMapping("/generate/default")
    public void generateByDefault(@RequestBody(required = false) ConfigRequest configRequest, HttpServletResponse response){
        byte[] result = mySQLDatabaseInfoService.generateByDefault(configRequest);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"defaultCode.zip\"");
        response.addHeader("Content-Length", "" + result.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        try {
            IOUtils.write(result, response.getOutputStream());
        }catch (IOException e) {
            response.reset();
            throw new BusinessException("", ExceptionCodeEnum.GEN_FILE_OUTPUT_EXCEPTION, e);
        }
    }
}
