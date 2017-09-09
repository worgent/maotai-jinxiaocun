package com.sohu.service.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sohu.mrd.domain.beans.TLogin;

/**
 * Created by haixingcheng on 2015/2/28.
 */
public interface LoginService {

	void insertTLogin(String uname, String upwd);

	void writeCookieAndLoginContext(HttpServletResponse response);

	void deleteCookies(HttpServletRequest request, HttpServletResponse response);

	int queryTLoginTotalRows(String uname, String upwd);

	TLogin queryTLoginByUname(String uname);

}
