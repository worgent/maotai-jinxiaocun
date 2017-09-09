package com.sohu.action;

import com.sohu.mrd.domain.beans.TCommodity;
import com.sohu.mrd.domain.beans.TCommodityExample;
import com.sohu.mrd.domain.beans.TCustomer;
import com.sohu.mrd.domain.beans.TEmployee;
import com.sohu.mrd.domain.beans.TOrder;
import com.sohu.mrd.domain.beans.TOrderAssociation;
import com.sohu.mrd.domain.beans.TOrderCommodity;
import com.sohu.mrd.domain.beans.TOrderCommodityExample;
import com.sohu.mrd.domain.beans.TStore;
import com.sohu.service.jinxiaocun.CustomerService;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.WebMvcHelper;
import com.sohu.mrd.domain.util.common.DateFormatUtils;
import com.sohu.service.jinxiaocun.CommodityService;
import com.sohu.service.jinxiaocun.EmployeeService;
import com.sohu.service.jinxiaocun.OrderCommodityService;
import com.sohu.service.jinxiaocun.OrderService;
import com.sohu.service.jinxiaocun.StoreService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/sale/order")
public class OrderController {

	private final static Logger log = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private StoreService storeService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private OrderCommodityService orderCommodityService;

	@RequestMapping(value = "/ajax/delete")
	public @ResponseBody String ajaxDelete(HttpServletRequest request,
			Model model, @RequestParam(required = true) int id)
			throws Exception {
		// 根据登录人的session获得store_id
		int userStoreId = 1;
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		orderService.delete(id);
		// 欠款
		return "1";
	}

	@RequestMapping(value = "/update")
	public int update(HttpServletRequest request, Model model,
			@RequestParam(required = true) int id,
			@RequestParam(required = true) TOrder tOrder) throws Exception {
		// 根据登录人的session获得store_id
		int userStoreId = 1;
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		orderService.update(id, tOrder);
		// 欠款
		return 0;
	}

	@RequestMapping(value = "/add")
	public String insert(HttpServletRequest request, Model model)
			throws Exception {
		TEmployee employee = (TEmployee) WebMvcHelper
				.getContext(WebMvcHelper.SESSION_EMPLOYEE);
		Integer storeId = employee.getStoreId();
		List<TStore> tStores = storeService.query(1, Integer.MAX_VALUE);
		model.addAttribute("tStores", tStores);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1,
				Integer.MAX_VALUE);
		model.addAttribute("tEmployees", tEmployees);
		List<TCustomer> tCustomers = customerService.query(storeId, 1,
				Integer.MAX_VALUE);
		model.addAttribute("tCustomers", tCustomers);

		List<TCommodity> tCommoditys = commodityService
				.query(1,100);
		model.addAttribute("tCommoditys", tCommoditys);

		model.addAttribute("employee", employee);
		model.addAttribute("date",
				DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		// 欠款
		return "view/sale/order/add";
	}

	@ResponseBody
	@RequestMapping(value = "/addResult", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addResult(HttpServletRequest request, Model model)
			throws Exception {
		TEmployee employee = (TEmployee) WebMvcHelper
				.getContext(WebMvcHelper.SESSION_EMPLOYEE);
		Integer lister = employee.getId();
		String listerName = employee.getName();

		String data = request.getParameter("data");

		JSONObject jsonObject = JSONObject.fromObject(data);
		String businessDate = jsonObject.getString("businessDate");
		String customerId = jsonObject.getString("customerId");
		String customerName = jsonObject.getString("customerName");
		String handler = jsonObject.getString("handler");
		String handlerName = jsonObject.getString("handlerName");
		String storeId = jsonObject.getString("storeId");
		String storeName = jsonObject.getString("storeName");
		Integer discountRate = jsonObject.getInt("discountRate");

		Integer totalAmount = 0;
		Integer finalAmount = 0;
		Integer discountMount = 0;

		List<TOrderCommodity> tOrderCommoditys = new ArrayList<TOrderCommodity>();

		JSONArray jsonArray = jsonObject.getJSONArray("eData");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONArray array = jsonArray.getJSONArray(i);
			Integer commodityId = array.getInt(0);
			String commondityName = array.getString(1);
			String commoditySerialNumber = array.getString(2);
			String specification = array.getString(3);
			String unit = array.getString(4);
			Integer commodityNum = array.getInt(5);
			Integer commodityPrice = array.getInt(6);
			Integer price = array.getInt(7);
			String remark = array.getString(8);

			totalAmount += price;

			TOrderCommodity tOrderCommodity = new TOrderCommodity();
			tOrderCommodity.setCommodityId(commodityId);
			tOrderCommodity.setCommodityNum(commodityNum);
			tOrderCommodity.setCommodityPrice(commodityPrice);
			tOrderCommodity.setCommondityName(commondityName);
			tOrderCommodity.setRemark(remark);
			tOrderCommodity.setCommodityId(commodityId);

			tOrderCommoditys.add(tOrderCommodity);

		}

		discountMount  = totalAmount * (100 - discountRate);
		finalAmount = totalAmount * discountRate;
		
		TOrder tOrder = new TOrder();
		tOrder.setStoreId(Integer.parseInt(storeId));
		tOrder.setStoreName(storeName);
		tOrder.setCustomerId(Integer.parseInt(customerId));
		tOrder.setCustomerName(customerName);
		tOrder.setHandler(Integer.parseInt(handler));
		tOrder.setHandlerName(handlerName);
		tOrder.setBusinessDate(DateUtil.getDateFromString(businessDate));
		tOrder.setTotalAmount(totalAmount);
		tOrder.setFinalAmount(finalAmount);
		tOrder.setDiscountRate(discountRate);
		tOrder.setDiscountMount(discountMount);

		tOrder.setLister(lister);
		tOrder.setListerName(listerName);

		int result = orderService.insert(tOrder);

		int orderId = tOrder.getId();

		// 组装商品序列阿红，商品关键字+日期+记录id
		String date = DateUtil.date2String(new Date(), "yyyyMMdd");
		String serailNumber = "OR" + date + orderId;

		// 更新商品记录对应的商品序列号
		tOrder = new TOrder();
		tOrder.setSerialNumber(serailNumber);
		orderService.update(orderId, tOrder);

		for (TOrderCommodity tOrderCommodity : tOrderCommoditys) {
			tOrderCommodity.setOrderId(orderId);

			// 在此处插入OrderCommodity的数据
			orderCommodityService.insert(tOrderCommodity);

		}

		return "" + result;

	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model)
			throws Exception {
		// 根据登录人的session获得store_id
		int userStoreId = 1;
		// 显示订单列表，另需展示门店信息，经手人，制表人，
		// 欠款
		String serailNumber = CunUtil.parseRequestString(request,
				"serailNumber");
		int customerId = CunUtil.parseRequestInt(request, "customerId");
		int handlerId = CunUtil.parseRequestInt(request, "handlerId");
		int listerId = CunUtil.parseRequestInt(request, "listerId");
		int storeId = CunUtil.parseRequestInt(request, "storeId");
		Date businessDateStart = CunUtil.parseRequestDate(request,
				"businessDateStart");
		Date businessDateEnd = CunUtil.parseRequestDate(request,
				"businessDateEnd");
		Date createdDateStart = CunUtil.parseRequestDate(request,
				"createdDateStart");
		Date createdDateEnd = CunUtil.parseRequestDate(request,
				"createdDateEnd");
		int pageNumber = 1;
		int pageSize = 1000;

		List<TOrderAssociation> tOrders = orderService.queryAssociations(
				serailNumber, customerId, handlerId, listerId, storeId,
				businessDateStart, businessDateEnd, createdDateStart,
				createdDateEnd, pageNumber, pageSize);

		model.addAttribute("tOrders", tOrders);
		List<TStore> tStores = storeService.query(1, 100);
		model.addAttribute("tStores", tStores);
		List<TEmployee> tEmployees = employeeService.query(storeId, 1, 100);
		model.addAttribute("tEmployees", tEmployees);
		List<TCustomer> tCustomers = customerService.query(storeId, 1, 100);
		model.addAttribute("tCustomers", tCustomers);

		return "view/sale/order/list";
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView detail(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		int id = CunUtil.parseRequestInt(request,"id");
		modelAndView.addObject("order", orderService.get(id));
		TOrderCommodityExample tOrderCommodityExample = new TOrderCommodityExample();
		tOrderCommodityExample.createCriteria().andCommodityIdEqualTo(id);
		modelAndView.addObject("orderCommoditys", orderCommodityService.query(tOrderCommodityExample));
		modelAndView.setViewName("view/sale/order/detail");
		return modelAndView;
	}

}