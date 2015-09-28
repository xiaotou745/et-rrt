package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.RoleInfo;

public interface IRoleInfoDao {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
}