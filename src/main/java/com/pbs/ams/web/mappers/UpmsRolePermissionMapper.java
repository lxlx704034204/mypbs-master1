package com.pbs.ams.web.mappers;

import com.pbs.ams.web.model.UpmsRolePermission;
import com.pbs.ams.web.model.UpmsRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsRolePermissionMapper {
    long countByExample(UpmsRolePermissionExample example);

    int deleteByExample(UpmsRolePermissionExample example);

    int deleteByPrimaryKey(Long rolePermissionId);

    int insert(UpmsRolePermission record);

    int insertSelective(UpmsRolePermission record);

    List<UpmsRolePermission> selectByExample(UpmsRolePermissionExample example);

    UpmsRolePermission selectByPrimaryKey(Long rolePermissionId);

    int updateByExampleSelective(@Param("record") UpmsRolePermission record, @Param("example") UpmsRolePermissionExample example);

    int updateByExample(@Param("record") UpmsRolePermission record, @Param("example") UpmsRolePermissionExample example);

    int updateByPrimaryKeySelective(UpmsRolePermission record);

    int updateByPrimaryKey(UpmsRolePermission record);
}