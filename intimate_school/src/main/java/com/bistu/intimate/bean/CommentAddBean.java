package com.bistu.intimate.bean;

public class CommentAddBean extends BaseBean{

	private static final long serialVersionUID = 6391470568183069480L;
	
	/**
	 * 1:匿名, 0:不匿名
	 */
	private Byte isAnonymous;
	
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 专业详情Id
	 */
	private Integer majorDetailId;
	
	/**
	 * 评论内容
	 */
	private String commentContent;

	public Byte getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(Byte isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public Integer getMajorDetailId() {
		return majorDetailId;
	}

	public void setMajorDetailId(Integer majorDetailId) {
		this.majorDetailId = majorDetailId;
	}

	@Override
	public String toString() {
		return "CommentAddBean [isAnonymous=" + isAnonymous + ", userId=" + userId + ", majorDetailId=" + majorDetailId
				+ ", commentContent=" + commentContent + "]";
	}
	
	
}
