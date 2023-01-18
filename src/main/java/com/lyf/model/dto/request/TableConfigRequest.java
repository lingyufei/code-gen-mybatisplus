package com.lyf.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableConfigRequest {
    private String tableName;
    private List<ColumnConfigRequest> ColumnConfigRequests;
}
