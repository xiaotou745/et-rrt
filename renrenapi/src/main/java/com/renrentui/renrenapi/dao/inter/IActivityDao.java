package com.renrentui.renrenapi.dao.inter;

import java.util.List;

import com.renrentui.renrenentity.Activity;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedActivityReq;
import com.renrentui.renrenentity.req.UpdateActivityReq;

public interface IActivityDao {

	List<Activity> getList();

	int shutUpActivity(UpdateActivityReq req);

	int startUpActivity(UpdateActivityReq req);

	Activity getSingleActivity(int id);

	int updateActityData(int id);

}
