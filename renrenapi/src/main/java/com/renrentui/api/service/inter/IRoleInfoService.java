package com.renrentui.api.service.inter;

import java.util.List;

import com.renrentui.entity.RoleInfo;



public interface IRoleInfoService {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
}
