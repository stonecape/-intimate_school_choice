package com.bistu.intimate.controller.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.User;
import com.bistu.intimate.service.UserCollectionService;
import com.bistu.intimate.vo.MajorDetailVo;

@Controller
@RequestMapping("/collection")
public class CollectionController extends BaseController{
	Log logger = LogFactory.getLog(CollectionController.class);
	@Autowired
	private UserCollectionService userCollectionService;
	
	@RequestMapping("/toMyCollectionView")
	public ModelAndView toMyCollectionView(HttpServletRequest req) {
		logger.info("toMyCollectionView");
		Map<String, Object> result = new HashMap<String, Object>();
		User user = this.getSessionUser(req);
		if(user == null) {
			logger.error("未登陆");
			return new ModelAndView("collection/myCollection", result);
		}
		
		List<MajorDetailVo> list = userCollectionService.queryCollectedMajorDetailByUserId(user.getUserId());
		result.put("rows", list);
		return new ModelAndView("collection/myCollection", result);
	}
}
