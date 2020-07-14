package io.renren.modules.sys.service;

import io.renren.modules.sys.entity.ViewLogEntity;

public interface ViewLogService {
    void saveViewLog(ViewLogEntity viewLogEntity);
    ViewLogEntity findById(Long shortVideoId,String openId);
}
