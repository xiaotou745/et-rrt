package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.RoleInfo;



public interface IRoleInfoService {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
}
