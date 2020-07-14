package io.renren.modules.oss.service;

public interface SysOssStsService {
    String getSecurityToken(String endpoint, String AccessKeyId,
                            String accessKeySecret, String roleArn,
                            String roleSessionName,String policy);
}
