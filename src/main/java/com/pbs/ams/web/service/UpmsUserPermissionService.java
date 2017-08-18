package com.pbs.ams.web.service;

import com.alibaba.fastjson.JSONArray;
import com.pbs.ams.common.base.BaseService;
import com.pbs.ams.web.model.UpmsUserPermission;
import com.pbs.ams.web.model.UpmsUserPermissionExample;

/**
 * UpmsUserPermissionService接口
 * Created by ams on 2017/8/16.
 */
public interface UpmsUserPermissionService extends BaseService<UpmsUserPermission, UpmsUserPermissionExample> {

    /** // 非自动生成---手动添加
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, long id);//int id, long id
}