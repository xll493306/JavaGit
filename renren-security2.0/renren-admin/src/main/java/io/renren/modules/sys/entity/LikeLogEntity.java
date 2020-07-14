package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_like_log")
public class LikeLogEntity {
    /**
     * 短视频Id
     */
    private Long shortVideoId;
    /**
     * 用户openId
     */
    private String openId;
}
