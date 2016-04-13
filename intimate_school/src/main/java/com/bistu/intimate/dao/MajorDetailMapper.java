package com.bistu.intimate.dao;

import com.bistu.intimate.dto.MajorDetail;
import com.bistu.intimate.dto.MajorDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MajorDetailMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int countByExample(MajorDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int deleteByExample(MajorDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int deleteByPrimaryKey(Integer majorDetailId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int insert(MajorDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int insertSelective(MajorDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	List<MajorDetail> selectByExample(MajorDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	MajorDetail selectByPrimaryKey(Integer majorDetailId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByExampleSelective(@Param("record") MajorDetail record, @Param("example") MajorDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByExample(@Param("record") MajorDetail record, @Param("example") MajorDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByPrimaryKeySelective(MajorDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table major_detail
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByPrimaryKey(MajorDetail record);
}