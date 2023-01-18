package com.lyf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyf.model.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserInfo Mapper
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-01-17 09:29:27
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

    List<UserInfo> fuzzyQueryByUsername(@Param("username") String username);
}