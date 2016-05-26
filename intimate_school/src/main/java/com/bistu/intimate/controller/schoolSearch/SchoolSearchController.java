package com.bistu.intimate.controller.schoolSearch;

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

import com.bistu.intimate.bean.SchoolInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.SchoolInfo;
import com.bistu.intimate.service.MajorDetailService;
import com.bistu.intimate.service.SchoolInfoService;
import com.bistu.intimate.service.UserCollectionService;
import com.bistu.intimate.vo.MajorDetailVo;

@Controller
@RequestMapping("/schoolSearch")
public class SchoolSearchController extends BaseController {
	Log logger = LogFactory.getLog(SchoolSearchController.class);
	@Autowired
	private SchoolInfoService schoolInfoService;
	
	@Autowired
	private MajorDetailService majorDetailService;
	
	@Autowired
	private UserCollectionService userCollectionService;
	
	@RequestMapping("/toSchoolSearchView")
	public ModelAndView toSchoolSearchView() {
		Map<String, Object> result = new HashMap<String, Object>();
		initialView(result);
		result.put("pageNo", 0);
		return new ModelAndView("schoolSearch/searchIndex", result);
	}
	
	@RequestMapping("/querySchoolInfo")
	public ModelAndView querySchoolInfo(SchoolInfoQueryBean queryBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 初始化
		initialView(result);
		result.put("schoolName", queryBean.getSchoolName());
		result.put("province", queryBean.getProvince());
		result.put("area", queryBean.getArea());
		result.put("schoolProperty", queryBean.getSchoolProperty());
		result.put("schoolType", queryBean.getSchoolType());
		result.put("schoolCharacter", queryBean.getSchoolCharacter());
		
		Pagination<SchoolInfo> pageList = schoolInfoService.getSchoolInfoPage(queryBean);
		result.put("rows", pageList);
		
		return new ModelAndView("schoolSearch/searchIndex", result);
		
	}
	
	@RequestMapping("/querySchoolInfoDetail")
	public ModelAndView querySchoolInfoDetail(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		String schoolId = req.getParameter("schoolId");
		logger.info("查询学校info详情,schoolId->" + schoolId);
		if(StringUtils.isEmpty(schoolId)) {
			logger.error("schoolId为空");
			return toSchoolSearchView();
		}
		
		//查询schoolInfo
		SchoolInfo schoolInfo = schoolInfoService.getSchoolInfoById(Integer.parseInt(schoolId));
		result.put("schoolInfo", schoolInfo);
		
		// 查询MajorDetial
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("schoolId", schoolId);
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
			
			if(this.getSessionUser(req).getIsManager() == (byte)1) {
				result.put("isManager", 1);
			} else {
				result.put("isManager", 0);
			}
		}
		result.put("detailVoList", detailVoList);
		
		return new ModelAndView("schoolSearch/searchDetail", result);
		
	}
	
	private Map<String, Object> initialView(Map<String, Object> result) {
		Map<String, Object> re = schoolInfoService.getSchoolSearchInitial();
		result.putAll(re);
		return result;
	}
}
