package com.bistu.intimate.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bistu.intimate.bean.MajorDetialAddBean;
import com.bistu.intimate.common.Result;
import com.bistu.intimate.controller.BaseController;
import com.bistu.intimate.service.MajorDetailService;

@Controller
@RequestMapping("/ajaxAdmin")
public class AjaxAdminController extends BaseController{
	Log logger = LogFactory.getLog(AjaxAdminController.class);
	@Autowired
	private MajorDetailService majorDetailService;
	
	@RequestMapping("/addMajorDetail")
	@ResponseBody
	public Map<String, Object> addMajorDetail(MajorDetialAddBean addBean, HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("===添加专业详情===");
		logger.info("addBean->" + ToStringBuilder.reflectionToString(addBean));
		Result<Boolean> correspond = majorDetailService.addNewMajorDetail(addBean);
		if(correspond.getSuccess()) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		return result;
		
	}
}

