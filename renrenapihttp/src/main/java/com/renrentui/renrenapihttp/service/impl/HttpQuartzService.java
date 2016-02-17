package com.renrentui.renrenapihttp.service.impl;

import org.springframework.stereotype.Service;

import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.common.QuartzServiceHelper;
import com.renrentui.renrenapihttp.service.inter.IHttpQuartzService;
@Service
public class HttpQuartzService implements IHttpQuartzService {
	@Override
	public HttpResultModel<String> expireTask() {
		return QuartzServiceHelper.doSend("expireTaskService");
	}

	@Override
	public HttpResultModel<String> sendTaskMsg() {
		return QuartzServiceHelper.doSend("sendTaskMsgService");
	}
	
}
