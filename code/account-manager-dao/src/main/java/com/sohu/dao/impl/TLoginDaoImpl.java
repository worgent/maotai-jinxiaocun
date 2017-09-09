package com.sohu.dao.impl;

import java.util.List;

import com.sohu.BaseDao;
import com.sohu.dao.TLoginDao;
import com.sohu.mrd.domain.beans.TLogin;
import com.sohu.mrd.domain.beans.query.TLoginQuery;

public class TLoginDaoImpl extends BaseDao implements TLoginDao {

	public int queryTLoginTotalRows(TLoginQuery tLoginQuery) {
		return (Integer) queryForObject("TLogin.queryTLoginTotalRows",
				tLoginQuery);
	}

	public List<TLogin> queryTLoginList(TLoginQuery tLoginQuery) {
		return queryForList("TLogin.queryTLoginList", tLoginQuery);
	}

	public TLogin queryTLoginByUname(String uname) {
		return (TLogin) queryForObject("TLogin.queryTLoginByUname", uname);
	}

	public int insertTLogin(TLogin tLogin) {
		return (Integer) insert("TLogin.insertTLogin", tLogin);
	}

	public int deleteTLoginByUname(String uname) {
		return delete("TLogin.deleteTLoginByUname", uname);
	}

	public int deleteTLoginById(int id) {
		return delete("TLogin.deleteTLoginById", id);
	}

	public int updateTLoginByUname(TLogin tLogin) {
		return update("TLogin.updateTLoginByUname", tLogin);
	}

	public int updateTLoginById(TLogin tLogin) {
		return update("TLogin.updateTLoginById", tLogin);
	}

	public TLogin queryTLoginById(int id) {
		return (TLogin) queryForObject("TLogin.queryTLoginById", id);
	}

}
