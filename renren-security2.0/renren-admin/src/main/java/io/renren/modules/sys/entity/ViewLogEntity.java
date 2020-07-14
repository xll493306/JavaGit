package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_view_log")
public class ViewLogEntity {
    /**
     * 短视频Id
     */
    private Long shortVideoId;
    /**
     * 用户openId
     */
    private String openId;
}
