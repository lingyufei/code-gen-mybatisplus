package com.lyf.core.generator;

import com.lyf.constant.Constant;
import com.lyf.core.model.bo.StringWriterResultBo;
import com.lyf.core.model.enums.FilePathEnum;
import com.lyf.core.schema.Schema;
import com.lyf.core.schema.TableSchema;
import com.lyf.utils.FileUtils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface Generator {
    /**
     * 基于文件名 生成 template
     * @param schema
     * @param fileName
     * @return
     */
    Optional<StringWriter> generate(Schema schema, String fileName);
}
