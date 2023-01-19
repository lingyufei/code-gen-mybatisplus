package com.lyf.core.model.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.StringWriter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringWriterResultTo {
    String path;
    StringWriter stringWriter;
}
