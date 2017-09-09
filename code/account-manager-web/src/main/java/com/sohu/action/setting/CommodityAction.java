package com.sohu.action.setting;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.mrd.domain.beans.TCommodityExample;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.ExcelUtil;
import com.sohu.service.jinxiaocun.CommodityService;

@Controller
@RequestMapping(value = "/setting/commodity")
public class CommodityAction {

	private final static Logger log = Logger.getLogger(CommodityAction.class);

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private ExcelUtil excelUtil;

	@RequestMapping(value = "/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView list(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		TCommodityExample commodityExample = new TCommodityExample();
		commodityExample.createCriteria().andDelFlagEqualTo(0);
		modelAndView.addObject("commodityList",
				commodityService.query(commodityExample));

		modelAndView.setViewName("view/setting/commodity/list");
		return modelAndView;
	}

	@RequestMapping(value = "/listExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void listExecl(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		TCommodityExample commodityExample = new TCommodityExample();
		commodityExample.createCriteria().andDelFlagEqualTo(0);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("commodityList", commodityService.query(commodityExample));
			excelUtil
					.exportExcel(request, response,
							DateUtil.date2String(new Date(), "yyyy-MM-dd")
									+ "商品列表.xls", "commodity.xls", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView add(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view/setting/commodity/add");
		return modelAndView;
	}

}
