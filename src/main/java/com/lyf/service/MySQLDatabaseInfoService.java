package com.lyf.service;

import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MySQLDatabaseInfoService {
    public List<TableInfoResponse> queryAllTable(String fuzzy);
    Optional<TableInfoResponse> queryTable(String tableName);

    public Map<String, List<ColumnInfoResponse>> queryColumnsOfAllTables(List<String> tableNames);

    List<ColumnInfoResponse> queryColumnsOfTable(String tableName);

    void generate(List<TableConfigRequest> tableConfigRequests);

}