package com.renrentui.renrenapi.service.inter;

import java.util.List;

import com.renrentui.renrenentity.Activity;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.req.PagedActivityReq;
import com.renrentui.renrenentity.req.UpdateActivityReq;

public interface IActivityService {
	List<Activity> getList();

	Activity getSingleActivity(int id);
	
	int shutUpActivity(UpdateActivityReq req);

	int startUpActivity(UpdateActivityReq req);

	int updateActityData(int id);
}
