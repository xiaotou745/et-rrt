package com.renrentui.renrenentity.req;

import java.util.ArrayList;

import com.renrentui.renrenentity.Strategy;
import com.renrentui.renrenentity.StrategyChild;

public class StrategyModelReq extends Strategy{
	private ArrayList<StrategyChild> childList;

	public ArrayList<StrategyChild> getChildList() {
		return childList;
	}

	public void setChildList(ArrayList<StrategyChild> childList) {
		this.childList = childList;
	}
}
