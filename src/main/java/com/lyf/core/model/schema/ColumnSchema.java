package com.lyf.core.model.schema;

import com.lyf.core.model.enums.DataTypeEnum;
import com.lyf.model.dto.request.ColumnConfigRequest;
import com.lyf.model.dto.request.PageRequest;
import com.lyf.utils.StringUtils;
import org.springframework.util.ObjectUtils;

public class ColumnSchema {

    //sql LOWER_UNDERSCORE user_info
    private String columnName;

    private DataTypeEnum dataType;

    private String columnComment;

    private String columnKey;

    //java LOWER_CAMEL  userInfo
    private String lowerCamelName;

    //java UPPER_CAMEL UserInfo
    private String upperCamelName;

    private Boolean ignoreEntity;

    private Boolean ignoreRequest;

    private Boolean ignoreResponse;

    private Boolean ignoreTo;


    public ColumnSchema(String columnName, DataTypeEnum dataType, String columnComment,
                        String columnKey, Boolean ignoreEntity, Boolean ignoreRequest,
                        Boolean ignoreResponse, Boolean ignoreTo) {
        this.columnName = columnName;
        this.lowerCamelName = StringUtils.LowerUnderscoreToLowerCamel(columnName);
        this.upperCamelName = StringUtils.LowerUnderScoreToUpperCamel(columnName);
        this.ignoreEntity = ignoreEntity;
        this.ignoreRequest = ignoreRequest;
        this.ignoreResponse = ignoreResponse;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
    }

    public ColumnSchema(String columnName, DataTypeEnum dataType, String columnComment, String columnKey) {
        this.columnName = columnName;
        this.lowerCamelName = StringUtils.LowerUnderscoreToLowerCamel(columnName);
        this.upperCamelName = StringUtils.LowerUnderScoreToUpperCamel(columnName);
        this.ignoreEntity = false;
        this.ignoreRequest = false;
        this.ignoreResponse = false;
        this.ignoreTo = false;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.columnKey = columnKey;
    }

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
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

    public Boolean getIgnoreEntity() {
        return ignoreEntity;
    }

    public void setIgnoreEntity(Boolean ignoreEntity) {
        this.ignoreEntity = ignoreEntity;
    }

    public Boolean getIgnoreRequest() {
        return ignoreRequest;
    }

    public void setIgnoreRequest(Boolean ignoreRequest) {
        this.ignoreRequest = ignoreRequest;
    }

    public Boolean getIgnoreResponse() {
        return ignoreResponse;
    }

    public void setIgnoreResponse(Boolean ignoreResponse) {
        this.ignoreResponse = ignoreResponse;
    }

    public Boolean getIgnoreTo() {
        return ignoreTo;
    }

    public void setIgnoreTo(Boolean ignoreTo) {
        this.ignoreTo = ignoreTo;
    }
}
