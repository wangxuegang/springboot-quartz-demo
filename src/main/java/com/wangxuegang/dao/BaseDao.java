package com.wangxuegang.dao;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseDao {

	void save(Object entity);

	void update(Object entity);

	<T> void delete(Class<T> entityClass, Object entityid);

	<T> void delete(Class<T> entityClass, Object[] entityids);

	Long nativeQueryCount(String nativeSql, Object... params);

	<T> List<T> nativeQueryPagingList(Class<T> resultClass, Pageable pageable,String nativeSql, Object... params);

}
