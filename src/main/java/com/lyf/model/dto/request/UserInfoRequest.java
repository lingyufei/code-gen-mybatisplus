package com.lyf.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * user_info Request
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-01-17 09:29:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {
    public Long id;
    public String username;
    public String password;
    public String salt;
    public String email;
    public String mobile;
    public Integer status;
    public Date createTime;
}
