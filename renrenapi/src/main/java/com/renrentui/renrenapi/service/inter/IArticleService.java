package com.renrentui.renrenapi.service.inter;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.Article;
import com.renrentui.renrenentity.req.PagedArticleReq;

public interface IArticleService {
	/**
	 * 插入文章
	 * @param req
	 * @return
	 */
	int saveArticle(Article req);
	/**
	 * 异步分页列表
	 * 茹化肖
	 * 2015年11月18日16:57:31
	 * 
	 * @param req
	 * @return
	 */
	PagedResponse<Article> querList(PagedArticleReq req);
	/**
	 * 获取文章详情
	 * @param id
	 * @return
	 */
	Article getDetail(Long id);
}
