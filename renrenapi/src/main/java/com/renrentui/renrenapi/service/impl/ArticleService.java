package com.renrentui.renrenapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renrentui.renrenapi.dao.inter.IArticleDao;
import com.renrentui.renrenapi.dao.inter.ITaskSetpDao;
import com.renrentui.renrenapi.service.inter.IArticleService;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.common.ResponseBase;
import com.renrentui.renrenentity.domain.Article;
import com.renrentui.renrenentity.req.PagedArticleReq;

@Service
public class ArticleService implements IArticleService{
	@Autowired
	private IArticleDao articleDao;
	@Autowired
	private ITaskSetpDao taskStepDao;
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
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@Override
	public ResponseBase delArticle(long id) {
		ResponseBase resp=new ResponseBase();
		int count=taskStepDao.selectCountByArticle(id);
		if (count>0) {
			resp.setResponseCode(-1);
			resp.setMessage("该文章已有任务使用中，无法删除！");
			return resp;
		}
		int res=articleDao.delArticle(id);
		if (res>0) {
			resp.setMessage("删除文章成功！");
			return resp;
		}else {
			resp.setResponseCode(-2);
			resp.setMessage("该文章已经不存在或者数据异常,请刷新后重试！");
			return resp;
		}
	}

}
