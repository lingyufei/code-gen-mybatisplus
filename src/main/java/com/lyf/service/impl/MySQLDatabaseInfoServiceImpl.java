package com.lyf.service.impl;

import com.alibaba.fastjson.JSON;
import com.lyf.constant.Constant;
import com.lyf.core.generator.GeneratorFacade;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.dao.MySQLDatabaseInfoDao;
import com.lyf.exception.BusinessException;
import com.lyf.model.dto.request.ConfigRequest;
import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;
import com.lyf.service.MySQLDatabaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

import static com.lyf.constant.ExceptionCodeEnum.FORM_VALID_EXCEPTION;

@Service("mySQLDatabaseInfoService")
public class MySQLDatabaseInfoServiceImpl implements MySQLDatabaseInfoService {

    @Resource
    MySQLDatabaseInfoDao mySQLDatabaseInfoDao;
    @Resource
    GeneratorFacade generatorFacade;

    /**
     * 返回数据库表信息，支持模糊查询
     * @param fuzzy
     * @return
     */
    public List<TableInfoResponse> queryAllTable(String fuzzy){
        List<TableInfoEntity> tableInfoEntities = mySQLDatabaseInfoDao.queryList(fuzzy);
        return tableInfoEntities.stream().map(TableInfoResponse::new).collect(Collectors.toList());
    }

    public Optional<TableInfoResponse> queryTable(String tableName){
        TableInfoEntity tableInfoEntity = mySQLDatabaseInfoDao.queryTable(tableName);
        return ObjectUtils.isEmpty(tableInfoEntity) ?
                Optional.empty() : Optional.of(new TableInfoResponse(tableInfoEntity));
    }

    public Map<String, List<ColumnInfoResponse>> queryColumnsOfAllTables(List<String> tableNames){
        Map<String, List<ColumnInfoResponse>> map = new HashMap<>();

        if(CollectionUtils.isEmpty(tableNames)){
            return map;
        }

        for(String tableName: tableNames){
            map.put(tableName, this.queryColumnsOfTable(tableName));
        }
        return map;
    }

    @Override
    public List<ColumnInfoResponse> queryColumnsOfTable(String tableName) {
        List<ColumnInfoEntity> columnInfoEntities = mySQLDatabaseInfoDao.queryColumns(tableName);
        return columnInfoEntities.stream().map(ColumnInfoResponse::new).collect(Collectors.toList());
    }

    /**
     * 代码生成
     * @param configRequest
     */
    @Override
    public void generate(ConfigRequest configRequest) {
        List<TableConfigRequest> tableConfigRequests = configRequest.getTableConfigRequestList();
        String packageName = Optional.ofNullable(configRequest.getPackageName()).orElse(Constant.DEFAULT_PACKAGE);
        String author = Optional.ofNullable(configRequest.getAuthor()).orElse(Constant.DEFAULT_PACKAGE);

        //校验，过滤空数据
        tableConfigRequests = tableConfigRequests.stream().filter(tableConfigRequest -> {
            return StringUtils.hasText(tableConfigRequest.getTableName());
        }).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(tableConfigRequests)){
            throw new BusinessException("", FORM_VALID_EXCEPTION, JSON.toJSONString(tableConfigRequests));
        }

        List<GenerationRequestInfoTo> generationRequestInfos = new ArrayList<>();

        //进行数据转换
        for (TableConfigRequest tableConfigRequest : tableConfigRequests) {
            String tableName = tableConfigRequest.getTableName();
            //获取表的所有表的详细信息
            TableInfoEntity tableInfoEntity = mySQLDatabaseInfoDao.queryTable(tableName);
            List<ColumnInfoEntity> columnInfoEntities = mySQLDatabaseInfoDao.queryColumns(tableName);

            generationRequestInfos.add(
                    new GenerationRequestInfoTo(tableName, tableConfigRequest, tableInfoEntity, columnInfoEntities, author, packageName));
        }

        List<StringWriterResultTo> resultToList = generatorFacade.generateByRequest(generationRequestInfos);
    }

    @Override
    public void generateByDefault(ConfigRequest configRequest) {
        String packageName = Optional.ofNullable(configRequest.getPackageName()).orElse(Constant.DEFAULT_PACKAGE);
        String author = Optional.ofNullable(configRequest.getAuthor()).orElse(Constant.DEFAULT_PACKAGE);

        List<TableInfoEntity> tableInfoEntities = mySQLDatabaseInfoDao.queryList(null);
        List<GenerationRequestInfoTo> generationRequestInfos = new ArrayList<>();
        for (TableInfoEntity tableInfoEntity : tableInfoEntities) {
            String tableName = tableInfoEntity.getTableName();
            List<ColumnInfoEntity> columnInfoEntities = mySQLDatabaseInfoDao.queryColumns(tableName);
            generationRequestInfos.add(
                    new GenerationRequestInfoTo(tableName, null, tableInfoEntity, columnInfoEntities, author, packageName));
        }

        List<StringWriterResultTo> resultTos = generatorFacade.generateByDefault(generationRequestInfos);
    }
}
