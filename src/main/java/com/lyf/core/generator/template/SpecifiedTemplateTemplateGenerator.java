package com.lyf.core.generator.template;

import com.lyf.constant.Constant;
import com.lyf.core.generator.file.FreeMarkerGenerator;
import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.schema.GeneralSchema;
import com.lyf.core.schema.TableSchema;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class SpecifiedTemplateTemplateGenerator extends AbstractTemplateGenerator {
    @Resource
    FreeMarkerGenerator freeMarkerGenerator;
    //固定路径
    public static final String Folder = Constant.FREEMARKER_TEMPLATE_SPECIFIED_FOLDER_PATH;

    @Override
    public List<StringWriterResultBo> generate(GeneralSchema generalSchema) {
        log.info("begin to generateSpecifiedTemplatesUnderFolder");
        List<TableSchema> tableSchemaList = generalSchema.getTableSchemaList();

        List<StringWriterResultBo> result = new ArrayList<>();
        Map<String, File> fileMap = getFileMapUnderFolder(Folder);
        for (TableSchema tableSchema: tableSchemaList){
            log.info("generateSpecifiedTemplates for [{}]", tableSchema.getTableName());
            String packagePath = tableSchema.getPackagePath();
            String entityName = tableSchema.getUpperCamelName();
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                //添加Table信息 /xxxx/${packageName}/xxx/${entity}Dao.xml
                //Final name
                String generationPath = entry.getKey().replaceAll("\\$\\{packageName}", packagePath)
                        .replaceAll("\\$\\{entity}", entityName);

                Optional<StringWriter> optional = FreeMarkerGenerator.Generate(tableSchema, FileUtils.GetRelativePath(file, new File(Constant.FREEMARKER_TEMPLATE_FOLDER)));
                optional.ifPresent(e ->{
                    result.add(new StringWriterResultBo(generationPath, e));
                });
            }
        }
        return result;
    }
}
