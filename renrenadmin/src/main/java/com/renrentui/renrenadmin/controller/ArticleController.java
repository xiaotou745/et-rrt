package com.renrentui.renrenadmin.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renrentui.renrenadmin.common.UserContext;
import com.renrentui.renrenapi.service.inter.IArticleService;
import com.renrentui.renrenentity.AccountInfo;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.Article;
import com.renrentui.renrenentity.req.PagedArticleReq;


@Controller
@RequestMapping("article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	/**
	 * 新建文章
	 * 茹化肖
	 * 2015年11月18日15:29:56
	 * @return
	 */
	@RequestMapping("new")
	public ModelAndView newArticle() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "文章管理");
		view.addObject("currenttitle", "新建文章");
		view.addObject("viewPath", "article/new");
		return view;
	}
	/**
	 * 保存文章
	 * 茹化肖
	 * 2015年11月18日15:30:00
	 * @return
	 */
	@RequestMapping("savearticle")
	@ResponseBody
	public int saveArticle(Article req,HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		req.setCreateName(context.getUserName());
		req.setUpdateName(context.getUserName());
		int a =  articleService.saveArticle(req);
		return a;
	}
	
	/**
	 * 新建文章
	 * 茹化肖
	 * 2015年11月18日15:29:56
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "文章管理");
		view.addObject("currenttitle", "文章列表");
		view.addObject("viewPath", "article/list");
		return view;
	}
	
	/**
	 * 新建文章
	 * 茹化肖
	 * 2015年11月18日15:29:56
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedArticleReq req) {
		PagedResponse<Article> resp = articleService.querList(req);
		ModelAndView view = new ModelAndView();
		view.addObject("listData", resp);
		view.addObject("viewPath", "article/listdo");
		return view;
	}
	
	/**
	 * 文章详情
	 * 茹化肖
	 * 2015年11月18日15:29:56
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(Long id) {
		Article detailArticle = articleService.getDetail(id);
		ModelAndView view = new ModelAndView();
		view.addObject("detail", detailArticle);
		view.addObject("viewPath", "article/detail");
		return view;
	}
}
