package io.renren;

import io.renren.modules.sys.dao.LikeLogDao;
import io.renren.modules.sys.dao.ShortVideoDao;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dao.ViewLogDao;
import io.renren.modules.sys.entity.LikeLogEntity;
import io.renren.modules.sys.entity.ShortVideoEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.ViewLogEntity;
import io.renren.modules.sys.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {
    @Autowired
    SysUserDao sysUserDao;
    @Autowired
    ShortVideoDao shortVideoDao;
    @Autowired
    ViewLogDao viewLogDao;
    @Autowired
    LikeLogDao likeLogDao;
    @Autowired
    LikeLogService likeLogService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    WxOpenIdService wxOpenIdService;
    @Autowired
    ShortVideoService shortVideoService;
    @Autowired
    ViewLogService viewLogService;
    @Test
    public void saveTest(){
        SysUserEntity sysUserEntity =new SysUserEntity();
        sysUserEntity.setDeptId((long)22);
        sysUserEntity.setUsername("686671");
        sysUserEntity.setPassword("493306");
        sysUserEntity.setSalt("123");
        sysUserEntity.setFullName("xll");
        sysUserEntity.setUserId((long)222222);
        sysUserDao.saveSysUser(sysUserEntity);
    }

    @Test
    public void findTest(){
        System.err.println(sysUserDao.findByName("admin"));
    }

    @Test
    public void shortTest(){
//        ShortVideoEntity shortVideoEntity =new ShortVideoEntity();
//        shortVideoEntity.setUserId((long)1);
//        shortVideoEntity.setCompetitor("xll");
//        shortVideoEntity.setName("laoba");
//        shortVideoEntity.setDeptName("hnd");
//        shortVideoEntity.setStage("0");
//        shortVideoDao.saveShortVideo(shortVideoEntity);
        System.err.println(shortVideoDao.findById((long) 4));
    }
    @Test
    public void usernameTesr(){
        String password = "1234";
        String username = "xll493306";
        Long userId = (long)4;
        sysUserDao.updateUsername(userId,username);
    }

    @Test
    public void WxopenTest(){
        System.err.println(wxOpenIdService.getOpenId("071OQKPq0USWsk1ZfQPq0mpGPq0OQKPa"));
    }
    @Test
    public void quanTest(){
        System.err.println(sysUserDao.queryAllPerms((long)222234));
    }
    @Test
    public void StageTest(){
        ShortVideoEntity shortVideoEntity = new ShortVideoEntity();
        shortVideoEntity.setId((long)1);
        shortVideoEntity.setStage("1");
        shortVideoEntity.setUpdateUserId((long)2);
        shortVideoEntity.setUpdateTime(new Date());
        shortVideoDao.updateStageById(shortVideoEntity);
    }
    @Test
    public void findByIdDeptName(){
        System.err.println(shortVideoService.findAllByUserId((long)1));
    }
    @Test
    public void ViewLogTest(){
        ViewLogEntity viewLogEntity= new ViewLogEntity();
        viewLogEntity.setOpenId("2gg3");
        viewLogEntity.setShortVideoId((long)1);
        viewLogDao.saveViewLog(viewLogEntity);
        System.err.println(viewLogDao.findByAllId((long)1, "2gg3"));
    }
    @Test
    public void ViewLogSerTest(){
        viewLogService.findById((long)1,"adking");

    }

    @Test
    public void LikeLogTest(){
        LikeLogEntity likeLogEntity= new LikeLogEntity();
        likeLogEntity.setOpenId("2gg3");
        likeLogEntity.setShortVideoId((long)1);
        likeLogDao.saveViewLog(likeLogEntity);
        System.err.println(likeLogDao.findByAllId((long)1, "2gg3"));
    }
    @Test
    public void LikeLogSerTest(){
        System.err.println(likeLogService.findById((long) 1, "adking"));

    }
    @Test
    public void CountTest(){

    }
}
