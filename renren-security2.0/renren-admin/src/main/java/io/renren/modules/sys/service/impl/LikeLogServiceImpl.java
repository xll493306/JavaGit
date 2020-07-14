package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.dao.LikeLogDao;
import io.renren.modules.sys.entity.LikeLogEntity;
import io.renren.modules.sys.service.LikeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LikeLogService")
public class LikeLogServiceImpl implements LikeLogService {
    @Autowired
    private LikeLogDao likeLogDao;
    @Override
    public void saveViewLog(LikeLogEntity likeLogEntity) {
        likeLogDao.saveViewLog(likeLogEntity);
    }

    @Override
    public LikeLogEntity findById(Long shortVideoId, String openId) {
        return likeLogDao.findByAllId(shortVideoId,openId);
    }
}
