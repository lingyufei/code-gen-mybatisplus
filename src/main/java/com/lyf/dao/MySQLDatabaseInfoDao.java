package com.lyf.dao;

import com.lyf.model.entity.ColumnInfoEntity;
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
    List<TableInfoEntity> queryList(@Param("db") String db, @Param("tableName") String tableName);

    TableInfoEntity queryTable(@Param("db") String db, @Param("tableName") String tableName);

    List<ColumnInfoEntity> queryColumns(@Param("db") String db, @Param("tableName") String tableName);

    List<String> queryAllDatabases();

}
