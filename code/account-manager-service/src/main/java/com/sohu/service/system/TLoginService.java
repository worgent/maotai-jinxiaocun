package com.sohu.service.system;

import java.util.List;

import com.sohu.mrd.domain.beans.TLogin;
import com.sohu.mrd.domain.beans.query.TLoginQuery;

public interface TLoginService {

	int queryTLoginTotalRows(TLoginQuery userQuery);

	List<TLogin> queryTLoginList(TLoginQuery userQuery, int pageIndex,
			int pageSize);

	TLogin queryTLoginByUname(String uname);

	TLogin queryTLoginById(int id);

	int insertTLogin(TLogin user);

	int deleteTLoginByUname(String uname);

	int updateTLoginByUname(TLogin user);

	int deleteTLoginById(int id);

	int updateTLoginById(TLogin user);

}
