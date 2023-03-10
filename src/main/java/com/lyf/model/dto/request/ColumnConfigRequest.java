package com.lyf.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnConfigRequest {
    private String columnName;
    private Boolean ignoreEntity = false;
    private Boolean ignoreRequest = false;
    private Boolean ignoreResponse= false;
    private Boolean ignoreBo= false;
}
