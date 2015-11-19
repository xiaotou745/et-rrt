package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IArticleDao;
import com.renrentui.renrenapi.service.inter.IArticleService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.Article;
import com.renrentui.renrenentity.req.PagedArticleReq;

@Service
public class ArticleService implements IArticleService{
	@Autowired
	private IArticleDao articleDao;
	/**
	 * 保存文章 
	 * 茹化肖
	 * 2015年11月18日15:57:23
	 */
	@Override
	public int saveArticle(Article req) {
		
		return articleDao.saveArticle(req);
	}
	/**
	 * 异步列表
	 * 茹化肖]
	 * 2015年11月18日16:59:52
	 */
	@Override
	public PagedResponse<Article> querList(PagedArticleReq req) {
		return articleDao.querList(req);
	}
	/**
	 * 获取文章详情
	 */
	@Override
	public Article getDetail(Long id) {
		return articleDao.getDetail(id);
	}

}
