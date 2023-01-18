package com.lyf.service.impl;

import com.lyf.dao.MySQLDatabaseInfoDao;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.TableInfoEntity;
import com.lyf.service.MySQLDatabaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("mySQLDatabaseInfoService")
public class MySQLDatabaseInfoServiceImpl implements MySQLDatabaseInfoService {

    @Autowired
    MySQLDatabaseInfoDao mySQLDatabaseInfoDao;

    /**
     * 返回数据库表信息，支持模糊查询
     * @param fuzzy
     * @return
     */
    public List<TableInfoResponse> queryAll(String fuzzy){
        List<TableInfoEntity> tableInfoEntities = mySQLDatabaseInfoDao.queryList(fuzzy);
        return tableInfoEntities.stream().map(TableInfoResponse::new).collect(Collectors.toList());
    }

    public Optional<TableInfoResponse> queryTable(String tableName){
        TableInfoEntity tableInfoEntity = mySQLDatabaseInfoDao.queryTable(tableName);
        return ObjectUtils.isEmpty(tableInfoEntity) ?
                Optional.empty() : Optional.of(new TableInfoResponse(tableInfoEntity));
    }
}
