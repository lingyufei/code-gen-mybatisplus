package ${packageName}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.model.entity.${upperCamelName};
import org.apache.ibatis.annotations.Mapper;

/**
* ${tableName} Mapper
* @author ${author}
*/
@Mapper
public interface ${upperCamelName}Dao extends BaseMapper<${upperCamelName}> {

}