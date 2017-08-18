package com.pbs.ams.web.service;

import com.alibaba.fastjson.JSONArray;
import com.pbs.ams.common.base.BaseService;
import com.pbs.ams.web.model.UpmsRolePermission;
import com.pbs.ams.web.model.UpmsRolePermissionExample;

/**
 * UpmsRolePermissionService接口
 * Created by ams on 2017/8/16.
 */
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermission, UpmsRolePermissionExample> {

    /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, long id); // long id, int id

}