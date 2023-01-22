package com.lyf.core.generator;

import com.lyf.core.schema.Schema;
import com.lyf.core.schema.TableSchema;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

@Component
@Slf4j
public class FreeMarkerGenerator implements Generator{

    @Resource
    Configuration configuration;

    @Override
    public Optional<StringWriter> generate(Schema schema, String fileName) {

        Template temp = null;
        StringWriter stringWriter = new StringWriter();
        try {
            temp = configuration.getTemplate(fileName);
            temp.process(schema, stringWriter);
            log.info("generate [{}] successfully for schema [{}] ", fileName, schema);
            return Optional.of(stringWriter);
        } catch (IOException | TemplateException e) {
            log.warn("FreeMarkerGenerator processing [{}] exception, [{}]", fileName, e);
            return Optional.empty();
        }
    }
}
