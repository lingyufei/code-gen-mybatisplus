package com.lyf.core.generator;

import com.lyf.constant.Constant;
import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.SchemaBuilder;
import com.lyf.core.schema.TableSchema;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class GeneratorFacade implements Generator{
    @Resource
    FreeMarkerGenerator freeMarkerGenerator;

    /**
     * 门面生成方法
     * @param generationRequestInfoTos
     * @return filePath -> StringWriter
     */
    public List<StringWriterResultTo> generateByRequest(List<GenerationRequestInfoTo> generationRequestInfoTos){
        List<TableSchema> tableSchemas = SchemaBuilder.BuildFromRequestConfig(generationRequestInfoTos);

        return doGenerate(tableSchemas);
    }

    public List<StringWriterResultTo> generateByDefault(List<GenerationRequestInfoTo> generationRequestInfoTos){
        List<TableSchema> tableSchemas = SchemaBuilder.BuildDefaultSchema(generationRequestInfoTos);

        return doGenerate(tableSchemas);
    }

    public List<StringWriterResultTo> doGenerate(List<TableSchema> tableSchemaList){
        List<StringWriterResultTo> result = new ArrayList<>();

        //FreeMarker生成
        for (TableSchema tableSchema : tableSchemaList) {
            List<String> fileNames = FileUtils.GetFileNameUnderFolder(Constant.FREEMARKER_TEMPLATE_FOLDER);
            for (String file: fileNames){
                Optional<StringWriterResultTo> toOptional = generate(tableSchema, file);
                toOptional.ifPresent(result::add);
            }
        }

        //其他生成xxxx

        return result;
    }

    @Override
    public Optional<StringWriterResultTo> generate(TableSchema tableSchema, String fileName) {
        return freeMarkerGenerator.generate(tableSchema, fileName);
    }
}
