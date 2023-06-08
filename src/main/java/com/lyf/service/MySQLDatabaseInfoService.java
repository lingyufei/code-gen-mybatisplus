package com.lyf.service;

import com.lyf.model.dto.request.ConfigRequest;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MySQLDatabaseInfoService {
    public List<String> queryAllDatabases();

    public List<TableInfoResponse> queryAllTable(String db, String fuzzy);
    Optional<TableInfoResponse> queryTable(String db, String tableName);

    public Map<String, List<ColumnInfoResponse>> queryColumnsOfAllTables(String db, List<String> tableNames);

    List<ColumnInfoResponse> queryColumnsOfTable(String db, String tableName);

    byte[] generate(ConfigRequest configRequest);

    byte[] generateByDefault(ConfigRequest configRequest);
}
