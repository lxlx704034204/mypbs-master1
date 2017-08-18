package com.pbs.ams.web.mappers;


import com.pbs.ams.web.model.UpmsPermission;
import com.pbs.ams.web.model.UpmsRole;

import java.util.List;

/**
 * 用户VOMapper
 * Created by ams on 2017/01/07.
 */
public interface UpmsApiMapper {

	// 根据用户id获取所拥有的权限
	List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Long upmsUserId);//(Integer upmsUserId);

	// 根据用户id获取所属的角色
	List<UpmsRole> selectUpmsRoleByUpmsUserId(Long upmsUserId);//(Integer upmsUserId);
	
}