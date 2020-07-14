package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.ShortVideoDao;
import io.renren.modules.sys.entity.ShortVideoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.ShortVideoService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("ShortVideoService")
public class ShortVideoServiceImpl extends ServiceImpl<ShortVideoDao, ShortVideoEntity> implements ShortVideoService {
    @Autowired
    ShortVideoDao shortVideoDao;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String[] splitStage ;
        String deptId = null;
        String stage =(String) params.get("stage");
        SysUserEntity user = ShiroUtils.getUserEntity();
        if(user == null) {
            deptId = (String) params.get("deptId");
        }else {
            List<Long> roleList =sysUserRoleService.queryRoleIdList(user.getUserId());
            if(roleList.contains(Constant.mangerId)){
                deptId = user.getDeptId().toString();
            }
        }
        if(stage == null){
            splitStage = null;
        }
        else {
            splitStage = stage.split(",");
        }

        IPage<ShortVideoEntity> page = this.page(
            new Query<ShortVideoEntity>().getPage(params),
            new QueryWrapper<ShortVideoEntity>()
                .eq(StringUtils.isNotBlank(deptId),"sd.dept_id",deptId)
                .in(StringUtils.isNotBlank(stage),"tsv.stage",splitStage)
        );
        return new PageUtils(page);
    }

    @Override
    public void updateStage(ShortVideoEntity shortVideoEntity) {
        ShortVideoEntity shortVideo = new ShortVideoEntity();
        shortVideo.setUpdateUserId(ShiroUtils.getUserId());
        shortVideo.setStage(shortVideoEntity.getStage());
        shortVideo.setUpdateTime(new Date());
        shortVideo.setId(shortVideoEntity.getId());
        System.err.println(shortVideo);
        shortVideoDao.updateStageById(shortVideo);
    }

    @Override
    public List<ShortVideoEntity> findAllByUserId(Long userId) {
        return shortVideoDao.queryAllShortVideo(userId);
    }

    @Override
    public void saveShortVideo(ShortVideoEntity shortVideoEntity) {
        shortVideoEntity.setStage("preliminary");
        shortVideoEntity.setCreateTime(new Date());
        shortVideoDao.saveShortVideo(shortVideoEntity);
    }

    @Override
    public ShortVideoEntity findByVideoId(Long id) {
        ShortVideoEntity shortVideoEntity = shortVideoDao.findById(id);
        return shortVideoEntity;
    }
}
