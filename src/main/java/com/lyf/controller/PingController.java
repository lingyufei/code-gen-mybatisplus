package com.lyf.controller;

import com.lyf.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public R ping(){
        return R.ok().put("data", "pong");
    }
}
