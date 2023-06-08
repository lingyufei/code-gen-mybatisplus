package com.lyf.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRequest {
    private String packageName;
    private String author;
    private String db;
    private OptionalConfigRequest optionalConfigRequest;
    private List<TableConfigRequest> tableConfigRequestList;
}
