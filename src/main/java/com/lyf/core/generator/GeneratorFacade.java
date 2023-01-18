package com.lyf.core.generator;

import com.lyf.core.model.schema.TableSchema;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;

import java.util.List;

public class GeneratorFacade {

    /**
     * 门面生成方法
     * @param generationRequestInfoTos
     */
    public static void Generate(List<GenerationRequestInfoTo> generationRequestInfoTos){
        //所有信息进行类型转换，汇聚成一个统一的 Schema
    }

    /**
     * 解析，类型转换
     */
    public static TableSchema transform2TableSchema(GenerationRequestInfoTo generationRequestInfoTo){
        TableConfigRequest tableConfigRequest = generationRequestInfoTo.getTableConfigRequest();
        TableInfoEntity tableInfoEntity = generationRequestInfoTo.getTableInfoEntity();
        List<ColumnInfoEntity> columnInfoEntities = generationRequestInfoTo.getColumnInfoEntities();
        return null;
    }
}
