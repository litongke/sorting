package com.chufinfo.sorting.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 * @author kenyon
 */
@Deprecated
public interface BaseDao<T> {
	/**
	 * 根据实体类保存相应的数据
	 * @param t
	 */
	void save(T t);
	/**
	 * 根据Map存储相应的数据
	 * @param map
	 */
	void save(Map<String, Object> map);
	/**
	 * 批量保存相应的数据
	 * @param list
	 */
	void saveBatch(List<T> list);
	/**
	 * 根据实体类更新数据
	 * @param t
	 * @return
	 */
	int update(T t);
	/**
	 * 根据Map保存相应的数据
	 * @param map
	 * @return
	 */
	int update(Map<String, Object> map);
	/**
	 * 根据主键删除相应的数据
	 * @param id
	 * @return
	 */
	int delete(Object id);
	/**
	 * 根据多个字段删除相应的数据
	 * @param map
	 * @return
	 */
	int delete(Map<String, Object> map);
	/**
	 * 根据主键批量删除数据
	 * @param id
	 * @return
	 */
	int deleteBatch(Object[] id);
	/**
	 * 根据主键查询相应的数据（单个）
	 * @param id
	 * @return
	 */
	T queryObject(Object id);
	/**
	 * 根据多个属性查询多条数据
	 * @param map
	 * @return
	 */
	List<T> queryList(Map<String, Object> map);
	/**
	 * 根据某个值查询多个数据
	 * @param id
	 * @return
	 */
	List<T> queryList(Object id);
	/**
	 * 根据多个条件查询数量
	 * @param map
	 * @return
	 */
	int queryTotal(Map<String, Object> map);
	/**
	 * 查询当前表的所有数据
	 * @return
	 */
	int queryTotal();
}
