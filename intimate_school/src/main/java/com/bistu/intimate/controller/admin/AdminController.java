package com.bistu.intimate.controller.admin;

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

import com.bistu.intimate.common.Result;
import com.bistu.intimate.dto.MajorDetail;
import com.bistu.intimate.dto.MajorDetailPassingScore;
import com.bistu.intimate.service.MajorDetailService;
import com.bistu.intimate.service.MajorInfoService;
import com.bistu.intimate.service.SchoolInfoService;
import com.bistu.intimate.vo.MajorDetailVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	Log logger = LogFactory.getLog(AdminController.class);
	@Autowired
	private MajorInfoService majorInfoService;
	@Autowired
	private SchoolInfoService schoolInfoService;
	@Autowired
	private MajorDetailService majorDetailService;
	
	@RequestMapping("/toAddNewMajorDetail")
	public ModelAndView toAddNewMajorDetail() {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<Integer, String> schoolMaps = schoolInfoService.getAllSchoolInfoSelect();
		Map<Integer, String> majorMaps = majorInfoService.getAllMajorInfoSelect();
		result.put("schoolMaps", schoolMaps);
		result.put("majorMaps", majorMaps);
		
		return new ModelAndView("admin/addNewMajorDetail", result);
	}
	
	@RequestMapping("/toUpdateMajorDetail")
	public ModelAndView toUpdateMajorDetail(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<Integer, String> schoolMaps = schoolInfoService.getAllSchoolInfoSelect();
		Map<Integer, String> majorMaps = majorInfoService.getAllMajorInfoSelect();
		result.put("schoolMaps", schoolMaps);
		result.put("majorMaps", majorMaps);
		
		String majorDetailId = req.getParameter("majorDetailId");
		Map<String, String> param = new HashMap<String, String>();
		param.put("majorDetailId", majorDetailId);
		List<MajorDetailVo> majorDetailVoRes = 
				majorDetailService.queryMajorDetailVoByConditions(param);
		result.put("majorDetailVo", majorDetailVoRes != null ? majorDetailVoRes.get(0) : null);
		
		Result<MajorDetail> majorDetailRes = majorDetailService.queryMajorDetailById(Integer.parseInt(majorDetailId));
		result.put("majorDetail", majorDetailRes != null ? majorDetailRes.getValue() : null);
		
		List<MajorDetailPassingScore> passingScoreList = 
				majorDetailService.queryPassingScoreByMajorDetailId(Integer.parseInt(majorDetailId));
		result.put("passingScoreList", passingScoreList);
		
		return new ModelAndView("admin/updateMajorDetail", result);
	}
}
