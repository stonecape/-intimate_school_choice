package com.bistu.intimate.service;

import java.util.List;

import com.bistu.intimate.bean.CommentAddBean;
import com.bistu.intimate.vo.CommentVo;

public interface CommentService {
	
	/**
	 * 添加新评论
	 * @param addBean
	 * @return
	 */
	boolean addNewComment(CommentAddBean addBean);
	
	/**
	 * 根据专业详情Id查询评论
	 * @param majorDetailId
	 * @return
	 */
	List<CommentVo> queryCommentsByMajorDetailId(Integer majorDetailId);
	
}
