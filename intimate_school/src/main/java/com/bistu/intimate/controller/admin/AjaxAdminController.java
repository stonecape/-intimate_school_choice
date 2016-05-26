package com.bistu.intimate.controller.admin;

import java.util.HashMap;
import java.util.List;
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
import com.bistu.intimate.vo.MajorDetailVo;

@Controller
@RequestMapping("/ajaxAdmin")
public class AjaxAdminController extends BaseController {
	Log logger = LogFactory.getLog(AjaxAdminController.class);
	@Autowired
	private MajorDetailService majorDetailService;

	@RequestMapping("/addMajorDetail")
	@ResponseBody
	public Map<String, Object> addMajorDetail(MajorDetialAddBean addBean, HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("===添加专业详情===");
		logger.info("addBean->" + ToStringBuilder.reflectionToString(addBean));

		// 1. 校验是否存在同样的学校同样的专业
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("majorId", addBean.getMajorId() + "");
		queryMap.put("schoolId", addBean.getSchoolId() + "");
		List<MajorDetailVo> majorDetailVos = majorDetailService.queryMajorDetailVoByConditions(queryMap);
		if (majorDetailVos != null && majorDetailVos.size() > 0) {
			result.put("success", false);
			result.put("msg", "相同的学校专业已经存在");
			return result;
		}

		// 2. 若没有 插入
		Result<Boolean> correspond = majorDetailService.addNewMajorDetail(addBean);
		if (correspond.getSuccess()) {
			result.put("success", true);
		} else {
			result.put("success", false);
			result.put("msg", "插入失败，请联系管理员");
		}
		return result;

	}

	@RequestMapping("/updateMajorDetail")
	@ResponseBody
	public Map<String, Object> updateMajorDetail(MajorDetialAddBean addBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("===更新专业详情===");
		logger.info("addBean->" + ToStringBuilder.reflectionToString(addBean));

		Result<Boolean> correspond = majorDetailService.updateMajorDetail(addBean, addBean.getMajorDetailId());
		if (correspond.getSuccess()) {
			result.put("success", true);
		} else {
			result.put("success", false);
			result.put("msg", "更新失败，请联系管理员");
		}
		return result;

	}
}
