package com.pbs.ams.web.service;
import com.pbs.ams.common.base.BaseServiceMock;
import com.pbs.ams.web.mappers.UpmsUserMapper;
import com.pbs.ams.web.model.UpmsUser;
import com.pbs.ams.web.model.UpmsUserExample;

/**
 * UpmsUserService接口
 * Created by ams on 2017/8/16.
 */
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        return null;
    }
}