package com.lyf.controller;

import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.service.MySQLDatabaseInfoService;
import com.lyf.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
}
