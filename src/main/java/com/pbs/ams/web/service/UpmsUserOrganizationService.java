package com.pbs.ams.web.service;

import com.pbs.ams.common.base.BaseService;
import com.pbs.ams.web.model.UpmsUserOrganization;
import com.pbs.ams.web.model.UpmsUserOrganizationExample;

/**
 * UpmsUserOrganizationService接口
 * Created by ams on 2017/8/16.
 */
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    /** // 非自动生成---手动添加
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, long id); // int id, long id

}