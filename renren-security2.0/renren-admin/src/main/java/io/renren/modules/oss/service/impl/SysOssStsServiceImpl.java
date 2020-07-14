package io.renren.modules.oss.service.impl;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import io.renren.modules.oss.service.SysOssStsService;
import org.springframework.stereotype.Service;

@Service("SysOssStsService")
public class SysOssStsServiceImpl implements SysOssStsService {
    @Override
    public String getSecurityToken(String endpoint, String AccessKeyId,
                                   String accessKeySecret, String roleArn,
                                   String roleSessionName, String policy)
    {
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts",endpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", AccessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限
            request.setDurationSeconds(1000L); // 设置凭证有效时间
            final AssumeRoleResponse response = client.getAcsResponse(request);
            String SecurityToken =  response.getCredentials().getSecurityToken();
            return SecurityToken;
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
            return null;
        }

    }
}
