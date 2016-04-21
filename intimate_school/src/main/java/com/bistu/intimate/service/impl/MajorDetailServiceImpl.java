package com.bistu.intimate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bistu.intimate.bean.MajorDetialAddBean;
import com.bistu.intimate.common.Result;
import com.bistu.intimate.dao.MajorDetailMapper;
import com.bistu.intimate.dao.MajorDetailPassingScoreMapper;
import com.bistu.intimate.dto.MajorDetail;
import com.bistu.intimate.dto.MajorDetailPassingScore;
import com.bistu.intimate.dto.MajorDetailPassingScoreExample;
import com.bistu.intimate.service.MajorDetailService;
import com.bistu.intimate.vo.MajorDetailVo;

@Service
public class MajorDetailServiceImpl implements MajorDetailService {
	Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private MajorDetailMapper majorDetailMapper;
	@Autowired
	private MajorDetailPassingScoreMapper detailPassingScoreMapper;

	public Result<Boolean> addNewMajorDetail(MajorDetialAddBean addBean) {
		logger.info("=====添加新的专业信息=====");
		Result<Boolean> result = new Result<Boolean>();
		logger.info("addBean->" + ToStringBuilder.reflectionToString(addBean));
		
		try {
			// 1. 拼装专业详情并插入数据库
			MajorDetail majorDetail = new MajorDetail();
			BeanUtils.copyProperties(addBean, majorDetail);
			majorDetail.setYn((byte) 1);
			majorDetail.setCreateTime(new Date());
			int insertLine = majorDetailMapper.insertSelective(majorDetail);
			if(insertLine <= 0) {
				result.setSuccess(false);
				logger.error("专业详情插入数据库失败");
				return result;
			}
			
			int majorDetailId = majorDetail.getMajorDetailId();
			
			// 2. 拼装分数线并插入数据库
			List<MajorDetailPassingScore> passingScores = new ArrayList<MajorDetailPassingScore>();
			passingScores.add(getPassingScoreByConditions(majorDetailId, addBean.getYear1(), addBean.getTotalPassingScore1(),
					addBean.getLesson1PassingScore1(), addBean.getLesson2PassingScore1(),
					addBean.getLesson3PassingScore1(), addBean.getLesson4PassingScore1()));
			passingScores.add(getPassingScoreByConditions(majorDetailId, addBean.getYear2(), addBean.getTotalPassingScore2(),
					addBean.getLesson1PassingScore2(), addBean.getLesson2PassingScore2(),
					addBean.getLesson3PassingScore2(), addBean.getLesson4PassingScore2()));
			passingScores.add(getPassingScoreByConditions(majorDetailId, addBean.getYear3(), addBean.getTotalPassingScore3(),
					addBean.getLesson1PassingScore3(), addBean.getLesson2PassingScore3(),
					addBean.getLesson3PassingScore3(), addBean.getLesson4PassingScore3()));
			
			for(MajorDetailPassingScore dto : passingScores) {
				detailPassingScoreMapper.insertSelective(dto);
			}
			
			result.setSuccess(true);
			return result;
			
		} catch(Exception e) {
			result.setSuccess(false);
			logger.error("专业详情和分数线插入数据库操作异常", e);
			return result;
		}
		
	}
	
	private MajorDetailPassingScore getPassingScoreByConditions(int MajorDetialId, String year, int total, int lesson1, 
			int lesson2, int lesson3, int lesson4) {
		MajorDetailPassingScore returnScore = new MajorDetailPassingScore();
		returnScore.setMajorDetialId(MajorDetialId);
		returnScore.setYear(getYear(year));
		returnScore.setTotalPassingScore(total);
		returnScore.setLesson1PassingScore(lesson1);
		returnScore.setLesson2PassingScore(lesson2);
		returnScore.setLesson3PassingScore(lesson3);
		returnScore.setLesson4PassingScore(lesson4);
		returnScore.setCreateTime(new Date());
		returnScore.setYn((byte) 1);
		return returnScore;
	}
	
	private String getYear(String yearMonth) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar c = Calendar.getInstance();
			Date yearM = df.parse(yearMonth);
			c.setTime(yearM);
			return String.valueOf(c.get(Calendar.YEAR));
		} catch(Exception e) {
			logger.error("转换日期发生异常->", e);
			return null;
		}
		
	}

	public List<MajorDetailVo> queryMajorDetailVoByConditions(Map<String, String> queryMap) {
		logger.info("===根据map中的条件查询MajorDetailVo,queryMap->" + queryMap);
		if(queryMap == null || queryMap.size() <= 0) {
			logger.error("queryMap为空");
			return null;
		}
		
		Map<String, Object> dbQuery = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(queryMap.get("majorId"))) {
			dbQuery.put("majorId", Integer.parseInt(queryMap.get("majorId")));
		}
		
		if(StringUtils.isNotEmpty(queryMap.get("schoolId"))) {
			dbQuery.put("schoolId", Integer.parseInt(queryMap.get("schoolId")));
		}
		
		try {
			return majorDetailMapper.getMajorDetailVoByConditions(dbQuery);
		} catch(Exception e) {
			logger.error("queryMajorDetailVoByConditions发生异常", e);
			return null;
		}
		
	}

	public List<MajorDetailPassingScore> queryPassingScoreByMajorDetailId(Integer majorDetailId) {
		logger.info("===查询分数线===");
		if(majorDetailId == null || majorDetailId <= 0) {
			logger.error("majorDetailId == null || majorDetailId <= 0");
			return null;
		}
		try {
			MajorDetailPassingScoreExample ex = new MajorDetailPassingScoreExample();
			ex.or().andMajorDetialIdEqualTo(majorDetailId);
			return detailPassingScoreMapper.selectByExample(ex);
			
		} catch(Exception e) {
			logger.error("发生异常", e);
			return null;
		}
	}

}
