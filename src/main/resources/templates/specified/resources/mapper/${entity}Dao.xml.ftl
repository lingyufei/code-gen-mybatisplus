<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.dao.${upperCamelName}Dao">

    <resultMap type="${packageName}.model.entity.${upperCamelName}" id="${lowerCamelName}Map">
    <#list columnSchemaList as columnSchema>
        <result property="${columnSchema.lowerCamelName}" column="${columnSchema.columnName}" jdbcType="${columnSchema.dataType.mysqlType}"/>
    </#list>
    </resultMap>

    <!--    <select id="query" resultMap="${lowerCamelName}Map">-->
    <!--        SELECT * FROM `${tableName}`-->
    <!--    </select>-->
</mapper>