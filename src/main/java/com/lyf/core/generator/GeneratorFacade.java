package com.lyf.core.generator;

import com.lyf.core.model.enums.GeneratorEnum;
import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.SchemaBuilder;
import com.lyf.core.schema.TableSchema;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GeneratorFacade{
    @Resource
    GeneratorFactory generatorFactory;

    /**
     * 门面生成方法
     * @param generationRequestInfoTos
     * @return filePath -> StringWriter
     */
    public List<StringWriterResultTo> generateByRequest(List<GenerationRequestInfoTo> generationRequestInfoTos){
        return generateByRequest(generationRequestInfoTos, GeneratorEnum.GetDefaultGeneratorEnum());
    }

    public List<StringWriterResultTo> generateByRequest(List<GenerationRequestInfoTo> generationRequestInfoTos, GeneratorEnum generatorEnum){
        List<TableSchema> tableSchemas = SchemaBuilder.BuildFromRequestConfig(generationRequestInfoTos);

        return doGenerate(tableSchemas, generatorFactory.getGeneratorByMode(generatorEnum));
    }

    public List<StringWriterResultTo> doGenerate(List<TableSchema> tableSchemaList, Generator generator){
        List<StringWriterResultTo> result = new ArrayList<>();

        for (TableSchema tableSchema : tableSchemaList) {
            List<StringWriterResultTo> resultTos = generate(tableSchema, generator);
            result.addAll(resultTos);
        }
        return result;
    }

    public List<StringWriterResultTo> generate(TableSchema tableSchema, Generator generator) {
        return generator.generate(tableSchema);
    }
}
