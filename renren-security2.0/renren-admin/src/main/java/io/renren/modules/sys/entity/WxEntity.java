package io.renren.modules.sys.entity;

import lombok.Data;

@Data
public class WxEntity {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
