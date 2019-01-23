package org.x.mybatisdemo.mapper;

import org.x.mybatisdemo.model.SysRole;
import org.x.mybatisdemo.model.SysUser;

import java.util.List;

public interface UserMapper {
    SysUser selectById(Long id);
    List<SysUser> selectAll();
    List<SysRole> selectRoleByUserId(Long userId);
}
