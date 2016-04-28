package com.bistu.intimate.controller.schoolSearch;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.bean.SchoolInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.dto.SchoolInfo;
import com.bistu.intimate.service.SchoolInfoService;

@Controller
@RequestMapping("/schoolSearch")
public class SchoolSearchController extends BaseController {
	Log logger = LogFactory.getLog(SchoolSearchController.class);
	@Autowired
	private SchoolInfoService schoolInfoService;
	
	@RequestMapping("/toSchoolSearchView")
	public ModelAndView toMajorSearchView() {
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
	
	private Map<String, Object> initialView(Map<String, Object> result) {
		Map<String, Object> re = schoolInfoService.getSchoolSearchInitial();
		result.putAll(re);
		return result;
	}
}
