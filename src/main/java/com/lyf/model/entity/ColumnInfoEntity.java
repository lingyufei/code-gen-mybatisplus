package com.lyf.model.entity;

public class ColumnInfoEntity {
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;

    public ColumnInfoEntity(String columnName, String dataType, String columnComment, String columnKey, String extra) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
        this.extra = extra;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public String getExtra() {
        return extra;
    }


    @Override
    public String toString() {
        return "ColumnInfoEntity{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
