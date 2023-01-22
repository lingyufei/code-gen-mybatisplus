package com.lyf.core.generator.file;

import com.lyf.core.schema.Schema;

import java.io.StringWriter;
import java.util.Optional;

public interface FileGenerator {
    /**
     * 基于文件名 生成 template
     * @param schema
     * @param fileName
     * @return
     */
    Optional<StringWriter> generate(Schema schema, String fileName);
}
