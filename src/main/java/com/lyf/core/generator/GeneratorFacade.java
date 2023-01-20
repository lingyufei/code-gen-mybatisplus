package com.lyf.core.generator;

import com.lyf.constant.Constant;
import com.lyf.core.model.enums.FilePathEnum;
import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.SchemaBuilder;
import com.lyf.core.schema.TableSchema;
import com.lyf.core.model.to.GenerationRequestInfoTo;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class GeneratorFacade{
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

        //FreeMarker生成 entity相关所有类
        result.addAll(generateSpecifiedTemplatesUnderFolder(Constant.FREEMARKER_TEMPLATE_SPECIFIED_FOLDER_PATH, tableSchemaList));

        //生成所有common
        result.addAll(generateCommonTemplatesUnderFolder(Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_PATH, new ArrayList<>(Collections.singleton(tableSchemaList.get(0)))));

        return result;
    }

    /**
     * 生成指定文件夹下全部模板
     * @param folder 文件夹
     * @param tableSchemaList
     * @return 结果集
     */
    private List<StringWriterResultTo> generateCommonTemplatesUnderFolder(String folder, List<TableSchema> tableSchemaList){
        List<StringWriterResultTo> result = new ArrayList<>();

        Map<String, File> fileMap = getFileMapUnderFolder(folder);

        String packagePath = tableSchemaList.get(0).getPackagePath();
        for (Map.Entry<String, File> entry : fileMap.entrySet()) {
            File file = entry.getValue();
            ///xxxx/${packageName}/xxx/R.java
            //Final name
            String generationPath = entry.getKey().replaceAll("\\$\\{packageName}", packagePath);

            Optional<StringWriter> optional = freeMarkerGenerator.generate(tableSchemaList.get(0), FileUtils.GetRelativePath(file, new File(Constant.FREEMARKER_TEMPLATE_FOLDER)));
            optional.ifPresent(e ->{
                result.add(new StringWriterResultTo(generationPath, e));
            });
        }

        return result;
    }

    private List<StringWriterResultTo> generateSpecifiedTemplatesUnderFolder(String folder, Collection<TableSchema> tableSchemaList){
        List<StringWriterResultTo> result = new ArrayList<>();

        Map<String, File> fileMap = getFileMapUnderFolder(folder);
        for (TableSchema tableSchema: tableSchemaList){
            String packagePath = tableSchema.getPackagePath();
            String entityName = tableSchema.getUpperCamelName();
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                //添加Table信息 /xxxx/${packageName}/xxx/${entity}Dao.xml
                //Final name
                String generationPath = entry.getKey().replaceAll("\\$\\{packageName}", packagePath)
                        .replaceAll("\\$\\{entity}", entityName);

                Optional<StringWriter> optional = freeMarkerGenerator.generate(tableSchema, FileUtils.GetRelativePath(file, new File(Constant.FREEMARKER_TEMPLATE_FOLDER)));
                optional.ifPresent(e ->{
                    result.add(new StringWriterResultTo(generationPath, e));
                });
            }
        }
        return result;
    }


    /**
     * 获取文件夹下所有文件
     * @param folder
     * @return 模板生成路径 -> 文件
     */
    private Map<String, File> getFileMapUnderFolder(String folder){
        File rootFolder = new File(folder);
        Map<String, File> fileMap = new ConcurrentHashMap<>();
        getTemplatesUnderFolder(rootFolder, rootFolder, fileMap);
        return fileMap;
    }

    /**
     * dfs 获取文件夹下全部模板
     * @param folder
     * @param root
     * @param map 模板生成路径 -> 文件
     */
    private void getTemplatesUnderFolder(File folder, File root, Map<String, File> map){
        if(!folder.exists()){
            return;
        }

        File[] files = folder.listFiles();
        if(ArrayUtils.isEmpty(files)){
            return;
        }

        for (File subFile : files) {
            if(subFile.isDirectory()){
                getTemplatesUnderFolder(subFile, root, map);
            }else{
                //计算相对路径 (/controller/${entity}Controller.java.ftl)
                String relativePath = FileUtils.GetRelativePath(subFile, root);
                //去除.ftl。文件输出的路径 (/src/man/java/${packageName}/controller/Controller.java)
                String path = Constant.BASE_PATH + FileUtils.GetGenerationFileName(relativePath);
                map.put(path, subFile);
            }
        }
    }


    /**
     * 基于 Enum 指定文件生成路径，过时！现在使用文件路径系统解析
     * @param tableSchemaList
     * @return
     */
    @Deprecated
    public List<StringWriterResultTo> doGenerateByEnumMap(List<TableSchema> tableSchemaList){
        List<StringWriterResultTo> result = new ArrayList<>();

        //FreeMarker生成
        for (TableSchema tableSchema : tableSchemaList) {
            List<String> fileNames = FileUtils.GetFileNameUnderFolder(Constant.FREEMARKER_TEMPLATE_FOLDER);
            for (String file: fileNames){
                FilePathEnum filePathEnum = FilePathEnum.getEnumByFileNameWithDefault(file);
                String path = Constant.BASE_PATH + filePathEnum.getFilePath().replaceAll("\\$\\{packageName}", tableSchema.getPackagePath())
                        + tableSchema.getUpperCamelName() + FileUtils.GetRealFileName(file, filePathEnum.getFileName());;

                Optional<StringWriter> optional = freeMarkerGenerator.generate(tableSchema, file);
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

            Optional<StringWriter> optional = freeMarkerGenerator.generate(tableSchema, Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_NAME + commonFile);
            optional.ifPresent(e ->{
                result.add(new StringWriterResultTo(path, e));
            });
        }
        return result;
    }

}
