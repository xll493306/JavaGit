/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@CrossOrigin(origins = "http://172.30.145.222:8080", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/selfInfo")
	public R info(){
		SysUserEntity sysUserEntity = getUser();
		if(sysUserEntity == null) return R.error(401, "用户尚未登录");
		SysUserEntity sysuser = sysUserService.getById(sysUserEntity.getUserId());
		return R.ok().put("user", sysuser);
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/updatePassword")
	@RequiresPermissions("sys:user:update")
	public R password(String oldPassword, String newPassword){
		Assert.isBlank(newPassword, "新密码不为能空");

		//原密码
		oldPassword = ShiroUtils.sha256(oldPassword, getUser().getSalt());
		//新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), oldPassword, newPassword);
		if(!flag){
			return R.error(400,"密码错误");
		}

		return R.ok("修改成功");
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		sysUserService.saveUser(user);
		return R.ok(201,"保存成功");
	}

	/**
	 * 保存参赛用户
	 */
	@PostMapping("/saveContestant")
	public R saveContestant(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, AddGroup.class);
		if(sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("username", user.getUsername())) != null){
			return R.error(409, "注册失败，手机号码已存在");
		}
		user.setStatus(1);
		user.setFullName("");
		//自动绑定参赛者角色
		List<Long> roleIdList = new LinkedList<>();
		roleIdList.add(Constant.roleId);
		user.setRoleIdList(roleIdList);
		sysUserService.saveUser(user);
		return R.ok(201,"保存成功");
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		sysUserService.update(user);
		
		return R.ok(201,"修改成功");
	}

	@SysLog("修改登录用户绑定书店")
	@RequestMapping("/updateDept")
	@RequiresPermissions("sys:user:update")
	public R updateDept(Long deptId){
//		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.updateDeptId(getUserId(),deptId);
		return R.ok("修改成功");
	}

	@SysLog("修改登录用户信息")
	@RequestMapping("/updateSelf")
	@RequiresPermissions("sys:user:update")
	public R updateFullName(String fullName){
		sysUserService.updateFullName(getUserId(),fullName);
		return R.ok("修改成功");
	}

	@SysLog("修改登录手机号码")
	@RequestMapping("/updateUsername")
	@RequiresPermissions("sys:user:update")
	public R updateUsername(String username, String password){
		sysUserService.updateUsername(getUserId(),username,password);
		return R.ok("修改成功");
	}
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysUserService.removeByIds(Arrays.asList(userIds));
		
		return R.ok();
	}
}
