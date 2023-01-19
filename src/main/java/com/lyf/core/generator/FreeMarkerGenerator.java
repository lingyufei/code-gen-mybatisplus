package com.lyf.core.generator;

import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.TableSchema;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FreeMarkerGenerator implements Generator{
    @Override
    public List<StringWriterResultTo> generate(TableSchema tableSchema) {
        return null;
    }
}
