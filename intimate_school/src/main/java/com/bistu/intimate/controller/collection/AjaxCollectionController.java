package com.bistu.intimate.controller.collection;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.UserCollectionService;

@Controller
@RequestMapping("/ajaxCollection")
public class AjaxCollectionController extends BaseController{
	Log logger = LogFactory.getLog(AjaxCollectionController.class);
	@Autowired
	private UserCollectionService userCollectionService;
	
	@RequestMapping("/addNewCollection")
	@ResponseBody
	public Map<String, Object> addNewCollection(HttpServletRequest req) {
		logger.info("===添加收藏===");
		Map<String, Object> result = new HashMap<String, Object>();
		String majorDetailId = req.getParameter("majorDetailId");
		logger.info("majorDetailId->" + majorDetailId);
		
		if(StringUtils.isEmpty(majorDetailId)) {
			logger.error("majorDetailId为空");
			result.put("success", false);
			return result;
		}
		
		User user = this.getSessionUser(req);
		
		if(user == null) {
			logger.error("没有登录");
			result.put("success", false);
			return result;
		}
		
		Result<Boolean> correspond = 
				userCollectionService.addNewCollection(user.getUserId(), Integer.parseInt(majorDetailId));
		
		if(correspond.getSuccess() && correspond.getValue()) {
			result.put("success", true);
			return result;
		} else {
			logger.error("添加收藏失败");
			result.put("success", false);
			return result;
		}
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteCollection(HttpServletRequest req) {
		logger.info("deleteCollection");
		String majorDetailId = req.getParameter("majorDetailId");
		
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.getSessionUser(req);
		if(user == null) {
			logger.error("未登陆");
			result.put("success", false);
			return result;
		}
		
		Result<Boolean> re = 
				userCollectionService.deleteCollection(user.getUserId(), Integer.parseInt(majorDetailId));
		if(re.getSuccess() && re.getValue()) {
			logger.info("取消关注成功");
			result.put("success", true);
			return result;
		}
		
		result.put("success", false);
		return result;
	}
}
