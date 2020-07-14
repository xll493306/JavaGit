package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.ShortVideoEntity;

import java.util.List;
import java.util.Map;

public interface ShortVideoService extends IService<ShortVideoEntity> {
    void saveShortVideo(ShortVideoEntity shortVideoEntity);
    ShortVideoEntity findByVideoId(Long id);
    PageUtils queryPage(Map<String, Object> params);
    void updateStage(ShortVideoEntity shortVideoEntity);
    List<ShortVideoEntity> findAllByUserId(Long userId);
}
