package com.bistu.intimate.vo;

import java.io.Serializable;

public class CommentVo implements Serializable {

	private static final long serialVersionUID = 1141745671485419334L;
	
	private Integer commentId;
	private Integer userId;
	/**
	 * 0:不匿名
	 */
	private Byte isAnonymous;
	/**
	 * 评论内容
	 */
	private String commentContent;
	/**
	 * 用户名
	 */
	private String userName;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Byte getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(Byte isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
}
