package com.renrentui.renrenapihttp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrenapihttp.common.HttpResultModel;
import com.renrentui.renrenapihttp.service.inter.IRegionService;
import com.renrentui.renrencore.enums.RegionCode;
import com.renrentui.renrencore.enums.TaskDetailCode;
import com.renrentui.renrenentity.PublicProvinceCity;
import com.renrentui.renrenentity.domain.RegionModel;
import com.renrentui.renrenentity.domain.RegionModelFirstLetter;
import com.renrentui.renrenentity.domain.TaskDetail;
import com.renrentui.renrenentity.req.RegionReq;

@Service
public class RegionService implements IRegionService{

	@Autowired
	IPublicProvinceCityService iPublicProvinceCityService;
	@Override
	public HttpResultModel<RegionModel> getHotRegionAndAll(RegionReq req) { 
		
		String[] letter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		
		//返回给app的结果Model
		RegionModel rModel = new RegionModel(); 
		
		//首字母排列的所有城市
		List<RegionModelFirstLetter> firstLetterRegionModel = new ArrayList<RegionModelFirstLetter>();
		//热门城市
		List<PublicProvinceCity> hotRegionModel = new ArrayList<PublicProvinceCity>(); 
		//调用接口返回的数据库原始结果集
		List<PublicProvinceCity> prcList = new ArrayList<PublicProvinceCity>();
		prcList = iPublicProvinceCityService.getOpenCityByJiBie(3);
		
		//过滤热门城市,ishot为1且开放
		hotRegionModel = prcList.stream().filter(k->k.getIsHot().equals(1) && k.getIsPublic().equals(1)).collect(Collectors.toList());
		rModel.setHotRegionModel(hotRegionModel);
		 
		String str="";
		for(int i=0;i<letter.length;i++){
			str=letter[i];
			RegionModelFirstLetter rmflFirstLetter =new RegionModelFirstLetter();
			rmflFirstLetter.setFirstLetter(str);//首字母
			List<PublicProvinceCity> publicProvinceCity = new ArrayList<PublicProvinceCity>();
			for(int j=0;j<prcList.size();j++){ 
				if(str.equals(prcList.get(j).getFirstLetter())){ //首字母一样
					publicProvinceCity.add(prcList.get(j));//添加到该字母下的城市  
				}
			}
			rmflFirstLetter.setRegionModel(publicProvinceCity);
			firstLetterRegionModel.add(rmflFirstLetter);
		}
		rModel.setFirstLetterRegionModel(firstLetterRegionModel);
		return new HttpResultModel<RegionModel>().setCode(RegionCode.Success.value()).setMsg(RegionCode.Success.desc()).setData(rModel);
		
	}

}
