package ${packageName}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.model.entity.${upperCamelName};
import org.apache.ibatis.annotations.Mapper;

/**
* ${tableName} Mapper
* @author LYF
* @email 1187112693@qq.com
* @date 2023-01-17 09:29:27
*/
@Mapper
public interface UserInfoDao extends BaseMapper<${upperCamelName}> {

}