package com.lyf.core.generator;

import com.lyf.constant.Constant;
import com.lyf.core.model.enums.FilePathEnum;
import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.SchemaBuilder;
import com.lyf.core.schema.TableSchema;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.StringWriter;
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
                FilePathEnum filePathEnum = FilePathEnum.getEnumByFileNameWithDefault(file);
                String path = Constant.BASE_PATH + filePathEnum.getFilePath().replaceAll("\\$\\{packageName}", tableSchema.getPackagePath())
                        + tableSchema.getUpperCamelName() + FileUtils.GetRealFileName(file, filePathEnum.getFileName());;

                Optional<StringWriter> optional = generate(tableSchema, file);
                optional.ifPresent(e ->{
                    result.add(new StringWriterResultTo(path, e));
                });
            }
        }

        //其他生成common类
        List<String> commonFiles = FileUtils.GetFileNameUnderFolder(Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_PATH);
        TableSchema tableSchema = tableSchemaList.get(0);
        for (String commonFile : commonFiles) {
            FilePathEnum filePathEnum = FilePathEnum.getEnumByFileNameWithDefault(commonFile);
            String path = Constant.BASE_PATH + filePathEnum.getFilePath().replaceAll("\\$\\{packageName}", tableSchema.getPackagePath())
                                             + FileUtils.GetRealFileName(commonFile, filePathEnum.getFileName());

            Optional<StringWriter> optional = generate(tableSchema, Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_NAME + commonFile);
            optional.ifPresent(e ->{
                result.add(new StringWriterResultTo(path, e));
            });
        }
        return result;
    }

    @Override
    public Optional<StringWriter> generate(TableSchema tableSchema, String fileName) {
        return freeMarkerGenerator.generate(tableSchema, fileName);
    }
}
