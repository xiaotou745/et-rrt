package com.renrentui.api.dao.inter;

import java.util.List;

import com.renrentui.entity.RoleAuth;
import com.renrentui.entity.RoleInfo;

public interface IRoleAuthDao {
	/**
	 * 修改给定角色的权限列表
	 * @author hailongzhao
	 * @date 20150902
	 * @return
	 */
	public boolean modifyAuthList(List<RoleAuth> authList) ;
}