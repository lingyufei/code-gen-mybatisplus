package com.lyf.model.dto.response;

import com.lyf.model.entity.ColumnInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnInfoResponse {
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;

    public ColumnInfoResponse(ColumnInfoEntity columnInfoEntity){
        this.columnName = columnInfoEntity.getColumnName();
        this.dataType = columnInfoEntity.getDataType();
        this.columnComment = columnInfoEntity.getColumnComment();
        this.columnKey = columnInfoEntity.getColumnKey();
        this.extra = columnInfoEntity.getExtra();
    }
}
