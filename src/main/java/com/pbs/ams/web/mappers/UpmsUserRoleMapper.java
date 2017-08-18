package com.pbs.ams.web.mappers;

import com.pbs.ams.web.model.UpmsUserRole;
import com.pbs.ams.web.model.UpmsUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserRoleMapper {
    long countByExample(UpmsUserRoleExample example);

    int deleteByExample(UpmsUserRoleExample example);

    int deleteByPrimaryKey(Long userRoleId);

    int insert(UpmsUserRole record);

    int insertSelective(UpmsUserRole record);

    List<UpmsUserRole> selectByExample(UpmsUserRoleExample example);

    UpmsUserRole selectByPrimaryKey(Long userRoleId);

    int updateByExampleSelective(@Param("record") UpmsUserRole record, @Param("example") UpmsUserRoleExample example);

    int updateByExample(@Param("record") UpmsUserRole record, @Param("example") UpmsUserRoleExample example);

    int updateByPrimaryKeySelective(UpmsUserRole record);

    int updateByPrimaryKey(UpmsUserRole record);
}