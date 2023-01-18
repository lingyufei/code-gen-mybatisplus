package com.lyf.controller;

import com.lyf.model.dto.request.PageRequest;
import com.lyf.model.dto.request.UserInfoRequest;
import com.lyf.model.dto.response.PageResponse;
import com.lyf.model.dto.response.UserInfoResponse;
import com.lyf.service.UserInfoService;
import com.lyf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-01-17 09:29:27
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageRequest pageRequest = new PageRequest(params);
        PageResponse<UserInfoResponse> page = userInfoService.queryPage(pageRequest);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UserInfoResponse userInfoResponse = userInfoService.getInfo(id);

        return R.ok().put("data", userInfoResponse);
    }

    /**
     * 保存
     */
    @PutMapping("/save")
    public R save(@RequestBody UserInfoRequest userInfoRequest){
		userInfoService.save(userInfoRequest);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody UserInfoRequest userInfoRequest){
		userInfoService.updateById(userInfoRequest);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public R deleteById(@PathVariable Long id){
		userInfoService.removeById(id);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/deleteBatch")
    public R delete(@RequestBody Long[] ids){
        userInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
