package com.lyf.core.generator;

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

//todo 改为非 ioc  implements Generator
@Component
@Slf4j
public class FreeMarkerGenerator{

//    @Resource
//    Configuration configuration;
    private static Configuration Configuration;

    static {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File(FREEMARKER_TEMPLATE_FOLDER));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            Configuration = cfg;
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
