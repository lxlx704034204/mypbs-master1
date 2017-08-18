package com.pbs.ams.web.service;
import com.alibaba.fastjson.JSONArray;
import com.pbs.ams.common.base.BaseServiceMock;
import com.pbs.ams.web.mappers.UpmsPermissionMapper;
import com.pbs.ams.web.model.UpmsPermission;
import com.pbs.ams.web.model.UpmsPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UpmsPermissionService接口
 * Created by ams on 2017/8/16.
 */
public class UpmsPermissionServiceMock extends BaseServiceMock<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsPermissionServiceMock.class);

    @Override
    public JSONArray getTreeByRoleId(Long roleId) {             //非自动生成---手动add
        _log.info("UpmsPermissionServiceMock => getTreeByRoleId");
        return null;
    }

    @Override
    public JSONArray getTreeByUserId(Long usereId, Byte type) { //非自动生成---手动add
        _log.info("UpmsPermissionServiceMock => getTreeByUserId");
        return null;
    }
}