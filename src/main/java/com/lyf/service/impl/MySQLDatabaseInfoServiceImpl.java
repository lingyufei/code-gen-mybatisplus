package com.lyf.service.impl;

import com.alibaba.fastjson.JSON;
import com.lyf.constant.Constant;
import com.lyf.core.fileSaver.ZipFileSaver;
import com.lyf.core.generator.GeneratorFacade;
import com.lyf.core.model.bo.GenerationInfoBo;
import com.lyf.core.model.bo.OptionalGenerationBo;
import com.lyf.core.model.bo.TableGenerationInfoBo;
import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.dao.MySQLDatabaseInfoDao;
import com.lyf.exception.BusinessException;
import com.lyf.model.dto.request.ConfigRequest;
import com.lyf.model.dto.request.OptionalConfigRequest;
import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.dto.response.ColumnInfoResponse;
import com.lyf.model.dto.response.TableInfoResponse;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;
import com.lyf.service.MySQLDatabaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.lyf.constant.ExceptionCodeEnum.FORM_VALID_EXCEPTION;

@Slf4j
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
    public byte[] generate(ConfigRequest configRequest) {
        List<TableConfigRequest> tableConfigRequests = configRequest.getTableConfigRequestList();
        String packageName = Optional.ofNullable(configRequest.getPackageName()).orElse(Constant.DEFAULT_PACKAGE);
        String author = Optional.ofNullable(configRequest.getAuthor()).orElse(Constant.DEFAULT_PACKAGE);
        OptionalConfigRequest optionalConfigRequest = Optional.ofNullable(configRequest.getOptionalConfigRequest())
                                                                .orElse(new OptionalConfigRequest());

        //校验，过滤空数据
        tableConfigRequests = tableConfigRequests.stream().filter(tableConfigRequest -> {
            return StringUtils.hasText(tableConfigRequest.getTableName());
        }).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(tableConfigRequests)){
            throw new BusinessException("", FORM_VALID_EXCEPTION, JSON.toJSONString(tableConfigRequests));
        }

        List<TableGenerationInfoBo> generationRequestInfos = new ArrayList<>();

        //进行数据转换
        for (TableConfigRequest tableConfigRequest : tableConfigRequests) {
            String tableName = tableConfigRequest.getTableName();
            //获取表的所有表的详细信息
            TableInfoEntity tableInfoEntity = mySQLDatabaseInfoDao.queryTable(tableName);
            List<ColumnInfoEntity> columnInfoEntities = mySQLDatabaseInfoDao.queryColumns(tableName);

            generationRequestInfos.add(
                    new TableGenerationInfoBo(tableName, tableConfigRequest, tableInfoEntity, columnInfoEntities, author, packageName));
        }

        OptionalGenerationBo optionalGenerationBo = new OptionalGenerationBo(optionalConfigRequest.getIgnoreThreadPool(), optionalConfigRequest.getIgnoreLogInterceptor());
        GenerationInfoBo generationInfoBo = new GenerationInfoBo(packageName, author, optionalGenerationBo, generationRequestInfos);

        List<StringWriterResultBo> resultToList = generatorFacade.generateByRequest(generationInfoBo);
        return ZipFileSaver.Save(resultToList);
    }

    @Override
    public byte[] generateByDefault(ConfigRequest configRequest) {
        log.info("begin to generateByDefault, configRequest: [{}]", configRequest);
        String packageName = Constant.DEFAULT_PACKAGE;
        String author = Constant.DEFAULT_AUTHOR;
        OptionalConfigRequest optionalConfigRequest = new OptionalConfigRequest();
        if(!ObjectUtils.isEmpty(configRequest)){
            if(StringUtils.hasText(configRequest.getPackageName())){
                packageName = configRequest.getPackageName();
            }
            if(StringUtils.hasText(configRequest.getAuthor())){
                author = configRequest.getAuthor();
            }
            if(ObjectUtils.isEmpty(configRequest.getOptionalConfigRequest())){
                optionalConfigRequest = configRequest.getOptionalConfigRequest();
            }
        }

        List<TableInfoEntity> tableInfoEntities = mySQLDatabaseInfoDao.queryList(null);
        List<TableGenerationInfoBo> generationRequestInfos = new ArrayList<>();
        for (TableInfoEntity tableInfoEntity : tableInfoEntities) {
            String tableName = tableInfoEntity.getTableName();
            List<ColumnInfoEntity> columnInfoEntities = mySQLDatabaseInfoDao.queryColumns(tableName);
            generationRequestInfos.add(
                    new TableGenerationInfoBo(tableName, null, tableInfoEntity, columnInfoEntities, packageName, author));
        }

        OptionalGenerationBo optionalGenerationBo = new OptionalGenerationBo(optionalConfigRequest.getIgnoreThreadPool(), optionalConfigRequest.getIgnoreLogInterceptor());
        List<StringWriterResultBo> resultToList = generatorFacade.generateByDefault(new GenerationInfoBo(packageName, author, optionalGenerationBo, generationRequestInfos));
        return ZipFileSaver.Save(resultToList);
    }
}
