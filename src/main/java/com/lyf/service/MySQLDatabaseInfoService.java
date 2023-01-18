package com.lyf.service;

import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.TableInfoEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MySQLDatabaseInfoService {
    public List<TableInfoResponse> queryAll(String fuzzy);
    Optional<TableInfoResponse> queryTable(String tableName);
}
