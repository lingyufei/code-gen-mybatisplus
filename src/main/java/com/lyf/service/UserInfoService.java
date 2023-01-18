package com.lyf.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.model.dto.request.PageRequest;
import com.lyf.model.dto.request.UserInfoRequest;
import com.lyf.model.dto.response.PageResponse;
import com.lyf.model.dto.response.UserInfoResponse;
import com.lyf.model.entity.UserInfo;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-01-17 09:29:27
 */
public interface UserInfoService extends IService<UserInfo>{

    public PageResponse<UserInfoResponse> queryPage(PageRequest pageRequest);

    UserInfoResponse getInfo(Long id);

    boolean save(UserInfoRequest userInfoRequest);

    boolean updateById(UserInfoRequest userInfoRequest);
}

