package com.pbs.ams.web.service;
import com.alibaba.fastjson.JSONArray;
import com.pbs.ams.common.base.BaseServiceMock;
import com.pbs.ams.web.mappers.UpmsUserPermissionMapper;
import com.pbs.ams.web.model.UpmsUserPermission;
import com.pbs.ams.web.model.UpmsUserPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UpmsUserPermissionService接口
 * Created by ams on 2017/8/16.
 */
public class UpmsUserPermissionServiceMock extends BaseServiceMock<UpmsUserPermissionMapper, UpmsUserPermission, UpmsUserPermissionExample> implements UpmsUserPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserPermissionServiceMock.class);

    @Override
    public int permission(JSONArray datas, long id) {
        _log.info("UpmsUserPermissionServiceMock => permission");
        return 0;
    }
}