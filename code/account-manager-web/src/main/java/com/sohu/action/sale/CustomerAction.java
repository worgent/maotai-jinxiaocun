package com.sohu.action.sale;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sohu.action.CunUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sohu.mrd.domain.beans.TCustomer;
import com.sohu.mrd.domain.beans.TCustomerAssociation;
import com.sohu.mrd.domain.beans.TOrder;
import com.sohu.mrd.domain.beans.TOrderExample;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.ExcelUtil;
import com.sohu.service.jinxiaocun.CustomerService;
import com.sohu.service.jinxiaocun.OrderService;

@Controller
@RequestMapping(value = "/sale/customer")
public class CustomerAction {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ExcelUtil excelUtil;

	@RequestMapping(value = "/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView list(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		String serialNumber = CunUtil.parseRequestString(request,
				"serialNumber");
		String name = CunUtil.parseRequestString(request, "name");
		String contactPhone = CunUtil.parseRequestString(request,
				"contactPhone");
		String officePhone = CunUtil.parseRequestString(request, "officePhone");
		String contact = CunUtil.parseRequestString(request, "contact");
		Integer storeId = CunUtil.parseRequestInt(request, "storeId");

		Integer pageNumber = 1;
		Integer pageSize = Integer.MAX_VALUE;
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		List<TCustomerAssociation> tCustomerAssociations = customerService
				.query(serialNumber, name, contactPhone, officePhone, contact,
						storeId, pageNumber, pageSize);

		// model.addAttribute("customer",
		// JSONObject.toJSONString(tCustomerAssociations));
		// model.addAttribute("customer", tCustomerAssociations);

		modelMap.put("serialNumber", serialNumber);
		modelMap.put("name", name);
		modelMap.put("contactPhone", contactPhone);
		modelMap.put("officePhone", officePhone);
		modelMap.put("contact", contact);
		modelMap.put("storeId", storeId);

		// modelAndView.addObject("customer", tCustomerAssociations);
		modelMap.put("customer", tCustomerAssociations);
		System.out.println("customer:"
				+ JSONObject.toJSONString(tCustomerAssociations));
		// 欠款

		modelAndView.setViewName("view/sale/customer/list");
		return modelAndView;
	}

	@RequestMapping(value = "/listExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void listExcel(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		String serialNumber = request.getParameter("serialNumber");
		String name = request.getParameter("name");
		String contactPhone = request.getParameter("contactPhone");
		String officePhone = request.getParameter("officePhone");
		String contact = request.getParameter("contact");
		String storeIdStr = request.getParameter("storeId");
		Integer storeId = StringUtils.isNotBlank(storeIdStr) ? Integer
				.parseInt(storeIdStr) : 0;
		Integer pageNumber = 1;
		Integer pageSize = Integer.MAX_VALUE;
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		List<TCustomerAssociation> tCustomerAssociations = customerService
				.query(serialNumber, name, contactPhone, officePhone, contact,
						storeId, pageNumber, pageSize);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("customer", tCustomerAssociations);
			excelUtil
					.exportExcel(request, response,
							DateUtil.date2String(new Date(), "yyyy-MM-dd")
									+ "客户列表.xls", "customer.xls", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 欠款

	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView add(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view/sale/customer/add");
		return modelAndView;
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView detail(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("id");
		String serialNumber = request.getParameter("serialNumber");
		if (StringUtils.isNotBlank(id)) {
			TCustomer tCustomer = customerService.get(Integer.parseInt(id));
			TOrderExample tOrderExample = new TOrderExample();
			tOrderExample.createCriteria().andCustomerIdEqualTo(
					Integer.parseInt(id));
			if (StringUtils.isNotBlank(serialNumber)) {
				tOrderExample.getOredCriteria().get(0)
						.andSerialNumberLike("%" + serialNumber + "%");
			}
			modelAndView.addObject("customer", tCustomer);
			modelAndView.addObject("orderCustomerList",
					orderService.query(tOrderExample));
		}
		modelMap.put("id", id);
		modelMap.put("serialNumber", serialNumber);
		modelAndView.setViewName("view/sale/customer/detail");
		return modelAndView;
	}

	@RequestMapping(value = "/detailExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void detailExcel(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String serialNumber = request.getParameter("serialNumber");
		if (StringUtils.isNotBlank(id)) {

			TOrderExample tOrderExample = new TOrderExample();
			tOrderExample.createCriteria().andCustomerIdEqualTo(
					Integer.parseInt(id));
			if (StringUtils.isNotBlank(serialNumber)) {
				tOrderExample.getOredCriteria().get(0)
						.andSerialNumberLike("%" + serialNumber + "%");
			}
			List<TOrder> orderCustomerList = orderService.query(tOrderExample);
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				map.put("orderCustomer", orderCustomerList);
				excelUtil.exportExcel(request, response,
						DateUtil.date2String(new Date(), "yyyy-MM-dd")
								+ "订单客户详情.xls", "orderCustomer.xls", map);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
