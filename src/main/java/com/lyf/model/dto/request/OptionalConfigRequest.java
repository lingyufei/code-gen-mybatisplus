package com.lyf.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionalConfigRequest {
    //线程池
    private Boolean ignoreThreadPool = false;

    //日志aop打印
    private Boolean ignoreLogInterceptor = false;

    //Redis配置
    private Boolean ignoreRedis = false;
}
