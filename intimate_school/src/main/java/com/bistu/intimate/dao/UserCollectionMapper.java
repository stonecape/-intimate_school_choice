package com.bistu.intimate.dao;

import com.bistu.intimate.dto.UserCollection;
import com.bistu.intimate.dto.UserCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int countByExample(UserCollectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int deleteByExample(UserCollectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int deleteByPrimaryKey(Integer collectionId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int insert(UserCollection record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int insertSelective(UserCollection record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	List<UserCollection> selectByExample(UserCollectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	UserCollection selectByPrimaryKey(Integer collectionId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByExampleSelective(@Param("record") UserCollection record,
			@Param("example") UserCollectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByExample(@Param("record") UserCollection record, @Param("example") UserCollectionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByPrimaryKeySelective(UserCollection record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_collection
	 * @mbggenerated  Tue Apr 12 22:32:36 CST 2016
	 */
	int updateByPrimaryKey(UserCollection record);
}