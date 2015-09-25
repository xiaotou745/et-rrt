package com.renrentui.api.dao.inter;

import java.util.List;

import com.renrentui.entity.RoleInfo;

public interface IRoleInfoDao {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
}