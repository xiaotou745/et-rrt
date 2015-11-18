package com.renrentui.renrenapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.renrentui.renrenapi.common.DaoBase;
import com.renrentui.renrenapi.dao.inter.IArticleDao;
import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.Article;
import com.renrentui.renrenentity.req.PagedArticleReq;
@Repository
public class ArticleDao extends DaoBase  implements IArticleDao {

	/**
	 * 插入一个新文章
	 * 茹化肖
	 * 2015年11月18日15:49:25
	 */
	@Override
	public int saveArticle(Article req) {
		 return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IArticleDao.saveArticle", req);
	}
	/**
	 * 异步分页列表
	 * 茹化肖
	 * 2015年11月18日16:58:10 
	 */
	@Override
	public PagedResponse<Article> querList(PagedArticleReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("com.renrentui.renrenapi.dao.inter.IArticleDao.querList", req);
	}
	/**
	 * 获取文章详情
	 */
	@Override
	public Article getDetail(Long id) {
		return getReadOnlySqlSessionUtil().selectOne("com.renrentui.renrenapi.dao.inter.IArticleDao.getDetail", id);
	}

}
