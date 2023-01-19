package com.lyf.core.generator;

import com.lyf.constant.Constant;
import com.lyf.core.model.enums.FilePathEnum;
import com.lyf.core.model.to.StringWriterResultTo;
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
    public Optional<StringWriterResultTo> generate(TableSchema tableSchema, String fileName) {
        FilePathEnum filePathEnum = FilePathEnum.getEnumByFileNameWithDefault(fileName);
        String path = Constant.BASE_PATH + filePathEnum.getFilePath().replaceAll("\\$\\{packageName}", tableSchema.getPackagePath());

        Template temp = null;
        StringWriter stringWriter = new StringWriter();
        try {
            temp = configuration.getTemplate(fileName);
            temp.process(tableSchema, stringWriter);

            //处理文件名


            return Optional.of(new StringWriterResultTo(path, stringWriter));
        } catch (IOException | TemplateException e) {
            log.warn("[{}] processing exception, [{}]", fileName, e);
            return Optional.empty();
        }

    }
}
