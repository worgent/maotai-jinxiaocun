package com.sohu.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TReceipt;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.StringUtil;
import com.sohu.mrd.domain.util.WebMvcHelper;
import com.sohu.service.jinxiaocun.*;
import com.sohu.service.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/financial/receive")
public class ReceiptController {

	private final static Logger log = Logger.getLogger(ReceiptController.class);

	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;


	@RequestMapping(value = "/ajax/delete")
	public @ResponseBody
	String ajaxDelete(HttpServletRequest request, Model model,
				   @RequestParam(required = true) int id) throws Exception {
		receiptService.delete(id);

		return "1";
	}


	@RequestMapping(value = "/list")
	public String index(HttpServletRequest request, Model model) throws Exception {
		//根据登录人的session获得receipt_id
		TEmployee tEmployee = (TEmployee) WebMvcHelper.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();
		TStore tStore = storeService.get(storeId);
		List<TOrder> tOrders = orderService.query(storeId);
		List<TReceipt> tReceipts = receiptService.query(storeId);
		//收款总额
		int sumReceipt = receiptService.queryStoreSumReceipt(storeId);
		//欠款总额
		int sumOrderDebt = 0;
		for(TOrder tOrder : tOrders){
			sumOrderDebt += (tOrder.getFinalAmount()-tOrder.getActualPayment());
		}
		List<Map<String, Integer>> customerReceiptSums = receiptService.queryCustomerSumReceipts(storeId, 1, 100);
		List<Map<String, Integer>> customerOrderSums = orderService.queryCustomerSumOrders(storeId, 1, 100);

		List<TCustomer> tCustomers = customerService.query(storeId, 1, 100);
		JSONArray jsonArray = new JSONArray();
		int sumInitDebt = 0;
		for(TCustomer tCustomer : tCustomers){
			sumInitDebt += tCustomer.getInitDebt();
			JSONObject jsonOne = (JSONObject) JSON.toJSON(tCustomer);

			for(Map<String,Integer> customerSum : customerReceiptSums){
				if( customerSum.get("customer_id").equals(tCustomer.getId()) ){
					jsonOne.put("sum_receipt_amount", customerSum.get("sum_amount"));
					customerReceiptSums.remove(customerSum);
					break;
				}
			}
			for(Map<String,Integer> customerSum : customerOrderSums){
				if( customerSum.get("customer_id").equals(tCustomer.getId()) ){
					jsonOne.put("sum_order_debt", customerSum.get("sum_debt"));
					customerOrderSums.remove(customerSum);
					break;
				}
			}
			if( jsonOne.containsKey("sum_order_debt") == false ) {
				jsonOne.put("sum_order_debt", 0);
			}
			if( jsonOne.containsKey("sum_receipt_amount") == false ) {
				jsonOne.put("sum_receipt_amount", 0);
			}
			jsonOne.put("sum_debt",
					tCustomer.getInitDebt()+jsonOne.getInteger("sum_order_debt")-jsonOne.getInteger("sum_receipt_amount"));

			jsonArray.add(jsonOne);
		}
		model.addAttribute("tStore", tStore);
		model.addAttribute("sumStroreDebt", sumInitDebt+sumOrderDebt-sumReceipt);
		model.addAttribute("sumInitDebt", sumInitDebt);
		model.addAttribute("sumReceipt", sumReceipt);
		model.addAttribute("sumOrderDebt", sumOrderDebt);
		model.addAttribute("tCustomers", jsonArray);

//		List<TReceipt> tReceipts = receiptService.query(pageNumber, pageSize);

//		model.addAttribute("content", com.alibaba.fastjson.JSONObject.toJSONString(tReceipts));
		List<TStore> tStores = storeService.query(1, 100);
		model.addAttribute("tStores", tStores);
		return "view/financial/receive/list";
	}

	@RequestMapping(value = "/add")
	public String add(Model model, HttpServletRequest request) {

		TEmployee tEmployee = (TEmployee) WebMvcHelper.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();
//
//		List<TStore> tStores = storeService.query(1, 100);
//		model.addAttribute("tStores", tStores);
		List<TCustomer> tCustomers = customerService.query(storeId, 1, 100);
		model.addAttribute("tCustomers", tCustomers);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
		model.addAttribute("tEmployees", tEmployees);
		return "view/financial/receive/add";
	}
	@RequestMapping(value = "/ajax/add")
	public @ResponseBody String ajaxAdd(HttpServletRequest request, Model model,
											@RequestBody String json) throws Exception {
		int storeId = 1;


		JSONObject jsonOb = JSON.parseObject(json);

//		JSONObject jsonOb = JSON.parseObject(json);
		TReceipt tReceipt = new TReceipt();
		tReceipt.setStoreId(storeId);
		tReceipt.setCustomerId(jsonOb.getInteger("customerId"));
		tReceipt.setStoreId(storeId);
		tReceipt.setAmount(StringUtil.getFen(jsonOb.getString("amount")));
		tReceipt.setRemark(jsonOb.getString("remark"));
		tReceipt.setHandler(jsonOb.getInteger("handler"));
		tReceipt.setBusinessDate(CunUtil.parseDate(jsonOb.getString("businessDate")));

		tReceipt.setCreatedTime(new Date());
		tReceipt.setUpdatedTime(new Date());

//		tReceipt.setBusinessDate(CunUtil.parseDate(jsonOb.getString("businessDate")));
		receiptService.insert(tReceipt);

		int id = tReceipt.getId();

		// 组装商品序列阿红，商品关键字+日期+记录id
		String date = DateUtil.date2String(new Date(), "yyyyMMdd");
		String serailNumber = "RE" + date + id;

		// 更新商品记录对应的商品序列号
		tReceipt = new TReceipt();
		tReceipt.setSerialNumber(serailNumber);
		receiptService.update(id, tReceipt);
		return "1";
	}

	@RequestMapping(value = "/update")
	public String update(Model model, HttpServletRequest request,
						 @RequestParam(required = true) int id) {

		TEmployee tEmployee = (TEmployee) WebMvcHelper.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();
		TReceipt tReceipt = receiptService.get(id);
		model.addAttribute("tReceipt", tReceipt);
//		List<TStore> tStores = storeService.query(1, 100);
//		model.addAttribute("tStores", tStores);
		List<TCustomer> tCustomers = customerService.query(storeId, 1, 100);
		model.addAttribute("tCustomers", tCustomers);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
		model.addAttribute("tEmployees", tEmployees);
		return "view/financial/receive/update";
	}
	@RequestMapping(value = "/ajax/update")
	public @ResponseBody String ajaxUpdate(HttpServletRequest request, Model model,
											@RequestBody String json) throws Exception {
		int storeId = 1;


		JSONObject jsonOb = JSON.parseObject(json);
		int id = jsonOb.getInteger("id");


//		JSONObject jsonOb = JSON.parseObject(json);
		TReceipt tReceipt = new TReceipt();
		tReceipt.setStoreId(storeId);
		tReceipt.setCustomerId(jsonOb.getInteger("customerId"));
		tReceipt.setStoreId(storeId);
		tReceipt.setAmount(StringUtil.getFen(jsonOb.getString("amount")));
		tReceipt.setRemark(jsonOb.getString("remark"));
		tReceipt.setHandler(jsonOb.getInteger("handler"));
		tReceipt.setBusinessDate(CunUtil.parseDate(jsonOb.getString("businessDate")));

		tReceipt.setCreatedTime(new Date());
		tReceipt.setUpdatedTime(new Date());

//		tReceipt.setBusinessDate(CunUtil.parseDate(jsonOb.getString("businessDate")));
		receiptService.update(id, tReceipt);

		return "1";
	}

	@RequestMapping(value = "/history")
	public String history(HttpServletRequest request, Model model
	) throws Exception {
		//根据登录人的session获得expense_id
		TEmployee tEmployee = (TEmployee) WebMvcHelper.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();

//		String serailNumber = CunUtil.parseRequestString(request, "serailNumber");
//		int handlerId = CunUtil.parseRequestInt(request, "handlerId");
//		int listerId = CunUtil.parseRequestInt(request, "listerId");
//		int storeId = CunUtil.parseRequestInt(request, "storeId");
//		Date businessDateStart = CunUtil.parseRequestDate(request, "businessDateStart");
//		Date businessDateEnd = CunUtil.parseRequestDate(request, "businessDateEnd");
//		Date createdDateStart = CunUtil.parseRequestDate(request, "createdDateStart");
//		Date createdDateEnd = CunUtil.parseRequestDate(request, "createdDateEnd");
//		int pageNumber=1;
//		int pageSize = 1000;
		List<TReceiptAssociation> tReceipts = receiptService.queryAssociation(storeId);
//				serailNumber,  type,
//				handlerId,  listerId,  storeId,
//				businessDateStart,  businessDateEnd,
//				createdDateStart,  createdDateEnd,
//				pageNumber, pageSize);

				model.addAttribute("tReceipts", tReceipts);
//		List<TStore> tStores = storeService.query(1, 100);
//		model.addAttribute("tStores", tStores);
//		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
//		model.addAttribute("tEmployees", tEmployees);

		return "view/financial/receive/history";
	}
}