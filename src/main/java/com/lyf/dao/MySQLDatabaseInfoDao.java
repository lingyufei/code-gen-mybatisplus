package com.lyf.dao;

import com.lyf.model.entity.TableInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * MySQL代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2018-07-24
 */
@Mapper
public interface MySQLDatabaseInfoDao {
    List<TableInfoEntity> queryList(@Param("tableName") String tableName);

    TableInfoEntity queryTable(@Param("tableName") String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}
