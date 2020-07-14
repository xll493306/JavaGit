package io.renren.modules.sys.controller;

import io.renren.common.utils.R;
import io.renren.modules.sys.entity.LikeLogEntity;
import io.renren.modules.sys.service.LikeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://172.30.145.222:8080", allowCredentials = "true")
@RestController
@RequestMapping("/like-log")
public class LikeLogController {
    @Autowired
    private LikeLogService likeLogService;
    @PostMapping("/save")
    public R saveLikeLog(@RequestBody LikeLogEntity likeLogEntity){
        if(likeLogService.findById(likeLogEntity.getShortVideoId(),likeLogEntity.getOpenId()) == null) {
            likeLogService.saveViewLog(likeLogEntity);
            return R.ok(201,"新增成功");
        }
        return R.error(400, "已存在日志");
    }
    @GetMapping("/info")
    public R getLikeLogInfo(Long shortVideoId, String openId){
        LikeLogEntity likeLogEntity = likeLogService.findById(shortVideoId,openId);
        return R.ok("获取成功").put("likeLog",likeLogEntity);
    }
}
