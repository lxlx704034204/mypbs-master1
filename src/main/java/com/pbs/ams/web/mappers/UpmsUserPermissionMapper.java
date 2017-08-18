package com.pbs.ams.web.mappers;

import com.pbs.ams.web.model.UpmsUserPermission;
import com.pbs.ams.web.model.UpmsUserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserPermissionMapper {
    long countByExample(UpmsUserPermissionExample example);

    int deleteByExample(UpmsUserPermissionExample example);

    int deleteByPrimaryKey(Long userPermissionId);

    int insert(UpmsUserPermission record);

    int insertSelective(UpmsUserPermission record);

    List<UpmsUserPermission> selectByExample(UpmsUserPermissionExample example);

    UpmsUserPermission selectByPrimaryKey(Long userPermissionId);

    int updateByExampleSelective(@Param("record") UpmsUserPermission record, @Param("example") UpmsUserPermissionExample example);

    int updateByExample(@Param("record") UpmsUserPermission record, @Param("example") UpmsUserPermissionExample example);

    int updateByPrimaryKeySelective(UpmsUserPermission record);

    int updateByPrimaryKey(UpmsUserPermission record);
}