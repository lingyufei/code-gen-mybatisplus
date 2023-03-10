package com.lyf.core.generator;

import com.lyf.constant.Constant;
import com.lyf.core.generator.file.FreeMarkerGenerator;
import com.lyf.core.generator.template.TemplateGenerator;
import com.lyf.core.generator.template.TemplateGeneratorFactory;
import com.lyf.core.model.bo.GenerationInfoBo;
import com.lyf.core.model.bo.OptionalGenerationBo;
import com.lyf.core.model.enums.FilePathEnum;
import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.schema.GeneralSchema;
import com.lyf.core.schema.OptionalSchema;
import com.lyf.core.schema.SchemaBuilder;
import com.lyf.core.schema.TableSchema;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.*;

@Slf4j
@Component
public class GeneratorFacade{
    /**
     * 门面生成方法
     * @param generationInfoBo
     * @return filePath -> StringWriter
     */
    public List<StringWriterResultBo> generateByRequest(GenerationInfoBo generationInfoBo){
        //开始生成
        return doGenerate(SchemaBuilder.BuildFromRequestConfig(generationInfoBo));
    }


    public List<StringWriterResultBo> generateByDefault(GenerationInfoBo generationInfoBo){
        return doGenerate(SchemaBuilder.BuildDefaultSchema(generationInfoBo));
    }


    public List<StringWriterResultBo> doGenerate(GeneralSchema generalSchema){
        List<StringWriterResultBo> result = new ArrayList<>();

        for (TemplateGenerator templateGenerator : TemplateGeneratorFactory.GetAllGenerator()) {
            result.addAll(templateGenerator.generate(generalSchema));
        }
        return result;
    }

    /**
     * 基于 Enum 指定文件生成路径，过时！现在使用文件路径系统解析
     * @param tableSchemaList
     * @return
     */
    @Deprecated
    public List<StringWriterResultBo> doGenerateByEnumMap(List<TableSchema> tableSchemaList){
        List<StringWriterResultBo> result = new ArrayList<>();

        //FreeMarker生成
        for (TableSchema tableSchema : tableSchemaList) {
            List<String> fileNames = FileUtils.GetFileNameUnderFolder(Constant.FREEMARKER_TEMPLATE_FOLDER);
            for (String file: fileNames){
                FilePathEnum filePathEnum = FilePathEnum.getEnumByFileNameWithDefault(file);
                String path = Constant.BASE_PATH + filePathEnum.getFilePath().replaceAll("\\$\\{packageName}", tableSchema.getPackagePath())
                        + tableSchema.getUpperCamelName() + FileUtils.GetRealFileName(file, filePathEnum.getFileName());;

                Optional<StringWriter> optional = FreeMarkerGenerator.Generate(tableSchema, file);
                optional.ifPresent(e ->{
                    result.add(new StringWriterResultBo(path, e));
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

            Optional<StringWriter> optional = FreeMarkerGenerator.Generate(tableSchema, Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_NAME + commonFile);
            optional.ifPresent(e ->{
                result.add(new StringWriterResultBo(path, e));
            });
        }
        return result;
    }

}
