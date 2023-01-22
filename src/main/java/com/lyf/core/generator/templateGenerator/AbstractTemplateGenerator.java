package com.lyf.core.generator.templateGenerator;

import com.lyf.constant.Constant;
import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.schema.GeneralSchema;
import com.lyf.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class AbstractTemplateGenerator implements TemplateGenerator {
    @Override
    public abstract List<StringWriterResultBo> generate(GeneralSchema generalSchema);

    protected Map<String, File> getFileMapUnderFolder(String folder){
        File rootFolder = new File(folder);
        Map<String, File> fileMap = new ConcurrentHashMap<>();
        getTemplatesUnderFolder(rootFolder, rootFolder, fileMap);
        log.info("getFileMapUnderFolder [{}], file: [{}]", folder, fileMap.keySet());
        return fileMap;
    }

    /**
     * dfs 获取文件夹下全部模板
     * @param folder
     * @param root
     * @param map 模板生成路径 -> 文件
     */
    protected void getTemplatesUnderFolder(File folder, File root, Map<String, File> map){
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


}
