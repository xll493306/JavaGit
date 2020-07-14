package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.sys.entity.ShortVideoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShortVideoDao extends BaseMapper<ShortVideoEntity> {
    void saveShortVideo(ShortVideoEntity shortVideoEntity);
    ShortVideoEntity findById(Long id);
    void updateStageById(ShortVideoEntity shortVideoEntity);
    /**
     * 查询用户上传视频
     */
    List<ShortVideoEntity> queryAllShortVideo(Long userId);
}
