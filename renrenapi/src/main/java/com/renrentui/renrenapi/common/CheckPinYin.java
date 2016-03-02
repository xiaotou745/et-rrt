package com.renrentui.renrenapi.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.renrentui.renrenapi.service.inter.IPublicProvinceCityService;
import com.renrentui.renrencore.util.PinyinUtil;
import com.renrentui.renrencore.util.SpringBeanHelper;
import com.renrentui.renrenentity.PublicProvinceCity;

public class CheckPinYin {
	private static Logger renrenAdminLogger = Logger.getLogger("renrenAdminLogger");
	private final static IPublicProvinceCityService publicProvinceCityService;
	static {
		publicProvinceCityService = SpringBeanHelper
				.getCustomBeanByType(IPublicProvinceCityService.class);
	}

	/**
	 * 打印出包含多音字的省市区数据
	 * @author hailongzhao
	 * @date 20160302
	 */
	public static void printPolyphoneData(Boolean isCreateUpdateSql) {
		String pinyinPolyphone4j = "";
		String pinyin4j = "";
		String jianxie4j = "";
		String first4j = "";
		String printInfo = "";
		System.out.println("开始打印出包含多音字的省市区数据");
		List<String> polyphoneCodes = new ArrayList<>();
		List<String> diffCodes = new ArrayList<>();
		List<String> sameCodes = new ArrayList<>();
		String updateString = "update PublicProvinceCity set FirstLetter='%s',LowerAcronym='%s',UpperAcronym='%s',LowerFullPinyin='%s' where code=%s;";
		List<PublicProvinceCity> cityDatas = publicProvinceCityService.getOpenCityListFromRedis();
		for (PublicProvinceCity publicProvinceCity : cityDatas) {
			// 多音字之间用#分割
			pinyinPolyphone4j = String.join("", PinyinUtil.stringToPinyin(publicProvinceCity.getName(), "#"));
			if (pinyinPolyphone4j.contains("#")) {
				pinyin4j = String.join("",PinyinUtil.stringToPinyin(publicProvinceCity.getName())).toLowerCase();
				polyphoneCodes.add(publicProvinceCity.getCode().toString());
				if (!publicProvinceCity.getLowerFullPinYin().equals(pinyin4j)) {
					if (isCreateUpdateSql) {
						jianxie4j = String.join("", PinyinUtil.getHeadByString(publicProvinceCity.getName()));
						first4j = String.valueOf(jianxie4j.toCharArray()[0]);
						printInfo = String.format(updateString,
													first4j.toUpperCase(), 
													jianxie4j.toLowerCase(),
													jianxie4j.toUpperCase(), 
													pinyin4j,
													publicProvinceCity.getCode());
						//数据太多时，控制台显示不全，需要写入log文件
						//renrenAdminLogger.info(printInfo);
						System.out.println(printInfo);
					}
					diffCodes.add(publicProvinceCity.getCode().toString() + "_"
							+ publicProvinceCity.getName() + ":"
							+ publicProvinceCity.getLowerFullPinYin() + ","
							+ pinyin4j + "," + pinyinPolyphone4j);
				} else {
					sameCodes.add(publicProvinceCity.getCode().toString() + "_"
							+ publicProvinceCity.getName() + ":"
							+ publicProvinceCity.getLowerFullPinYin() + ","
							+ pinyinPolyphone4j);
				}
			}
		}
		System.out.println("包含多音字的区域的code:" + polyphoneCodes.size() + "=="+ String.join(",", polyphoneCodes));
		System.out.println("差异的:");
		for (String string : diffCodes) {
			System.out.println(string);
		}
		System.out.println("一致的:");
		for (String string : sameCodes) {
			System.out.println(string);
		}
	}
	/**
	 * 生成给定的code的修复sql
	 * @author hailongzhao
	 * @date 20160302
	 */
	public static void createUpdateSql(String codes) {
		if (codes == null || codes.isEmpty()) {
			System.out.println("入参为空，没有需要修复的数据");
			return;
		}
		String[] citys = codes.split(",");
		if (citys.length == 0) {
			System.out.println("入参为空，没有需要修复的数据");
			return;
		}
		String pinyin4j = "";
		String jianxie4j = "";
		String first4j = "";
		String printInfo = "";
		List<String> givenList = Arrays.asList(citys);
		String updateString = "update PublicProvinceCity set FirstLetter='%s',LowerAcronym='%s',UpperAcronym='%s',LowerFullPinyin='%s' where code=%s;";
		List<PublicProvinceCity> cityes = publicProvinceCityService.getOpenCityListFromRedis();
		for (PublicProvinceCity publicProvinceCity : cityes) {
			if (givenList.contains(publicProvinceCity.getCode().toString())) {
				pinyin4j = String.join("", PinyinUtil.stringToPinyin(publicProvinceCity.getName()));
				jianxie4j = String.join("", PinyinUtil.getHeadByString(publicProvinceCity.getName()));
				first4j = String.valueOf(jianxie4j.toCharArray()[0]);
				printInfo = String.format(updateString, 
											first4j.toUpperCase(),
											jianxie4j.toLowerCase(), 
											jianxie4j.toUpperCase(),
											pinyin4j, 
											publicProvinceCity.getCode());
				//数据太多时，控制台显示不全，需要写入log文件
				//renrenAdminLogger.info(printInfo);
				System.out.println(printInfo);
			}
		}
	}
}
