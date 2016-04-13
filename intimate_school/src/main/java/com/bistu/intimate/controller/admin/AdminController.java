package com.bistu.intimate.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bistu.intimate.service.MajorInfoService;
import com.bistu.intimate.service.SchoolInfoService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	Log logger = LogFactory.getLog(AdminController.class);
	@Autowired
	private MajorInfoService majorInfoService;
	@Autowired
	private SchoolInfoService schoolInfoService;
	
	@RequestMapping("/toAddNewMajorDetail")
	public ModelAndView toAddNewMajorDetail() {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<Integer, String> schoolMaps = schoolInfoService.getAllSchoolInfoSelect();
		Map<Integer, String> majorMaps = majorInfoService.getAllMajorInfoSelect();
		result.put("schoolMaps", schoolMaps);
		result.put("majorMaps", majorMaps);
		
		return new ModelAndView("admin/addNewMajorDetail", result);
	}
}
