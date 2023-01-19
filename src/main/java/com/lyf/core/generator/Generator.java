package com.lyf.core.generator;

import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.TableSchema;

import java.util.List;
import java.util.Optional;

public interface Generator {
    Optional<StringWriterResultTo> generate(TableSchema tableSchema, String fileName);
}
