package com.pbs.ams.web.service;

import com.alibaba.fastjson.JSONArray;
import com.pbs.ams.common.base.BaseService;
import com.pbs.ams.web.model.UpmsPermission;
import com.pbs.ams.web.model.UpmsPermissionExample;

/**
 * UpmsPermissionService接口
 * Created by ams on 2017/8/16.
 */
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Long roleId);             //非自动生成---手动add

    JSONArray getTreeByUserId(Long usereId, Byte type); //非自动生成---手动add
}