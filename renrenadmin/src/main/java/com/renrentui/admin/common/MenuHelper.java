package com.renrentui.admin.common;

import java.util.List;


import com.renrentui.entity.MenuInfo;

public class MenuHelper {
	public static String getAuthJson(List<MenuInfo> menuList) {
		if (menuList != null && menuList.size() > 0) {
			StringBuffer htmlStrBuffer = new StringBuffer();
			htmlStrBuffer.append("[");
			createMenu(htmlStrBuffer, 0, menuList);
			htmlStrBuffer.append("]");
			String ab = htmlStrBuffer.toString();
			return ab.replace(",\"nodes\":[]", "");
		}

		return "";
	}
	/**
	 * 根据authlist生成前台需要的json串
	 * @author hailongzhao
	 * @date 20150902
	 * @param htmlStrBuffer
	 * @param parentID
	 * @param menuList
	 * @return
	 */
		private static int createMenu(StringBuffer htmlStrBuffer, int parentID,List<MenuInfo> menuList) {
			int hasSub = 0;
			for (MenuInfo menuEntity : menuList) {
				if (menuEntity.getParid() == parentID) {
					hasSub = 1;
					htmlStrBuffer.append("{");
					htmlStrBuffer.append("\"id\":\"" + menuEntity.getId() + "\"");
					htmlStrBuffer.append(",\"parentid\":\"" + menuEntity.getParid()+ "\"");
					if (menuEntity.getIsbutton()) {
						htmlStrBuffer.append(",\"isbutton\":\"1\"");
					} else {
						htmlStrBuffer.append(",\"isbutton\":\"0\"");
					}
					if (parentID == 0) {
						htmlStrBuffer.append(",\"text\":\""
								+ menuEntity.getMenuname() + "\"");
					} else {
						if (menuEntity.getIsbutton()) {
							htmlStrBuffer.append(",\"text\":\"<font color='#A5A552'>"
									+ menuEntity.getMenuname() + "</font>\"");
						} 
						else {
							htmlStrBuffer.append(",\"text\":\"<font color='#AAAAFF'>"
									+ menuEntity.getMenuname() + "</font>\"");
						}
					}
					// 当前用户有权限的菜单处于选中状态
					if (menuEntity.getId() != null
							&& menuEntity.getId() > 0) {
						htmlStrBuffer.append(",\"state\":{\"checked\": \"true\"}");
					}
					htmlStrBuffer.append(",\"nodes\":[");
					createMenu(htmlStrBuffer, menuEntity.getId(), menuList);
					htmlStrBuffer.append("]},");
				}
			}
			if (hasSub > 0) {
				htmlStrBuffer.deleteCharAt(htmlStrBuffer.length() - 1);
			}
			return hasSub;
		}
}
