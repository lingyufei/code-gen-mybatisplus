package com.lyf.model.dto.response;


import com.lyf.model.entity.TableInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfoResponse {
    private String tableName;
    private String tableComment;
    private String engine;
    private Date createTime;
    
    public TableInfoResponse(TableInfoEntity tableInfoEntity){
        this.tableName = tableInfoEntity.getTableName();
        this.tableComment = tableInfoEntity.getTableComment();
        this.engine = tableInfoEntity.getEngine();
        this.createTime = tableInfoEntity.getCreateTime();
    }
}
