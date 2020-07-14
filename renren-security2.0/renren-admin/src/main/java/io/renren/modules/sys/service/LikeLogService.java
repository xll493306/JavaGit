package io.renren.modules.sys.service;

import io.renren.modules.sys.entity.LikeLogEntity;
import io.renren.modules.sys.entity.ViewLogEntity;

public interface LikeLogService {
    void saveViewLog(LikeLogEntity likeLogEntity);
    LikeLogEntity findById(Long shortVideoId,String openId);
}
