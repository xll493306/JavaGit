package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.modules.sys.entity.ViewLogEntity;
import io.renren.modules.sys.service.ViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://172.30.145.222:8080", allowCredentials = "true")
@RestController
@RequestMapping("/view-log")
public class ViewLogController {
    @Autowired
    private ViewLogService viewLogService;
    @PostMapping("/save")
    public R save(@RequestBody ViewLogEntity viewLogEntity){
        if(viewLogService.findById(viewLogEntity.getShortVideoId(), viewLogEntity.getOpenId()) == null) {
            viewLogService.saveViewLog(viewLogEntity);
            return R.ok(201,"新增成功");
        }
        return R.error(400, "已存在日志");
    }
    @GetMapping("/info")
    public R getInfo(@RequestParam("shortVideoId") Long shortVideoId, @RequestParam("openId") String openId){
        ViewLogEntity viewLog = viewLogService.findById(shortVideoId,openId);
        return R.ok("获取成功").put("viewLog",viewLog);
    }
}
