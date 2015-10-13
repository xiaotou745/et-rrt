package com.renrentui.renrenapihttp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;






import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.ICommonService;


/**
 * 通用模块 
 * @author CaoHeYang
 * @date 20150909
 */
@Service
public class CommonService implements ICommonService {
	/**
	 * B端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@Override
	public HttpResultModel<List<String>> getRecordtypeB() {
		HttpResultModel<List<String>> resultModel=new HttpResultModel<List<String>>();

       return resultModel;
	}

	/**
	 * C端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@Override
	public HttpResultModel<List<String>> getRecordtypeC() {
		HttpResultModel<List<String>> resultModel=new HttpResultModel<List<String>>();
       
       return resultModel;
	}

}
