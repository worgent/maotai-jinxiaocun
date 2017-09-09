package com.sohu.action;

import com.alibaba.fastjson.JSONObject;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.WebMvcHelper;
import com.sohu.service.jinxiaocun.CustomerService;
import com.sohu.service.jinxiaocun.OrderService;
import com.sohu.service.jinxiaocun.ReceiptService;
import com.sohu.service.jinxiaocun.StoreService;
import com.sohu.service.login.LoginService;

import org.apache.commons.lang3.StringUtils;
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

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	private final static Logger log = Logger
			.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,
			@RequestParam(required = false) String serialNumber,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String contactPhone,
			@RequestParam(required = false) String officePhone,
			@RequestParam(required = false) String contact,
			@RequestParam(required = false) int storeId,
			@RequestParam(required = false) int pageNumber,
			@RequestBody(required = false) int pageSize) throws Exception {
		// 根据登录人的session获得store_id
		int userStoreId = 1;

		pageNumber = 1;
		pageSize = Integer.MAX_VALUE;

		// 显示客户列表，另需展示客户欠款信息，门店信息;
		List<TCustomerAssociation> tCustomerAssociations = customerService
				.query(serialNumber, name, contactPhone, officePhone, contact,
						storeId, pageNumber, pageSize);

		// model.addAttribute("customer",
		// JSONObject.toJSONString(tCustomerAssociations));
		model.addAttribute("customer", tCustomerAssociations);
		System.out.println("customer:"
				+ JSONObject.toJSONString(tCustomerAssociations));
		// 欠款
		return "sale/customer/list";
	}

	@ResponseBody
	@RequestMapping(value = "/ajax/delete")
	public String ajaxDelete(@RequestParam(required = true) int id)
			throws Exception {
		int result = customerService.delete(id);
		return "" + result;
	}

	@RequestMapping(value = "/update")
	public int update(HttpServletRequest request, Model model,
			@RequestParam(required = true) int id,
			@RequestParam(required = true) TCustomer tCustomer)
			throws Exception {
		// 根据登录人的session获得store_id
		int userStoreId = 1;
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		customerService.update(id, tCustomer);
		// 欠款
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/insert")
	public String insert(TCustomer tCustomer) throws Exception {

		TEmployee tEmployee = (TEmployee) WebMvcHelper
				.getContext(WebMvcHelper.SESSION_EMPLOYEE);

		Integer storeId = tEmployee.getStoreId();
		if (storeId == null) {
			tCustomer.setStoreId(0);
		}else
			tCustomer.setStoreId(storeId);

		tCustomer.setStoreId(storeId);

		int result = customerService.insert(tCustomer);
		int id = tCustomer.getId();

		// 组装商品序列阿红，商品关键字+日期+记录id
		String date = DateUtil.date2String(new Date(), "yyyyMMdd");
		String serailNumber = "KH" + date + id;

		// 更新商品记录对应的商品序列号
		tCustomer = new TCustomer();
		tCustomer.setSerailNumber(serailNumber);
		customerService.update(id, tCustomer);

		return "" + result;
	}

	@RequestMapping(value = "/detail")
	public String detail(Model model, HttpServletRequest request,
							   HttpServletResponse response,
							   @RequestParam(required = true) int id,
							   @RequestParam(required = true) int type) {
//		String serialNumber = request.getParameter("serialNumber");
		TCustomer tCustomer = customerService.get(id);
		TStore tStore =storeService .get(tCustomer.getStoreId());

		List<TOrder> orderCustomerList = orderService.queryCustomerOrders(id);
		List<TReceipt> receiptCustomerList = receiptService.queryCustomerReceipts(id);
		int sumOrderDebt = 0;
		for(TOrder tOrder : orderCustomerList){
			sumOrderDebt += (tOrder.getFinalAmount() - tOrder.getActualPayment());
		}
		int sumReceipt = 0;
		for(TReceipt tReceipt : receiptCustomerList){
			sumReceipt += (tReceipt.getAmount());
		}

		int allDebt = tCustomer.getInitDebt() + sumOrderDebt - sumReceipt;

		model.addAttribute("customer", tCustomer);
		model.addAttribute("store", tStore);
		model.addAttribute("allDebt", allDebt);
		model.addAttribute("sumOrderDebt", sumOrderDebt);
		model.addAttribute("sumReceipt", sumReceipt);
		model.addAttribute("orderCustomerList",orderCustomerList);
		model.addAttribute("receiptCustomerList",
				receiptCustomerList);
		model.addAttribute("id", id);
//		model.addAttribute("serialNumber", serialNumber);
		if(type == 1)
			return "view/sale/customer/detail";
		else
			return "view/sale/customer/detail_order";

	}
}