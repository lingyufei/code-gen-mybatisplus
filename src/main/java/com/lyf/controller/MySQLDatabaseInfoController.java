package com.lyf.controller;

import com.alibaba.fastjson.JSON;
import com.lyf.exception.BusinessException;
import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.service.MySQLDatabaseInfoService;
import com.lyf.utils.R;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public R generate(@RequestBody List<TableConfigRequest> tableConfigRequests){
        if(CollectionUtils.isEmpty(tableConfigRequests)){
            throw new BusinessException("", FORM_VALID_EXCEPTION, JSON.toJSONString(tableConfigRequests));
        }
        mySQLDatabaseInfoService.generate(tableConfigRequests);
        return R.ok();
    }
}
