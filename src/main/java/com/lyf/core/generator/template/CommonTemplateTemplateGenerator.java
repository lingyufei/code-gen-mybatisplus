package com.lyf.core.generator.template;

import com.lyf.constant.Constant;
import com.lyf.core.generator.file.FreeMarkerGenerator;
import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.schema.GeneralSchema;
import com.lyf.core.schema.TableSchema;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class CommonTemplateTemplateGenerator extends AbstractTemplateGenerator {

    public static final String Folder = Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_PATH;


    @Override
    public List<StringWriterResultBo> generate(GeneralSchema generalSchema) {
        log.info("begin to generateCommonTemplates");
        List<TableSchema> tableSchemaList = generalSchema.getTableSchemaList();
        String packagePath = tableSchemaList.get(0).getPackagePath();

        List<StringWriterResultBo> result = new ArrayList<>();
        Map<String, File> fileMap = getFileMapUnderFolder(Folder);
        for (Map.Entry<String, File> entry : fileMap.entrySet()) {
            File file = entry.getValue();
            ///xxxx/${packageName}/xxx/R.java
            //Final name
            String generationPath = entry.getKey().replaceAll("\\$\\{packageName}", packagePath);

            Optional<StringWriter> optional = FreeMarkerGenerator.Generate(tableSchemaList.get(0), FileUtils.GetRelativePath(file, new File(Constant.FREEMARKER_TEMPLATE_FOLDER)));
            optional.ifPresent(e ->{
                result.add(new StringWriterResultBo(generationPath, e));
            });
        }
        return result;
    }
}
