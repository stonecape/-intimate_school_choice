package com.bistu.intimate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bistu.intimate.bean.MajorInfoQueryBean;
import com.bistu.intimate.common.Pagination;
import com.bistu.intimate.dao.MajorInfoMapper;
import com.bistu.intimate.dto.MajorInfo;
import com.bistu.intimate.dto.MajorInfoExample;
import com.bistu.intimate.service.MajorInfoService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class MajorInfoServiceImpl implements MajorInfoService{
	Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private MajorInfoMapper majorInfoMapper;
	
	public Pagination<MajorInfo> getMajorInfoPage(MajorInfoQueryBean queryBean) {
		logger.info("=====分页查询专业info=====");
		try {
			if(queryBean == null) {
				logger.error("参数queryBean == null");
				return null;
			}
			logger.info("queryBean->" + ToStringBuilder.reflectionToString(queryBean));
			
			MajorInfoExample ex = new MajorInfoExample();
			
			if(StringUtils.isEmpty(queryBean.getMajorClass())) {
				ex.or().andMajorClassEqualTo(queryBean.getMajorClass());
			}
			if(StringUtils.isEmpty(queryBean.getMajorName())) {
				ex.or().andMajorNameEqualTo(queryBean.getMajorName());
			}
			if(StringUtils.isEmpty(queryBean.getMajorType())) {
				ex.or().andMajorTypeEqualTo(queryBean.getMajorType());
			}
			
			PageBounds pageBounds = new PageBounds();
			pageBounds.setLimit(queryBean.getPageSize());
			pageBounds.setPage(queryBean.getPageNo());
			
			List<MajorInfo> resList = majorInfoMapper.selectByExample(ex, pageBounds);
			PageList<MajorInfo> pageList = (PageList<MajorInfo>) resList;
			int totalCount = pageList.getPaginator().getTotalCount();
			Pagination<MajorInfo> page = 
					new Pagination<MajorInfo>(queryBean.getPageSize(), queryBean.getPageNo(), totalCount, resList);
			logger.info("最后封装的分页返回值->" + ToStringBuilder.reflectionToString(page));
			return page;
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("分页查询专业发生异常->", e);
			return null;
		}
	}

	public Map<Integer, String> getAllMajorInfoSelect() {
		logger.info("=====getAllMajorInfoSelect=====");
		Map<Integer, String> returnMap = new HashMap<Integer, String>();
		MajorInfoExample ex = new MajorInfoExample();
		List<MajorInfo> list = majorInfoMapper.selectByExample(ex);
		if(list != null && list.size() > 0) {
			for(MajorInfo info : list) {
				returnMap.put(info.getMajorId(), info.getMajorName());
			}
		}
		return returnMap;
	}

}
