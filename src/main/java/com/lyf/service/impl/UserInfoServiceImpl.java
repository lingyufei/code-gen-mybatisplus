package com.lyf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyf.dao.UserInfoDao;
import com.lyf.model.dto.request.PageRequest;
import com.lyf.model.dto.request.UserInfoRequest;
import com.lyf.model.dto.response.PageResponse;
import com.lyf.model.dto.response.UserInfoResponse;
import com.lyf.model.entity.UserInfo;
import com.lyf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    public PageResponse<UserInfoResponse> queryPage(PageRequest pageRequest) {
        try(Page<?> page = PageHelper.startPage(pageRequest.getPage(), pageRequest.getLimit())){
            List<UserInfo> userInfos = this.list(new QueryWrapper<UserInfo>()
                    .orderBy(true, !pageRequest.getDesc(), "id"));

            //类型转换
            List<UserInfoResponse> userInfoResponseList = userInfos.stream().map(UserInfoResponse::new).collect(Collectors.toList());
            return new PageResponse<>(userInfoResponseList, page.getTotal(), pageRequest.getLimit(), pageRequest.getPage(), pageRequest.getDesc());
        }
    }

    @Override
    public UserInfoResponse getInfo(Long id) {
        UserInfo userInfo = super.getById(id);
        return new UserInfoResponse(userInfo);
    }

    @Override
    public boolean save(UserInfoRequest userInfoRequest) {
        UserInfo userInfo = new UserInfo(userInfoRequest);
        return super.save(userInfo);
    }

    @Override
    public boolean updateById(UserInfoRequest userInfoRequest) {
        UserInfo userInfo = new UserInfo(userInfoRequest);
        return super.updateById(userInfo);
    }
}