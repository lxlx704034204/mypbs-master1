package com.pbs.ams.web.service;

import com.pbs.ams.common.base.BaseService;
import com.pbs.ams.web.model.UpmsUserRole;
import com.pbs.ams.web.model.UpmsUserRoleExample;

/**
 * UpmsUserRoleService接口
 * Created by ams on 2017/8/16.
 */
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /** // 非自动生成---手动添加
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, long id); //int id, long id

}