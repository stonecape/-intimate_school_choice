package com.bistu.intimate.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.bean.CommentAddBean;
import com.bistu.intimate.dao.CommentMapper;
import com.bistu.intimate.dto.Comment;
import com.bistu.intimate.service.CommentService;
import com.bistu.intimate.vo.CommentVo;

@Service
public class CommentServiceImpl implements CommentService {
	Log logger = LogFactory.getLog(CommentServiceImpl.class);
	@Autowired
	private CommentMapper commentMapper;

	public boolean addNewComment(CommentAddBean addBean) {
		logger.info("===添加新评论===addBean->" + addBean);
		try {
			if(addBean == null) {
				logger.error("addBean == null");
				return false;
			}
			
			if(addBean.getUserId() == null || addBean.getCommentContent() == null || addBean.getIsAnonymous() == null ||
					addBean.getMajorDetailId() == null || StringUtils.isEmpty(addBean.getCommentContent()) || addBean.getUserId() <= 0
					|| addBean.getMajorDetailId() <= 0) {
				logger.error("参数不完整");
				return false;
			}
			
			Comment comment = new Comment();
			comment.setCreateTime(new Date());
			comment.setYn((byte)1);
			BeanUtils.copyProperties(addBean, comment);
			
			int line = commentMapper.insert(comment);
			boolean flag = false;
			if(line > 0) {
				flag = true;
			}
			return flag;
			
		} catch(Exception e) {
			logger.error("发生异常", e);
			return false;
		}
		
	}

	public List<CommentVo> queryCommentsByMajorDetailId(Integer majorDetailId) {
		logger.info("===queryCommentsByMajorDetailId===majorDetailId->" + majorDetailId);
		if(majorDetailId == null || majorDetailId <= 0) {
			logger.error("参数不完整");
			return null;
		}
		
		List<CommentVo> result = commentMapper.queryCommentsByMajorDetailId(majorDetailId);
		
		return result;
	}


}
