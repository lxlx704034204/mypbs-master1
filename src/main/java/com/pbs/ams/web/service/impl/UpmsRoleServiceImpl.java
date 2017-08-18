package com.pbs.ams.web.service.impl;

import com.pbs.ams.common.annotation.BaseService;
import com.pbs.ams.common.base.BaseServiceImpl;
import com.pbs.ams.web.mappers.UpmsRoleMapper;
import com.pbs.ams.web.model.UpmsRole;
import com.pbs.ams.web.model.UpmsRoleExample;
import com.pbs.ams.web.service.UpmsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsRoleService实现
 * Created by ams on 2017/8/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    private UpmsRoleMapper upmsRoleMapper;



}