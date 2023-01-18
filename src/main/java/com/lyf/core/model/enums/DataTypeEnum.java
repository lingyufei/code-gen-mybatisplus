package com.lyf.core.model.enums;

import org.springframework.util.StringUtils;

import java.util.Optional;

public enum DataTypeEnum {
    TINYINT("tinyint", "Integer"),
    SMALLINT("smallint", "Integer" ),
    MEDIUMINT("mediumint", "Integer"),
    INT("int", "Integer"),
    BIGINT("bigint", "Long"),
    FLOAT("float", "Double" ),
    DOUBLE("double", "Double"),
    DECIMAL("decimal", "BigDecimal"),
    DATE("date", "Date"),
    TIME("time", "Time"),
    YEAR("year", "Integer"),
    DATETIME("datetime", "Date"),
    TIMESTAMP("timestamp", "Long"),
    CHAR("char", "String"),
    VARCHAR("varchar", "String"),
    TINYTEXT("tinytext", "String"),
    TEXT("text", "String"),
    MEDIUMTEXT("mediumtext", "String"),
    LONGTEXT("longtext", "String"),
    TINYBLOB("tinyblob", "byte[]"),
    BLOB("blob", "byte[]"),
    MEDIUMBLOB("mediumblob", "byte[]"),
    LONGBLOB("longblob", "byte[]"),
    BINARY("binary", "byte[]"),
    VARBINARY("varbinary", "byte[]");


    public final String javaType;
    public final String mysqlType;

    DataTypeEnum(String mysqlType, String javaType) {
        this.javaType = javaType;
        this.mysqlType = mysqlType;
    }

    /**
     * 根据 mysqlType 获取枚举
     *
     * @param mysqlType
     * @return
     */
    public static DataTypeEnum getEnumByMysqlType(String mysqlType) {
        if (!StringUtils.hasText(mysqlType)) {
            return null;
        }
        for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
            if (dataTypeEnum.mysqlType.equals(mysqlType)) {
                return dataTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据 mysqlType 获取枚举,如果都不匹配，使用
     *
     * @param mysqlType
     * @return
     */
    public static DataTypeEnum getEnumByMysqlTypeWithDefault(String mysqlType) {
        return Optional.ofNullable(DataTypeEnum.getEnumByMysqlType(mysqlType))
                .orElse(DataTypeEnum.TEXT);
    }


    public String getJavaType() {
        return javaType;
    }

    public String getMysqlType() {
        return mysqlType;
    }
}
