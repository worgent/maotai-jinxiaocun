package com.sohu.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sohu.mrd.domain.beans.TCommodity;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.StringUtil;
import com.sohu.service.jinxiaocun.CommodityService;
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
@RequestMapping(value = "/commodity")
public class CommodityController {

	private final static Logger log = Logger
			.getLogger(CommodityController.class);

	@Autowired
	private CommodityService commodityService;

	@ResponseBody
	@RequestMapping(value = "/ajax/delete")
	public String ajaxDelete(HttpServletRequest request, Model model,
			@RequestParam(required = true) int id) throws Exception {
		// 根据登录人的session获得store_id
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		int result = commodityService.delete(id);
		// 欠款
		return "" + result;
	}



	@ResponseBody
	@RequestMapping(value = "/ajax/get")
	public String ajaxGet(HttpServletRequest request,
						  HttpServletResponse response,
						  Model model,
							  @RequestParam(required = true) int id) throws Exception {
		// 根据登录人的session获得store_id
		// 显示客户列表，另需展示客户欠款信息，门店信息;
//		request.setCharacterEncoding("utf-8");
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		TCommodity tCommodity = commodityService.get(id);
		
		System.out.println(tCommodity.getName());
		
		String ret = JSON.toJSONString(tCommodity);
		
		// @ResponseBody 如果配置文件中有<mvc:annotation-driven /> 返回时，则会调用StringHttpMessageConverter来解析，该类默认编码为ISO-8859-1
		ret = new String(ret.getBytes("UTF-8"),"ISO-8859-1");
		
		System.out.println(ret);
//		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		return ret;
	}

	@RequestMapping(value = "/update")
	public int update(HttpServletRequest request, Model model,
			@RequestParam(required = true) int id,
			@RequestParam(required = true) TCommodity tCommodity)
			throws Exception {
		// 根据登录人的session获得store_id
		int userStoreId = 1;
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		commodityService.update(id, tCommodity);
		// 欠款
		return 0;
	}

	@ResponseBody
	@RequestMapping(value = "/insert")
	public String insert(TCommodity tCommodity, HttpServletRequest request) throws Exception {
		// 根据登录人的session获得store_id
		// 显示客户列表，另需展示客户欠款信息，门店信息;
		
		String retailPriceYuan = request.getParameter("retailPriceYuan");
		String purchasePriceYuan = request.getParameter("purchasePriceYuan");
		
		// 销售价格
		tCommodity.setRetailPrice(StringUtil.getFen(retailPriceYuan));
		// 进货价格
		tCommodity.setPurchasePrice(StringUtil.getFen(purchasePriceYuan));

		// 插入客户信息
		int result = commodityService.insert(tCommodity);
		int id = tCommodity.getId();

		// 组装商品序列阿红，商品关键字+日期+记录id
		String date = DateUtil.date2String(new Date(), "yyyyMMdd");
		String serailNumber = "SP" + date + id;

		// 更新商品记录对应的商品序列号
		tCommodity = new TCommodity();
		tCommodity.setSerailNumber(serailNumber);
		commodityService.update(id, tCommodity);
		// 欠款
		// response.getWriter().print(result);
		// response.getWriter().print(result);
		return "" + result;
	}

}