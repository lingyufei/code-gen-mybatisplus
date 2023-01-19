package com.lyf.core.schema;

import com.lyf.constant.Constant;
import com.lyf.utils.StringUtils;

import java.util.List;

public class TableSchema {
    //sql LOWER_UNDERSCORE user_info
    private String tableName;

    //java LOWER_CAMEL  userInfo
    private String lowerCamelName;

    //java UPPER_CAMEL UserInfo
    private String upperCamelName;

    //包名
    private String packageName;

    private String tableComment;

    List<ColumnSchema> columnSchemaList;

    public TableSchema(String tableName, String packageName, List<ColumnSchema> columnSchemaList, String tableComment) {
        this.tableName = tableName;
        this.lowerCamelName = StringUtils.LowerUnderscoreToLowerCamel(tableName);
        this.upperCamelName = StringUtils.LowerUnderScoreToUpperCamel(tableName);
        if(org.springframework.util.StringUtils.hasText(packageName)){
            this.packageName = packageName;
        }else{
            this.packageName = Constant.DEFAULT_PACKAGE;
        }
        this.packageName = packageName;
        this.columnSchemaList = columnSchemaList;
        this.tableComment = tableComment;
    }

    public TableSchema(String tableName, List<ColumnSchema> columnSchemaList, String tableComment) {
        this.tableName = tableName;
        this.lowerCamelName = StringUtils.LowerUnderscoreToLowerCamel(tableName);
        this.upperCamelName = StringUtils.LowerUnderScoreToUpperCamel(tableName);
        this.columnSchemaList = columnSchemaList;
        this.tableComment = tableComment;
        this.packageName = Constant.DEFAULT_PACKAGE;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLowerCamelName() {
        return lowerCamelName;
    }

    public void setLowerCamelName(String lowerCamelName) {
        this.lowerCamelName = lowerCamelName;
    }

    public String getUpperCamelName() {
        return upperCamelName;
    }

    public void setUpperCamelName(String upperCamelName) {
        this.upperCamelName = upperCamelName;
    }

    public String getPackageName() {
        if(org.springframework.util.StringUtils.hasText(packageName)){
            return packageName;
        }else{
            return  Constant.DEFAULT_PACKAGE;
        }
    }

    public String getPackagePath() {
        if(org.springframework.util.StringUtils.hasText(packageName)){
            return packageName.replace("\\.", "/");
        }else{
            return  Constant.DEFAULT_PACKAGE.replace("\\.", "/");
        }
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ColumnSchema> getColumnSchemaList() {
        return columnSchemaList;
    }

    public void setColumnSchemaList(List<ColumnSchema> columnSchemaList) {
        this.columnSchemaList = columnSchemaList;
    }
}
