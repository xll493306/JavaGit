package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.modules.sys.service.WxOpenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://172.30.145.222:8080", allowCredentials = "true")
@RestController
public class WxOpenIdController {
    @Autowired
    WxOpenIdService wxOpenIdService;
    @GetMapping("/weixin/openId")
    public R getOpenId(@RequestParam(value = "code")String code){
//        Map<String,String> openId = wxOpenIdService.getOpenId(code);
        return R.ok("获取成功").put("openId", UUID.randomUUID());
    }
}
