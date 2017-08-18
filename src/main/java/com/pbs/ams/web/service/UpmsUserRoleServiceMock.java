package com.pbs.ams.web.service;
import com.pbs.ams.common.base.BaseServiceMock;
import com.pbs.ams.web.mappers.UpmsUserRoleMapper;
import com.pbs.ams.web.model.UpmsUserRole;
import com.pbs.ams.web.model.UpmsUserRoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UpmsUserRoleService接口
 * Created by ams on 2017/8/16.
 */
public class UpmsUserRoleServiceMock extends BaseServiceMock<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserRoleServiceMock.class);

    @Override
    public int role(String[] roleIds, long id) {
        _log.info("UpmsUserRoleServiceMock => role");
        return 0;
    }
}