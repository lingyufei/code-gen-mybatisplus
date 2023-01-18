package com.lyf.core.model.to;

import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;

import java.util.List;

public class GenerationRequestInfoTo {
    private String tableName;
    private TableConfigRequest tableConfigRequest;
    private TableInfoEntity tableInfoEntity;
    private List<ColumnInfoEntity> columnInfoEntities;

    public GenerationRequestInfoTo(String tableName, TableConfigRequest tableConfigRequest, TableInfoEntity tableInfoEntity, List<ColumnInfoEntity> columnInfoEntities) {
        this.tableName = tableName;
        this.tableConfigRequest = tableConfigRequest;
        this.tableInfoEntity = tableInfoEntity;
        this.columnInfoEntities = columnInfoEntities;
    }

    public String getTableName() {
        return tableName;
    }

    public TableConfigRequest getTableConfigRequest() {
        return tableConfigRequest;
    }

    public TableInfoEntity getTableInfoEntity() {
        return tableInfoEntity;
    }

    public List<ColumnInfoEntity> getColumnInfoEntities() {
        return columnInfoEntities;
    }
}
