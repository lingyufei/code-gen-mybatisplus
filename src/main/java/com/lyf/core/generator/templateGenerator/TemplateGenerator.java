package com.lyf.core.generator.templateGenerator;

import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.schema.GeneralSchema;
import com.lyf.core.schema.Schema;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

public interface TemplateGenerator {
    /**
     * 基于 generalSchema 生成对应的 template
     * @param generalSchema
     * @param
     * @return
     */
    List<StringWriterResultBo> generate(GeneralSchema generalSchema);
}
