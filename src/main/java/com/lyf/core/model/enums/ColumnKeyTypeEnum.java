package com.lyf.core.model.enums;

import org.springframework.util.StringUtils;

import java.util.Optional;

public enum ColumnKeyTypeEnum {
    PRIMARY("PRI"),
    UNIQUE("UNI"),
    NONE("NONE");

    final String type;

    ColumnKeyTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 根据 mysqlType 获取枚举
     *
     * @param type
     * @return MysqlKeyTypeEnum
     */
    public static ColumnKeyTypeEnum getEnumByType(String type) {
        if (!StringUtils.hasText(type)) {
            return null;
        }
        for (ColumnKeyTypeEnum keyTypeEnum : ColumnKeyTypeEnum.values()) {
            if (keyTypeEnum.type.equals(type)) {
                return keyTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据 type 获取枚举,如果都不匹配，使用 NONE
     *
     * @param type
     * @return
     */
    public static ColumnKeyTypeEnum getEnumByTypeWithDefault(String type) {
        return Optional.ofNullable(ColumnKeyTypeEnum.getEnumByType(type))
                .orElse(ColumnKeyTypeEnum.NONE);
    }

    public String getType() {
        return type;
    }
}
