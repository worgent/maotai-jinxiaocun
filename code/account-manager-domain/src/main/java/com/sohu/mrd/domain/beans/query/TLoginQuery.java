package com.sohu.mrd.domain.beans.query;

import com.sohu.mrd.common.query.BaseQuery;

public class TLoginQuery extends BaseQuery {

	private String username;
	private String password;
	private Integer state;
	private Integer delTag;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDelTag() {
		return delTag;
	}

	public void setDelTag(Integer delTag) {
		this.delTag = delTag;
	}

}
