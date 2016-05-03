package com.bistu.intimate.controller.majorDetail;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bistu.intimate.bean.CommentAddBean;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.CommentService;

@Controller
@RequestMapping("/ajaxMajorDetail")
public class AjaxMajorDetailController extends BaseController {
	Log logger = LogFactory.getLog(AjaxMajorDetailController.class);
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/addComment")
	@ResponseBody
	public Map<String, Object> addComment(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.getSessionUser(req);
		if(user == null) {
			logger.error("没有登陆");
			result.put("success", false);
			return result;
		}
		Integer userId = user.getUserId();
		String isAnonymous = req.getParameter("isAnonymous");
		String majorDetailId = req.getParameter("majorDetailId");
		String commentContent = req.getParameter("commentContent");
		
		if(StringUtils.isEmpty(isAnonymous) 
				|| StringUtils.isEmpty(majorDetailId) 
				|| StringUtils.isEmpty(commentContent)) {
			logger.error("参数不合法");
			result.put("success", false);
			return result;
		}
		
		CommentAddBean addBean = new CommentAddBean();
		addBean.setCommentContent(commentContent);
		addBean.setIsAnonymous(Byte.valueOf(isAnonymous));
		addBean.setMajorDetailId(Integer.parseInt(majorDetailId));
		addBean.setUserId(userId);
		boolean record = commentService.addNewComment(addBean);
		
		result.put("success", record);
		return result;
	}
}
