package com.lyf.core.generator.template;

import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.schema.GeneralSchema;

import java.util.List;

public interface TemplateGenerator {
    /**
     * 基于 generalSchema 生成对应的 template
     * @param generalSchema
     * @param
     * @return
     */
    List<StringWriterResultBo> generate(GeneralSchema generalSchema);
}
