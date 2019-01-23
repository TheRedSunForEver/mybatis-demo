package org.x.mybatisdemo.mapper;

import org.x.mybatisdemo.model.SysUser;

public interface UserMapper {
    SysUser selectById(Long id);
}
