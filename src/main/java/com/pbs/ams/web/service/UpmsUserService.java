package com.pbs.ams.web.service;

import com.pbs.ams.common.base.BaseService;
import com.pbs.ams.web.model.UpmsUser;
import com.pbs.ams.web.model.UpmsUserExample;

/**
 * UpmsUserService接口
 * Created by ams on 2017/8/16.
 */
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);// 非自动生成---手动添加
}