package com.lyf.core.model.bo;

import com.lyf.model.dto.request.TableConfigRequest;
import com.lyf.model.entity.ColumnInfoEntity;
import com.lyf.model.entity.TableInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerationInfoBo {
    private String tableName;
    private String packageName;
    private String author;
    private OptionalGenerationBo optionalGenerationBo;

    List<TableGenerationInfoBo> tableGenerationInfoBoList;
}
