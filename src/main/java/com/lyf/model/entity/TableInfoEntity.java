package com.lyf.model.entity;


import java.util.Date;

public class TableInfoEntity {
    private String tableName;
    private String tableComment;
    private String engine;
    private Date createTime;

    public TableInfoEntity(String tableName, String tableComment, String engine, Date createTime) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.engine = engine;
        this.createTime = createTime;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getEngine() {
        return engine;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "TableInfoEntity{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", engine='" + engine + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
