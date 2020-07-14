package io.renren.modules.sys.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import io.renren.common.utils.Constant;
import io.renren.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://172.30.145.222:8080", allowCredentials = "true")
@RestController
public class AliyunController {
    @GetMapping("/aliyun/oss/assumeRole")
    @RequiresPermissions("sys:shortvideo:save")
    public R getAccessInfo(){
        String endpoint = Constant.endpoint;
        String AccessKeyId = Constant.AccessKeyId;
        String accessKeySecret = Constant.accessKeySecret;
        String roleArn = Constant.roleArn;
        String roleSessionName = "short-video-author";
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", AccessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setDurationSeconds(3600L); // 设置凭证有效时间
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return R.ok("授权成功").put("assumeRole", response);
        } catch (ClientException e) {
            return R.error("服务器内部错误");
        }
    }
}
