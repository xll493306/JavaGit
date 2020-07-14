package io.renren.modules.sys.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.ShortVideoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.ShortVideoService;
import io.renren.modules.sys.shiro.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://172.30.145.222:8080", allowCredentials = "true")
@RestController
@RequestMapping("/short-video")
public class ShortVideoController extends AbstractController {
    @Autowired
    private ShortVideoService shortVideoService;
    @RequestMapping("/page")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = shortVideoService.queryPage(params);
        return R.ok().put("page", page);
    }
    @RequestMapping("/selfList")
    public R shortVideoListInfo(){
        if(getUser() == null)return R.error(401, "用户尚未登陆");
        List<ShortVideoEntity> list =shortVideoService.findAllByUserId(getUserId());
        return R.ok("获取成功").put("shortVideoList",list);
    }
    @GetMapping("/info")
    public R info(@RequestParam Long id){
        ShortVideoEntity shortVideo = shortVideoService.findByVideoId(id);
        return R.ok().put("shortVideo", shortVideo);
    }
    @SysLog("修改参赛状态")
    @RequestMapping("/updateStage")
    @RequiresPermissions("sys:shortvideo:updateStage")
    public R updateStage(@RequestBody ShortVideoEntity shortVideoEntity){
        ValidatorUtils.validateEntity(shortVideoEntity);
        shortVideoService.updateStage(shortVideoEntity);
        return R.ok();
    }
    @SysLog("修改参赛信息")
    @RequestMapping("/update")
    @RequiresPermissions("sys:shortvideo:update")
    public R update(@RequestBody ShortVideoEntity shortVideo){
        ValidatorUtils.validateEntity(shortVideo);
        shortVideo.setUpdateUserId(ShiroUtils.getUserId());
        shortVideo.setUpdateTime(new Date());
        shortVideoService.updateById(shortVideo);

        return R.ok(201,"操作成功");
    }
    @SysLog("新增短视频")
    @PostMapping("/save")
    @RequiresPermissions("sys:shortvideo:save")
    public R saveVideoInfo(@RequestBody ShortVideoEntity shortVideoEntity){
        SysUserEntity user =ShiroUtils.getUserEntity();
        shortVideoEntity.setUserId(user.getUserId());
        shortVideoEntity.setDeptName(user.getDeptName());
        shortVideoService.saveShortVideo(shortVideoEntity);
        return R.ok(201,"报名成功");
    }
    @SysLog("删除短视频")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:shortvideo:delete")
    public R delete(@RequestBody Long[] ids){
        shortVideoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
