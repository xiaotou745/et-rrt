package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.common.PagedResponse;
import com.renrentui.renrenentity.domain.Article;
import com.renrentui.renrenentity.req.PagedArticleReq;

public interface IArticleDao {
	/**
	 * 插入文章
	 * @param req
	 * @return
	 */
	int saveArticle(Article req);
	/**
	 * 异步分页列表
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
