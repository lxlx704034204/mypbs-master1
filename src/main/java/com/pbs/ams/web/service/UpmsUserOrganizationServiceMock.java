package com.pbs.ams.web.service;
import com.pbs.ams.common.base.BaseServiceMock;
import com.pbs.ams.web.mappers.UpmsUserOrganizationMapper;
import com.pbs.ams.web.model.UpmsUserOrganization;
import com.pbs.ams.web.model.UpmsUserOrganizationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UpmsUserOrganizationService接口
 * Created by ams on 2017/8/16.
 */
public class UpmsUserOrganizationServiceMock extends BaseServiceMock<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceMock.class);

    @Override
    public int organization(String[] organizationIds, long id) {
        _log.info("UpmsUserOrganizationServiceMock => organization");
        return 0;
    }
}