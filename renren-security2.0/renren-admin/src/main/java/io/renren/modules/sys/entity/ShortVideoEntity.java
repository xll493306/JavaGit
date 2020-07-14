package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_short_video")
public class ShortVideoEntity {
    /**
     * 短视频Id
     */
    @TableId
    private Long id;
    /**
     * 视频发布者ID
     */
    private Long userId;
    /**
     * 书店Id
     */
    @TableField(exist = false)
    private Long deptId;
    /**
     * 书店名称
     */
    @TableField(exist = false)
    private String deptName;
    /**
     * 参赛人
     */
    private String competitor;
    /**
     * 标题
     */
    private String name;
    /**
     * 简介
     */
    private String description;
    /**
     * 视频封面URL
     */
    private String posterUrl;
    /**
     * 视频URL
     */
    private String url;
    /**
     * 播放数
     */
    @TableField(exist = false)
    private int viewNumber;
    /**
     * 点赞数
     */
    @TableField(exist = false)
    private int likeNumber;
    /**
     * 视频所处的评选阶段
     */
    private String stage;
    /**
     * 短视频创建时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date createTime;
    /**
     * 短视频更新时间
     */
    private Date updateTime;
    /**
     * 更新操作用户ID
     */
    private Long updateUserId;
}
