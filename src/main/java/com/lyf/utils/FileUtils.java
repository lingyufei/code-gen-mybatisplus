package com.lyf.utils;

import com.lyf.constant.ExceptionCodeEnum;
import com.lyf.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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

    /**
     *
     * @param templateName  ${entity}Dao.java.ftl
     * @param enumFileName  Dao.java / ""(default)
     * @return
     */
    public static String GetRealFileName(String templateName, String enumFileName){
        if(StringUtils.hasText(enumFileName)){
            return enumFileName;
        }else{
            int index = templateName.lastIndexOf(".ftl");
            if(index > -1){
                templateName = templateName.substring(0, index);
            }
            return templateName;
        }
    }

    /**
     *
     * @param templateName  ${entity}Dao.java.ftl
     * @return
     */
    public static String GetGenerationFileName(String templateName){
        if(!StringUtils.hasText(templateName)){
            return "";
        }
        int index = templateName.lastIndexOf(".ftl");
        if(index > -1){
            templateName = templateName.substring(0, index);
        }
        return templateName;
    }

    public static String GetRelativePath(File child, File parent){
        return parent.toURI().relativize(child.toURI()).getPath();
    }

    public static void ResponseFileToHttp(byte[] data, String fileName, HttpServletResponse response){
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        try {
            IOUtils.write(data, response.getOutputStream());
        }catch (IOException e) {
            response.reset();
            throw new BusinessException("", ExceptionCodeEnum.GEN_FILE_OUTPUT_EXCEPTION, e);
        }
    }
}
