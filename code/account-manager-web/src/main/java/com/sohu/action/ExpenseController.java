package com.sohu.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sohu.TimeHandler;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.StringUtil;
import com.sohu.mrd.domain.util.WebMvcHelper;
import com.sohu.service.jinxiaocun.EmployeeService;
import com.sohu.service.jinxiaocun.ExpenseService;
import com.sohu.service.jinxiaocun.StoreService;
import com.sohu.service.login.LoginService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/financial/cost")
public class ExpenseController {

	private final static Logger log = Logger.getLogger(ExpenseController.class);

	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StoreService storeService;


	@RequestMapping(value = "/ajax/delete")
	public @ResponseBody
	String ajaxDelete(HttpServletRequest request, Model model,
				   @RequestParam(required = true) int id) throws Exception {
		expenseService.delete(id);

		return "1";
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model
						) throws Exception {
		//根据登录人的session获得expense_id
		int userStoreId = 1;

		String serailNumber = CunUtil.parseRequestString(request, "serailNumber");
		int type = CunUtil.parseRequestInt(request, "type");
		int handlerId = CunUtil.parseRequestInt(request, "handlerId");
		int listerId = CunUtil.parseRequestInt(request, "listerId");
		int storeId = CunUtil.parseRequestInt(request, "storeId");
		Date businessDateStart = CunUtil.parseRequestDate(request, "businessDateStart");
		Date businessDateEnd = CunUtil.parseRequestDate(request, "businessDateEnd");
		Date createdDateStart = CunUtil.parseRequestDate(request, "createdDateStart");
		Date createdDateEnd = CunUtil.parseRequestDate(request, "createdDateEnd");
		int pageNumber=1;
		int pageSize = 1000;
		List<TExpenseAssociation> tExpenses = expenseService.query(
				 serailNumber,  type,
		 handlerId,  listerId,  storeId,
		 businessDateStart,  businessDateEnd,
				 createdDateStart,  createdDateEnd,
				pageNumber, pageSize);

		model.addAttribute("tExpenses", tExpenses);
		List<TStore> tStores = storeService.query(1, 100);
		model.addAttribute("tStores", tStores);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
		model.addAttribute("tEmployees", tEmployees);

		return "view/financial/cost/list";
	}
	@RequestMapping(value = "/add")
	public String add(Model model, HttpServletRequest request) {

//		model.addAttribute("att", "1000");
		int storeId = 1;
		//员工
		List<TEmployee> employeeList = employeeService.query(storeId, 1, 100);

		model.addAttribute("employees", employeeList);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
		model.addAttribute("tEmployees", tEmployees);
		return "view/financial/cost/add";
	}
	@RequestMapping(value = "/ajax/add")
	public @ResponseBody
	String ajaxAdd(HttpServletRequest request, Model model,
				   @RequestBody String json) throws Exception {
		int storeId = 1;

		JSONObject jsonOb = JSON.parseObject(json);
		TExpense tExpense = new TExpense();
		tExpense.setStoreId(storeId);
		tExpense.setType(jsonOb.getInteger("type"));
		tExpense.setAmount(StringUtil.getFen(jsonOb.getString("amount")));
		tExpense.setRemark(jsonOb.getString("remark"));
		tExpense.setHandler(jsonOb.getInteger("handler"));
		tExpense.setCreatedTime(new Date());
		tExpense.setUpdatedTime(new Date());

		tExpense.setBusinessDate(CunUtil.parseDate(jsonOb.getString("businessDate")));
		expenseService.insert(tExpense);

		int id = tExpense.getId();

		// 组装商品序列阿红，商品关键字+日期+记录id
		String date = DateUtil.date2String(new Date(), "yyyyMMdd");
		String serailNumber = "KH" + date + id;

		// 更新商品记录对应的商品序列号
		tExpense = new TExpense();
		tExpense.setSerialNumber(serailNumber);
		expenseService.update(id, tExpense);
//		JSONObject jsonRet = new JSONObject();
//		jsonRet.put("Status", true);
		return "true";
	}
	@RequestMapping(value = "/update")
	public String update(Model model, HttpServletRequest request,
					  @RequestParam(required = true) int id) {
		TEmployee tEmployee = (TEmployee) WebMvcHelper.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();

		TExpense tExpense = expenseService.get(id);
		model.addAttribute("tExpense", tExpense);
		//员工
		List<TEmployee> employeeList = employeeService.query(storeId, 1, 100);
		model.addAttribute("employees", employeeList);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
		model.addAttribute("tEmployees", tEmployees);
		return "view/financial/cost/update";
	}
	@RequestMapping(value = "/ajax/update")
	public @ResponseBody
	String ajaxUpdate(HttpServletRequest request, Model model,
				   @RequestBody String json) throws Exception {
		TEmployee tEmployee = (TEmployee) WebMvcHelper.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();

		JSONObject jsonOb = JSON.parseObject(json);
		int id = jsonOb.getInteger("id");

		TExpense tExpense = new TExpense();
		tExpense.setStoreId(storeId);
		tExpense.setType(jsonOb.getInteger("type"));
		tExpense.setAmount(StringUtil.getFen(jsonOb.getString("amount")));
		tExpense.setRemark(jsonOb.getString("remark"));
		tExpense.setHandler(jsonOb.getInteger("handler"));
		tExpense.setCreatedTime(new Date());
		tExpense.setUpdatedTime(new Date());

		tExpense.setBusinessDate(CunUtil.parseDate(jsonOb.getString("businessDate")));
		expenseService.update(id, tExpense);

//		JSONObject jsonRet = new JSONObject();
//		jsonRet.put("Status", true);
		return "1";
	}
}