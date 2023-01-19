package com.lyf.utils;

import org.springframework.util.ObjectUtils;

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
}
