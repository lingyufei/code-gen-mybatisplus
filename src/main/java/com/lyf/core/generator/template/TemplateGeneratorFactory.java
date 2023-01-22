package com.lyf.core.generator.template;

import com.lyf.constant.Constant;

import java.util.*;

public class TemplateGeneratorFactory {

    private static final Map<String, TemplateGenerator> GeneratorMap;

    static {
        GeneratorMap = new HashMap<>(3);
        GeneratorMap.put(Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_NAME, new CommonTemplateTemplateGenerator());
        GeneratorMap.put(Constant.FREEMARKER_TEMPLATE_SPECIFIED_FOLDER_NAME, new SpecifiedTemplateTemplateGenerator());
        GeneratorMap.put(Constant.FREEMARKER_TEMPLATE_OPTIONAL_FOLDER_NAME, new OptionalTemplateTemplateGenerator());
    }

    /**
     * 基于模板类型获取相应模板生成类。默认使用Common (common 对每个 template 只生成一次)
     * @param mode
     * @return
     */
    public static TemplateGenerator GetGeneratorByMode(String mode){
        return Optional.ofNullable(GeneratorMap.get(mode))
                .orElse(GeneratorMap.get(Constant.FREEMARKER_TEMPLATE_COMMON_FOLDER_NAME));
    }

    public static Collection<TemplateGenerator> GetAllGenerator(){
        return GeneratorMap.values();
    }
}
