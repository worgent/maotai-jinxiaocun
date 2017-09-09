package com.sohu.service.system.impl;

import java.util.List;

import com.sohu.dao.TLoginDao;
import com.sohu.mrd.common.query.PaginatedArrayList;
import com.sohu.mrd.common.query.PaginatedList;
import com.sohu.mrd.domain.beans.TLogin;
import com.sohu.mrd.domain.beans.query.TLoginQuery;
import com.sohu.service.system.TLoginService;

public class TLoginServiceImpl implements TLoginService {

	private TLoginDao tLoginDao;

	public void setTLoginDao(TLoginDao tLoginDao) {
		this.tLoginDao = tLoginDao;
	}

	public List<TLogin> queryTLoginList(TLoginQuery tLoginQuery, int pageIndex,
			int pageSize) {
		PaginatedList<TLogin> list = new PaginatedArrayList<TLogin>(pageIndex,
				pageSize);
		list.setTotalItem(tLoginDao.queryTLoginTotalRows(tLoginQuery));
		tLoginQuery
				.setStartRow(tLoginQuery.getMySqlStartRow(list.getStartRow()));
		tLoginQuery.setEndRow(list.getPageSize());
		list.addAll(tLoginDao.queryTLoginList(tLoginQuery));
		return list;
	}

	public int insertTLogin(TLogin user) {
		return tLoginDao.insertTLogin(user);
	}

	public int deleteTLoginByUname(String uname) {
		return tLoginDao.deleteTLoginByUname(uname);
	}

	public int updateTLoginByUname(TLogin user) {
		return tLoginDao.updateTLoginById(user);
	}

	public int queryTLoginTotalRows(TLoginQuery tLoginQuery) {
		return tLoginDao.queryTLoginTotalRows(tLoginQuery);
	}

	public int deleteTLoginById(int id) {
		return tLoginDao.deleteTLoginById(id);
	}

	public int updateTLoginById(TLogin user) {
		return tLoginDao.updateTLoginById(user);
	}

	public TLogin queryTLoginByUname(String uname) {
		return tLoginDao.queryTLoginByUname(uname);
	}

	public TLogin queryTLoginById(int id) {
		return tLoginDao.queryTLoginById(id);
	}

}
