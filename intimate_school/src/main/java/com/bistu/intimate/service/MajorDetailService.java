package com.bistu.intimate.service;

import java.util.List;
import java.util.Map;

import com.bistu.intimate.bean.MajorDetialAddBean;
import com.bistu.intimate.common.Result;
import com.bistu.intimate.dto.MajorDetail;
import com.bistu.intimate.dto.MajorDetailPassingScore;
import com.bistu.intimate.vo.MajorDetailVo;

public interface MajorDetailService {
	/**
	 * 添加新的专业详情信息
	 * 
	 * @param addBean
	 * @return
	 */
	Result<Boolean> addNewMajorDetail(MajorDetialAddBean addBean);

	/**
	 * 更新专业详情信息
	 * 
	 * @param addBean
	 * @param majorDetailId
	 * @return
	 */
	Result<Boolean> updateMajorDetail(MajorDetialAddBean updateBean, Integer majorDetailId);

	/**
	 * 根据map中的条件查询
	 * 
	 * @param queryMap
	 * @return
	 */
	List<MajorDetailVo> queryMajorDetailVoByConditions(Map<String, String> queryMap);

	/**
	 * 根据majorDetailId查
	 * 
	 * @param majorDetailId
	 * @return
	 */
	Result<MajorDetail> queryMajorDetailById(Integer majorDetailId);

	/**
	 * 查询分数线
	 * 
	 * @param majorDetailId
	 * @return
	 */
	List<MajorDetailPassingScore> queryPassingScoreByMajorDetailId(Integer majorDetailId);
}
