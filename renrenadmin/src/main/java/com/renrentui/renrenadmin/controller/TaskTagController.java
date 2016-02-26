package com.renrentui.renrenadmin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.ITaskTagService;
import com.renrentui.renrenentity.TaskTag;

@Controller
@RequestMapping("tasktag")
public class TaskTagController {
	@Autowired
	private ITaskTagService taskTagService;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "管理员");
		view.addObject("currenttitle", "标签管理");
		view.addObject("viewPath", "tasktag/list");
		List<TaskTag>  listData=taskTagService.getAll();
		view.addObject("listData", listData);
		return view;
	}

	@RequestMapping("insert")
	@ResponseBody
	public int insert(HttpServletRequest request,TaskTag tag) {
		UserContext context=UserContext.getCurrentContext(request);
		tag.setCreateName(context.getUserName());
		tag.setUpdateName(context.getUserName());
		return taskTagService.insert(tag);
	}
	@RequestMapping("update")
	@ResponseBody
	public int update(HttpServletRequest request,TaskTag tag) {
		UserContext context=UserContext.getCurrentContext(request);
		tag.setUpdateName(context.getUserName());
		return taskTagService.update(tag);
	}
}
