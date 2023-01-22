package com.lyf.core.generator;

import com.lyf.core.schema.Schema;
import com.lyf.core.schema.TableSchema;

import java.io.StringWriter;
import java.util.Optional;

public interface Generator {
    Optional<StringWriter> generate(Schema schema, String fileName);
}
