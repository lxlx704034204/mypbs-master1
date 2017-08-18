package com.pbs.ams.web.service.impl;

import com.pbs.ams.common.annotation.BaseService;
import com.pbs.ams.common.base.BaseServiceImpl;
import com.pbs.ams.web.mappers.UpmsUserOrganizationMapper;
import com.pbs.ams.web.model.UpmsUserOrganization;
import com.pbs.ams.web.model.UpmsUserOrganizationExample;
import com.pbs.ams.web.service.UpmsUserOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsUserOrganizationService实现
 * Created by ams on 2017/8/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
@BaseService
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceImpl.class);

    @Autowired
    private UpmsUserOrganizationMapper upmsUserOrganizationMapper;

    @Override
    public int organization(String[] organizationIds, long id) { // int id, long id
        int result = 0;
        // 删除旧记录
        UpmsUserOrganizationExample upmsUserOrganizationExample = new UpmsUserOrganizationExample();
        upmsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        upmsUserOrganizationMapper.deleteByExample(upmsUserOrganizationExample);
        // 增加新记录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
                upmsUserOrganization.setUserId(id);
                upmsUserOrganization.setOrganizationId(NumberUtils.toLong(organizationId));//.toInt(organizationId)
                result = upmsUserOrganizationMapper.insertSelective(upmsUserOrganization);
            }
        }
        return result;
    }

}