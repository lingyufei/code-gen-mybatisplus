package com.lyf.core.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 可选项目Schema
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionalSchema implements Schema{
    //线程池
    private Boolean ignoreThreadPool = false;

    //日志aop打印
    private Boolean ignoreLogInterceptor = false;
}
