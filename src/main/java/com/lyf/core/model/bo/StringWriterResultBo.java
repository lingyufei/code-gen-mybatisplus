package com.lyf.core.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.StringWriter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StringWriterResultBo {
    String path;
    StringWriter stringWriter;
}
