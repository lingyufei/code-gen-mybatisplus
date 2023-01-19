package com.lyf.core.generator;

import com.lyf.core.model.enums.GeneratorEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GeneratorFactory {
    @Resource
    private FreeMarkerGenerator freeMarkerGenerator;

    public Generator getGeneratorByMode(GeneratorEnum generatorEnum){
        if(generatorEnum.equals(GeneratorEnum.FREE_MARKER)){
            return freeMarkerGenerator;
        }else{
            return freeMarkerGenerator;
        }

    }
}
