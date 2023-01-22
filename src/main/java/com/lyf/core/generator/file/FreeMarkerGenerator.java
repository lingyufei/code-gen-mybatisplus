package com.lyf.core.generator.file;

import com.lyf.config.FreeMarkerConfig;
import com.lyf.core.schema.Schema;
import com.lyf.core.schema.TableSchema;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

import static com.lyf.constant.Constant.FREEMARKER_TEMPLATE_FOLDER;

//todo 实现接口
@Slf4j
public class FreeMarkerGenerator{

//    @Resource
//    Configuration configuration;
    private static Configuration Configuration;

    static {
        try {
            Configuration = FreeMarkerConfig.Configuration();
        } catch (IOException e) {
            log.warn("Fail to load Configuration for FreeMarkerGenerator, error: + ", e);
        }
    }

//    @Override
    public static  Optional<StringWriter> Generate(Schema schema, String fileName) {

        Template temp = null;
        StringWriter stringWriter = new StringWriter();
        try {
            temp = Configuration.getTemplate(fileName);
            temp.process(schema, stringWriter);
            log.info("generate [{}] successfully for schema [{}] ", fileName, schema);
            return Optional.of(stringWriter);
        } catch (IOException | TemplateException e) {
            log.warn("FreeMarkerGenerator processing [{}] exception, [{}]", fileName, e);
            return Optional.empty();
        }
    }
}
