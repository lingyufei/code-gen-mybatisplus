package com.lyf.core.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionalGenerationBo {
    //线程池
    private Boolean ignoreThreadPool = false;

    //日志aop打印
    private Boolean ignoreLogInterceptor = false;
}
