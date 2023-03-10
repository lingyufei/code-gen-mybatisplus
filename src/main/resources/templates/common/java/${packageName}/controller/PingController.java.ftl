package ${packageName}.controller;

import ${packageName}.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* ping测试
* @author ${author}
**/
@RestController
public class PingController {

    @GetMapping("/ping")
    public R ping(){
        return R.ok().put("data", "pong");
    }
}
