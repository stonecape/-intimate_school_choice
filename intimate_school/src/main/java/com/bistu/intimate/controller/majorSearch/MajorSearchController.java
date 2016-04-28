package com.bistu.intimate.controller.majorSearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.bean.MajorInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.common.Result;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.MajorDetail;
import com.bistu.intimate.dto.MajorDetailPassingScore;
import com.bistu.intimate.dto.MajorInfo;
import com.bistu.intimate.service.MajorDetailService;
import com.bistu.intimate.service.MajorInfoService;
import com.bistu.intimate.service.UserCollectionService;
import com.bistu.intimate.vo.MajorDetailVo;

@Controller
@RequestMapping("/majorSearch")
public class MajorSearchController extends BaseController {
	Log logger = LogFactory.getLog(MajorSearchController.class);
	@Autowired
	private MajorInfoService majorInfoService;
	@Autowired
	private MajorDetailService majorDetailService;
	@Autowired
	private UserCollectionService userCollectionService;
	
	@RequestMapping("/toMajorSearchView")
	public ModelAndView toMajorSearchView() {
		Map<String, Object> result = new HashMap<String, Object>();
		initialView(result);
		result.put("pageNo", 0);
		return new ModelAndView("majorSearch/searchIndex", result);
	}
	
	@RequestMapping("/queryMajorInfo")
	public ModelAndView queryMajorInfo(MajorInfoQueryBean queryBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 初始化
		initialView(result);
		result.put("majorName", queryBean.getMajorName());
		result.put("majorClass", queryBean.getMajorClass());
		result.put("majorType", queryBean.getMajorType());
		result.put("pageNo", queryBean.getPageNo());
		
		Pagination<MajorInfo> pageList = majorInfoService.getMajorInfoPage(queryBean);
		result.put("rows", pageList);
		
		return new ModelAndView("majorSearch/searchIndex", result);
	}
	
	@RequestMapping("/queryMajorInfoDetail")
	public ModelAndView queryMajorInfoDetail(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		String majorId = req.getParameter("majorId");
		logger.info("查询专业info详情,majorId->" + majorId);
		if(StringUtils.isEmpty(majorId)) {
			logger.error("majorId为空");
			return toMajorSearchView();
		}
		// 查询majorInfo
		MajorInfo majorinfo = majorInfoService.getMajorInfoById(Integer.parseInt(majorId));
		result.put("majorInfo", majorinfo);
		
		// 查询MajorDetial
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("majorId", majorId);
		List<MajorDetailVo> detailVoList = 
				majorDetailService.queryMajorDetailVoByConditions(queryMap);
		
		// 查询收藏
		if(this.getSessionUser(req) != null) {
			Set<Integer> collectedMajorDetailIds = 
					userCollectionService.queryCollectMajorDetailIdByUserId(this.getSessionUser(req).getUserId());
			if(collectedMajorDetailIds != null && collectedMajorDetailIds.size() > 0) {
				for(MajorDetailVo detailVo : detailVoList) {
					if(collectedMajorDetailIds.contains(detailVo.getMajorDetailId())) {
						detailVo.setCollect(true);
					}
				}
			}
		}
		result.put("detailVoList", detailVoList);
		
		return new ModelAndView("majorSearch/searchDetail", result);
		
	}
	
	@RequestMapping("/queryPassingScore")
	public ModelAndView queryPassingScore(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		String majorDetailId = req.getParameter("majorDetailId");
		if(StringUtils.isEmpty(majorDetailId)) {
			logger.error("majorDetailId为空");
			return new ModelAndView("majorSearch/passingScores", result);
		}
		// 查专业科目
		Result<MajorDetail> majorDetailResult = 
				majorDetailService.queryMajorDetailById(Integer.parseInt(majorDetailId));
		
		if(majorDetailResult.getSuccess() && majorDetailResult.getValue() != null) {
			MajorDetail majorDetail = majorDetailResult.getValue();
			result.put("majorDetail", majorDetail);
		}
		
		// 差分数线
		List<MajorDetailPassingScore> list = 
				majorDetailService.queryPassingScoreByMajorDetailId(Integer.parseInt(majorDetailId));
		
		result.put("rows", list);
		return new ModelAndView("majorSearch/passingScores", result);
	}
	
	private Map<String, Object> initialView(Map<String, Object> result) {
		List<String> allMajorClass = majorInfoService.getAllMajorClass();
		List<String> allMajorType = majorInfoService.getAllMajorType();
		
		result.put("allMajorClass", allMajorClass);
		result.put("allMajorType", allMajorType);
		
		return result;
	}
}
