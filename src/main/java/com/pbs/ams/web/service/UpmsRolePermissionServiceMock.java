package com.pbs.ams.web.service;
import com.alibaba.fastjson.JSONArray;
import com.pbs.ams.common.base.BaseServiceMock;
import com.pbs.ams.web.mappers.UpmsRolePermissionMapper;
import com.pbs.ams.web.model.UpmsRolePermission;
import com.pbs.ams.web.model.UpmsRolePermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UpmsRolePermissionService接口
 * Created by ams on 2017/8/16.
 */
public class UpmsRolePermissionServiceMock extends BaseServiceMock<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRolePermissionServiceMock.class);

    @Override
    public int rolePermission(JSONArray datas, long id) { // long id, int id
        _log.info("UpmsRolePermissionServiceMock => rolePermission");
        return 0;
    }
}