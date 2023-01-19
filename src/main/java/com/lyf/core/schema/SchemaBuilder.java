package com.lyf.core.schema;

import com.lyf.constant.Constant;
import com.lyf.core.model.enums.ColumnKeyTypeEnum;
import com.lyf.core.model.enums.DataTypeEnum;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.model.dto.request.ColumnConfigRequest;
import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class SchemaBuilder {
    /**
     * 基于用户的配置请求，转换为TableSchema
     * @param generationRequestInfoTos
     * @return
     */
    public static List<TableSchema> BuildFromRequestConfig(List<GenerationRequestInfoTo> generationRequestInfoTos){
        //所有信息进行类型转换，汇聚成一个统一的 Schema
        List<TableSchema> tableSchemaList = generationRequestInfoTos.stream()
                .map(SchemaBuilder::BuildFromRequestConfig)
                .filter(e -> {
                    return !ObjectUtils.isEmpty(e) && !CollectionUtils.isEmpty(e.columnSchemaList);
                })
                .collect(Collectors.toList());
        log.info("create tableSchema from generationRequestInfoTos successfully");
        return tableSchemaList;
    }

    /**
     * 基于用户的配置请求，转换为TableSchema
     * @param generationRequestInfoTo
     * @return
     */
    public static TableSchema BuildFromRequestConfig(GenerationRequestInfoTo generationRequestInfoTo){
        log.info("Begin to BuildFromRequestConfig: [{}]", generationRequestInfoTo);
        //获取配置信息
        TableConfigRequest tableConfigRequest = generationRequestInfoTo.getTableConfigRequest();
        TableInfoEntity tableInfoEntity = generationRequestInfoTo.getTableInfoEntity();
        List<ColumnInfoEntity> columnInfoEntities = generationRequestInfoTo.getColumnInfoEntities();

        String tableName = tableInfoEntity.getTableName();
        String packageName = generationRequestInfoTo.getPackageName();
        String author = generationRequestInfoTo.getAuthor();
        String tableComment = tableInfoEntity.getTableComment();
        Map<String, ColumnInfoEntity> columnInfoEntityMap = columnInfoEntities.stream().collect(Collectors.toMap(ColumnInfoEntity::getColumnName, e -> e));

        //column配置为空，该table直接不生成
        if(CollectionUtils.isEmpty(tableConfigRequest.getColumnConfigRequests())) {
            log.warn("The column config in [{}]  is empty, will skip this table", tableName);
            return null;
        }
        //开始转换column，所有数据在tableConfigRequest中有的才处理
        List<ColumnSchema> columnSchemas = new ArrayList<>();
        for (ColumnConfigRequest columnConfigRequest : tableConfigRequest.getColumnConfigRequests()) {
            ColumnInfoEntity columnInfoEntity = columnInfoEntityMap.get(columnConfigRequest.getColumnName());
            //跳过
            if(ObjectUtils.isEmpty(columnInfoEntity)){
                log.warn(" one of the column config in [{}] is null, will skip this column", tableName);
                continue;
            }
            columnSchemas.add(BuildColumnSchema(columnConfigRequest, columnInfoEntity));
        }

        return new TableSchema(tableName, packageName, columnSchemas, tableComment, author);
    }

    /**
     * 默认全部生成
     * @param generationRequestInfoTos
     * @return
     */
    public static List<TableSchema> BuildDefaultSchema(List<GenerationRequestInfoTo> generationRequestInfoTos){
        //所有信息进行类型转换，汇聚成一个统一的 Schema
        List<TableSchema> tableSchemaList = generationRequestInfoTos.stream()
                .map(SchemaBuilder::BuildDefaultSchema)
                .filter(e -> !CollectionUtils.isEmpty(e.columnSchemaList))
                .collect(Collectors.toList());
        log.info("create tableSchema from generationRequestInfoTos successfully");
        return tableSchemaList;
    }

    public static TableSchema BuildDefaultSchema(GenerationRequestInfoTo generationRequestInfoTo){
//        TableConfigRequest tableConfigRequest = generationRequestInfoTo.getTableConfigRequest();
        TableInfoEntity tableInfoEntity = generationRequestInfoTo.getTableInfoEntity();
        List<ColumnInfoEntity> columnInfoEntities = generationRequestInfoTo.getColumnInfoEntities();

        String tableName = tableInfoEntity.getTableName();
        String packageName = generationRequestInfoTo.getPackageName();
        String author = generationRequestInfoTo.getAuthor();
        String tableComment = tableInfoEntity.getTableComment();

        List<ColumnSchema> columnSchemas = new ArrayList<>();
        for (ColumnInfoEntity columnInfoEntity : columnInfoEntities) {
            columnSchemas.add(BuildColumnSchema(columnInfoEntity));
        }
        return new TableSchema(tableName, packageName, columnSchemas, tableComment, author);
    }

    /**
     * 基于请求构建 ColumnSchema
     * @param columnConfigRequest
     * @param columnInfoEntity
     * @return
     */
    public static ColumnSchema BuildColumnSchema(ColumnConfigRequest columnConfigRequest, ColumnInfoEntity columnInfoEntity){
        DataTypeEnum dataType = DataTypeEnum.getEnumByMysqlTypeWithDefault(columnInfoEntity.getDataType());
        ColumnKeyTypeEnum columnKeyTypeEnum = ColumnKeyTypeEnum.getEnumByTypeWithDefault(columnInfoEntity.getColumnKey());
        boolean ignoreEntity = Optional.ofNullable(columnConfigRequest.getIgnoreEntity()).orElse(false);
        boolean ignoreRequest = Optional.ofNullable(columnConfigRequest.getIgnoreRequest()).orElse(false);
        boolean ignoreResponse = Optional.ofNullable(columnConfigRequest.getIgnoreRequest()).orElse(false);
        boolean ignoreVo = Optional.ofNullable(columnConfigRequest.getIgnoreVo()).orElse(false);

        return new ColumnSchema(columnInfoEntity.getColumnName(), dataType,
                columnInfoEntity.getColumnComment(), columnKeyTypeEnum,
                ignoreEntity, ignoreRequest, ignoreResponse, ignoreVo);
    }

    public static ColumnSchema BuildColumnSchema(ColumnInfoEntity columnInfoEntity){
        DataTypeEnum dataType = DataTypeEnum.getEnumByMysqlTypeWithDefault(columnInfoEntity.getDataType());
        ColumnKeyTypeEnum columnKeyTypeEnum = ColumnKeyTypeEnum.getEnumByTypeWithDefault(columnInfoEntity.getColumnKey());
        return new ColumnSchema(columnInfoEntity.getColumnName(), dataType, columnInfoEntity.getColumnComment(), columnKeyTypeEnum);
    }
}
