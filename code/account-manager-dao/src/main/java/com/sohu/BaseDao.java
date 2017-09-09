package com.sohu;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public class BaseDao {

	public final static Logger log = LogManager.getLogger(BaseDao.class);

	private SqlSessionTemplate sqlSession;

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Object queryForObject(String statementName)
			throws DataAccessException {
		return sqlSession.selectOne(statementName);
	}

	public Object queryForObject(String statementName, Object parameterObject)
			throws DataAccessException {
		return sqlSession.selectOne(statementName, parameterObject);
	}

	public List queryForList(String statementName) throws DataAccessException {
		return sqlSession.selectList(statementName);
	}

	public List queryForList(String statementName, Object parameterObject)
			throws DataAccessException {
		return sqlSession.selectList(statementName, parameterObject);
	}

	public Map queryForMap(String statementName, String keyProperty) {
		return sqlSession.selectMap(statementName, keyProperty, keyProperty);
	}

	public Map queryForMap(String statementName, Object parameterObject,
			String keyProperty) {
		return sqlSession
				.selectMap(statementName, parameterObject, keyProperty);
	}

	public Object insert(String statementName) throws DataAccessException {
		return sqlSession.insert(statementName);
	}

	public Object insert(String statementName, Object parameterObject)
			throws DataAccessException {
		return sqlSession.insert(statementName, parameterObject);
	}

	public int delete(String statementName) throws DataAccessException {
		return sqlSession.delete(statementName);
	}

	public int delete(String statementName, Object parameterObject)
			throws DataAccessException {
		return sqlSession.delete(statementName, parameterObject);
	}

	public int update(String statementName) throws DataAccessException {
		return sqlSession.update(statementName);
	}

	public int update(String statementName, Object parameterObject)
			throws DataAccessException {
		return sqlSession.update(statementName, parameterObject);
	}

}
