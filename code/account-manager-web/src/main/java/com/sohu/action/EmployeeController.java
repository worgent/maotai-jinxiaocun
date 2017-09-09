package com.sohu.action;

import com.sohu.mrd.domain.beans.TEmployee;
import com.sohu.service.jinxiaocun.EmployeeService;
import com.sohu.service.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

	private final static Logger log = Logger.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/ajax/delete")
	public @ResponseBody
	String ajaxDelete(HttpServletRequest request, Model model,
				   @RequestParam(required = true) int id) throws Exception {
		//根据登录人的session获得store_id
		int userStoreId = 1;
		//显示客户列表，另需展示客户欠款信息，门店信息;
		employeeService.delete(id);
		//欠款
		return "1";
	}

	@RequestMapping(value = "/update")
	public int update(HttpServletRequest request, Model model,
					  @RequestParam(required = true) int id,
					  @RequestParam(required = true) TEmployee tEmployee) throws Exception {
		//根据登录人的session获得store_id
		int userStoreId = 1;
		//显示客户列表，另需展示客户欠款信息，门店信息;
		employeeService.update(id, tEmployee);
		//欠款
		return 0;
	}

	@RequestMapping(value = "/insert")
	public int insert(HttpServletRequest request, Model model,
					  @RequestParam(required = true) TEmployee tEmployee) throws Exception {
		//根据登录人的session获得store_id
		int userStoreId = 1;
		//显示客户列表，另需展示客户欠款信息，门店信息;
		employeeService.insert(tEmployee);
		//欠款
		return 0;
	}

}