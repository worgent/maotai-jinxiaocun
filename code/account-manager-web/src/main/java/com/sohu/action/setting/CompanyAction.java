package com.sohu.action.setting;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.action.CommodityController;
import com.sohu.mrd.domain.beans.TStoreExample;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.mrd.domain.util.ExcelUtil;
import com.sohu.service.jinxiaocun.StoreService;

@Controller
@RequestMapping(value = "/setting/company")
public class CompanyAction {

	private final static Logger log = Logger.getLogger(CompanyAction.class);

	@Autowired
	private StoreService storeService;

	@Autowired
	private ExcelUtil excelUtil;

	@RequestMapping(value = "/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView list(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		String serailNumber = request.getParameter("serailNumber");
		ModelAndView modelAndView = new ModelAndView();
		TStoreExample tStoreExample = new TStoreExample();
		tStoreExample.createCriteria().andDelFlagEqualTo(0);
		if (StringUtils.isNotBlank(serailNumber)) {
			tStoreExample.getOredCriteria().get(0)
					.andSerailNumberLike("%" + serailNumber + "%");
		}
		modelAndView.addObject("storeList", storeService.query(tStoreExample));
		modelAndView.addObject("serailNumber", serailNumber);
		modelAndView.setViewName("view/setting/company/list");
		return modelAndView;
	}

	@RequestMapping(value = "/listExcel", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void listExcel(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		TStoreExample tStoreExample = new TStoreExample();
		tStoreExample.createCriteria().andDelFlagEqualTo(0);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("storeList", storeService.query(tStoreExample));
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
		modelAndView.setViewName("view/setting/company/add");
		return modelAndView;
	}

}
