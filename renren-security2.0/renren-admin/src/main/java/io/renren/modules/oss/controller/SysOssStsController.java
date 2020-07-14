package io.renren.modules.oss.controller;

import io.renren.common.utils.R;
import io.renren.modules.oss.service.SysOssStsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SysOssStsController {
    @Autowired
    private SysOssStsService sysOssStsService;
    @RequestMapping("/getSts")
    public R getSts(){
        String endpoint = "<sts-endpoint>";
        String AccessKeyId = "<access-key-id>";
        String accessKeySecret = "<access-key-secret>";
        String roleArn = "<role-arn>";
        String roleSessionName = "<session-name>";
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        String SecurityToken = sysOssStsService.getSecurityToken(endpoint,AccessKeyId,accessKeySecret,roleArn,roleSessionName,policy);
        Map<String,Object> map = new HashMap<>();
        map.put("SecurityToken",SecurityToken);
        return R.ok(map);
    }
}
