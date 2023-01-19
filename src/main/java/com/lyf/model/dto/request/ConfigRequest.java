package com.lyf.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRequest {
    private String packageName;
    private String author;
    List<TableConfigRequest> tableConfigRequestList;
}