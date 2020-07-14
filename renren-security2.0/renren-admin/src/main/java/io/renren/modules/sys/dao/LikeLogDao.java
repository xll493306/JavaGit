package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.LikeLogEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeLogDao {
    void saveViewLog(LikeLogEntity likeLogEntity);
    LikeLogEntity findByAllId(@Param("shortVideoId")Long shortVideoId, @Param("openId")String openId);

}
