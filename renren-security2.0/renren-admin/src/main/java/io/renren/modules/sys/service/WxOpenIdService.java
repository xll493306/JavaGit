package io.renren.modules.sys.service;

import java.util.Map;

public interface WxOpenIdService {
    Map<String,String> getOpenId(String code);
}
