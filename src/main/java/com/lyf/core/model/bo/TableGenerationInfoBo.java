package com.lyf.core.model.bo;

import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;

import java.util.List;

public class TableGenerationInfoBo {
    private String tableName;
    private String packageName;
    private String author;
    private TableConfigRequest tableConfigRequest;
    private TableInfoEntity tableInfoEntity;
    private List<ColumnInfoEntity> columnInfoEntities;

    public TableGenerationInfoBo(String tableName, TableConfigRequest tableConfigRequest,
                                 TableInfoEntity tableInfoEntity, List<ColumnInfoEntity> columnInfoEntities,
                                 String packageName, String author) {
        this.tableName = tableName;
        this.tableConfigRequest = tableConfigRequest;
        this.tableInfoEntity = tableInfoEntity;
        this.columnInfoEntities = columnInfoEntities;
        this.author = author;
        this.packageName = packageName;
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

    public String getPackageName() {
        return packageName;
    }

    public String getAuthor() {
        return author;
    }
}
