package com.bistu.intimate.controller.majorDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.common.Result;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.MajorDetail;
import com.bistu.intimate.dto.MajorDetailPassingScore;
import com.bistu.intimate.service.CommentService;
import com.bistu.intimate.service.MajorDetailService;
import com.bistu.intimate.vo.CommentVo;
import com.bistu.intimate.vo.MajorDetailVo;

@Controller
@RequestMapping("/majorDetail")
public class MajorDetailController extends BaseController {
	Log logger = LogFactory.getLog(MajorDetailController.class);
	@Autowired
	private CommentService commentService;
	@Autowired
	private MajorDetailService majorDetailService;
	
	@RequestMapping("/toMajorDetailView")
	public ModelAndView toMajorDetailView(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		String majorDetailId = req.getParameter("majorDetailId");
		if(StringUtils.isEmpty(majorDetailId)) {
			logger.error("majorDetailId为空");
			return new ModelAndView("majorDetail/detailIndex", result);
		}
		
		// 查询MajorDetial
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("majorDetailId", majorDetailId);
		List<MajorDetailVo> detailVoList = 
				majorDetailService.queryMajorDetailVoByConditions(queryMap);
		result.put("majorDetailVo", detailVoList == null ? null : detailVoList.get(0));
		
		// 查评论
		List<CommentVo> commentList = 
				commentService.queryCommentsByMajorDetailId(Integer.parseInt(majorDetailId));
		result.put("commentList", commentList);
		
		return new ModelAndView("majorDetail/detailIndex", result);
		
	}
	
}
