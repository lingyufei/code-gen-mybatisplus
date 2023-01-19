package com.lyf.core.model.enums;

import org.springframework.util.StringUtils;

import java.util.Optional;

public enum FilePathEnum {
    CONTOLLER("Controller.java", "/java/${packageName}/controller/"),
    SERVICE("Service.java", "/java/${packageName}/service/"),
    SERVICEIMPL("ServiceImpl.java", "/${packageName}/service/impl/"),
    DAO("Dao.java", "/java/${packageName}/dao/"),
    DAO_XML("Dao.xml", "/resources/mapper/"),
    ENTITY("Entity.java", "/java/${packageName}/model/entity/"),
    REQUEST("Request.java", "/java/${packageName}/model/dto/request/"),
    RESPONSE("Response.java", "/java/${packageName}/model/dto/response/"),
    VO("Vo.java", "/java/${packageName}/model/vo/"),
    DEFAULT("", "/resources/others/");

    
    final String fileName;
    final String filePath;

    FilePathEnum(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * 根据 mysqlType 获取枚举
     *
     * @param fileName
     * @return
     */
    public static FilePathEnum getEnumByFileName(String fileName) {
        if (!StringUtils.hasText(fileName)) {
            return null;
        }
        for (FilePathEnum filePathEnum : FilePathEnum.values()) {
            if (fileName.startsWith(filePathEnum.fileName)) {
                return filePathEnum;
            }
        }
        return null;
    }

    /**
     * 根据 mysqlType 获取枚举,如果都不匹配，使用
     *
     * @param fileName
     * @return
     */
    public static FilePathEnum getEnumByFileNameWithDefault(String fileName) {
        return Optional.ofNullable(FilePathEnum.getEnumByFileName(fileName))
                .orElse(FilePathEnum.DEFAULT);
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }
}
