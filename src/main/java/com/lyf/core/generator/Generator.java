package com.lyf.core.generator;

import com.lyf.core.model.to.StringWriterResultTo;
import com.lyf.core.schema.TableSchema;

import java.util.List;

public interface Generator {
    List<StringWriterResultTo> generate(TableSchema tableSchema);
}
