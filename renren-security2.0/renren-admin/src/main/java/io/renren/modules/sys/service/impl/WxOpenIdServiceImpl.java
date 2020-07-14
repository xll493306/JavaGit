package io.renren.modules.sys.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.modules.sys.entity.WxEntity;
import io.renren.modules.sys.service.WxOpenIdService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service("WxOpenIdService")
public class WxOpenIdServiceImpl implements WxOpenIdService {
    @Override
    public Map<String,String> getOpenId(String code) {
        Map<String, String> map = new HashMap<>();
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                Constant.appID, Constant.appsecret, code);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> wxEntityResponseEntity = restTemplate.getForEntity(url, String.class);
//        JSONObject obj = (JSONObject) JSON.parse(wxEntityResponseEntity);
        JSONObject object = (JSONObject)JSON.parse(wxEntityResponseEntity.getBody());
        String openId = (String) object.get("openid");
        if (openId == null){
            throw new RRException("参数无效",400);
        }
        map.put("openId",openId);
        return map;
    }
}
