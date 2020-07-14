package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.dao.ViewLogDao;
import io.renren.modules.sys.entity.ViewLogEntity;
import io.renren.modules.sys.service.ViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ViewLogService")
public class ViewLogServiceImpl implements ViewLogService {
    @Autowired
    ViewLogDao viewLogDao;
    @Override
    public void saveViewLog(ViewLogEntity viewLogEntity) {
        viewLogDao.saveViewLog(viewLogEntity);
    }

    @Override
    public ViewLogEntity findById(Long shortVideoId, String openId) {
        return viewLogDao.findByAllId(shortVideoId,openId);
    }
}
