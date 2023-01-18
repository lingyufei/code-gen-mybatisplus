package com.lyf.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyf.model.dto.request.UserInfoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * user_info entity
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-01-17 09:29:27
 */
@Data
@TableName("user_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * xxxxxx
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String salt;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createTime;

	public UserInfo(UserInfoRequest source){
		setId(source.getId());
		setUsername(source.getUsername());
		setPassword(source.getPassword());
		setSalt(source.getSalt());
		setEmail(source.getEmail());
		setMobile(source.getMobile());
		setStatus(source.getStatus());
		setCreateTime(source.getCreateTime());
	}

}
