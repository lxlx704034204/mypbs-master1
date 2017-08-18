package com.pbs.ams.web.service.impl;

import com.pbs.ams.common.annotation.BaseService;
import com.pbs.ams.common.base.BaseServiceImpl;
import com.pbs.ams.web.mappers.UpmsOrganizationMapper;
import com.pbs.ams.web.model.UpmsOrganization;
import com.pbs.ams.web.model.UpmsOrganizationExample;
import com.pbs.ams.web.service.UpmsOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsOrganizationService实现
 * Created by ams on 2017/8/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    private UpmsOrganizationMapper upmsOrganizationMapper;



}