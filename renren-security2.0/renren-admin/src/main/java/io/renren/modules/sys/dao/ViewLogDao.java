package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.ViewLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ViewLogDao {
    void saveViewLog(ViewLogEntity viewLogEntity);
    ViewLogEntity findByAllId(@Param("shortVideoId")Long shortVideoId, @Param("openId")String openId);
}
