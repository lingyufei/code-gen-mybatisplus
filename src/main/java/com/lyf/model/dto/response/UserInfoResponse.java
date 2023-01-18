package com.lyf.model.dto.response;

import com.lyf.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * user_info Response
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-01-17 09:29:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    public Long id;
    public String username;
    public String email;
    public String mobile;
    public Integer status;
    public Date createTime;

    public UserInfoResponse(UserInfo source){
        setId(source.getId());
        setUsername(source.getUsername());
        setEmail(source.getEmail());
        setMobile(source.getMobile());
        setStatus(source.getStatus());
        setCreateTime(source.getCreateTime());
    }
}
