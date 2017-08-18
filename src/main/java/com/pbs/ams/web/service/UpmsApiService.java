package com.pbs.ams.web.service;


import com.pbs.ams.web.model.*;

import java.util.List;

/**
 * upms系统接口
 * Created by ams on 2017/2/11.
 */
public interface UpmsApiService {

    /**1
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Long upmsUserId);//(Integer upmsUserId);
    /**2
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Long upmsUserId);//(Integer

    /**3
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Long upmsUserId);//(Integer upmsUserId);


    /**4
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Long upmsUserId);//(Integer



    /**5
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Long upmsRoleId);//(Integer upmsRoleId);

    /**6
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Long upmsUserId);//(Integer

    /**7
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample);

    /**8
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample);

    /**9
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    UpmsUser selectUpmsUserByUsername(String username);

    /**10
     * 写入操作日志
     * @param record
     * @return
     */
    int insertUpmsLogSelective(UpmsLog record);

}

