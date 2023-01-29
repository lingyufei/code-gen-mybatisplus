package com.lyf.core.schema;

import com.lyf.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 通用生成配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralSchema implements Schema{

    private String author;

    private String packageName;

    private OptionalSchema optionalSchema;

    private List<TableSchema> tableSchemaList;

    public GeneralSchema(String author, String packageName, List<TableSchema> tableSchemaList) {
        this.author = author;
        this.packageName = packageName;
        this.tableSchemaList = tableSchemaList;
        this.optionalSchema = new OptionalSchema();
    }

    public OptionalSchema getOptionalSchema() {
        return Optional.ofNullable(optionalSchema).orElse(new OptionalSchema());
    }

    public String getPackagePath() {
        if(org.springframework.util.StringUtils.hasText(packageName)){
            return packageName.replace(".", "/");
        }else{
            return  Constant.DEFAULT_PACKAGE.replace(".", "/");
        }
    }
}
