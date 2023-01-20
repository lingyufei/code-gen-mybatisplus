package com.lyf.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> GetFileNameUnderFolder(String path){
        List<String> result = new ArrayList<>();
        File file = new File(path);
        if(!file.exists()){
            return result;
        }

        File[] files = file.listFiles();
        if(ObjectUtils.isEmpty(files)){
            return result;
        }
        for (File subFile : files) {
            if(subFile.isFile()){
                result.add(subFile.getName());
            }
        }
        return result;
    }

    /**
     * /a/b/c.java -> c.java
     * @param fileWithPath
     * @return
     */
    public static String GetFileName(String fileWithPath){
        if(!StringUtils.hasText(fileWithPath)){
            return fileWithPath;
        }

        int slashIndex = fileWithPath.lastIndexOf("/");
        if(slashIndex > -1){
            fileWithPath = fileWithPath.substring(slashIndex + 1);
        }
        return fileWithPath;
    }
}
