package com.pbs.ams.web.controller;

import com.pbs.ams.common.base.BaseController;
import com.pbs.ams.web.model.*;
import com.pbs.ams.web.service.UpmsApiService;
import com.pbs.ams.web.service.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 后台controller
 * Created by ZhangShuzheng on 2017/01/19.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(ManageController.class);

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Autowired
    private UpmsApiService upmsApiService;

    /*http://localhost:1111/manage/index
    \mypbs-master\src\main\webapp\WEB-INF\jsp\index.jsp
    \mypbs-master\src\main\webapp\WEB-INF\jsp\manage\index.jsp
    */
    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
//        System.out.println("----test-1-/manage/index---: " + upmsSystemService); //com.pbs.ams.web.service.impl.UpmsSystemServiceImpl@5bc53a34
        UpmsSystem  upmsSystem = upmsSystemService.selectByPrimaryKey(1L);
        System.out.println("----test-2-/manage/index---: " + upmsSystem.getTitle()); //

        // 已注册系统
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andStatusEqualTo((byte) 1);
        List<UpmsSystem> upmsSystems = upmsSystemService.selectByExample(upmsSystemExample);
        System.out.println("----test-3-/manage/index---: " + upmsSystems.size() ); //
        modelMap.put("upmsSystems", upmsSystems);
        // 当前登录用户权限
        Subject subject = SecurityUtils.getSubject();
        System.out.println("----test-4-/manage/index---: " + subject ); //
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        modelMap.put("upmsPermissions", upmsPermissions);
        return "/manage/index.jsp";
    }

}