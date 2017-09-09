package com.sohu.action;

import com.sohu.mrd.domain.beans.TStore;
import com.sohu.mrd.domain.util.DateUtil;
import com.sohu.service.jinxiaocun.StoreService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * 首页action
 */

@Controller
@RequestMapping(value = "/store")
public class StoreController {

	private final static Logger log = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	@ResponseBody
	@RequestMapping(value = "/ajax/add")
	public String add(TStore tStore) throws Exception {
//		storeService.insert(tStore);
		
		// 插入客户信息
		int result = storeService.insert(tStore);
		int id = tStore.getId();

		// 组装商品序列阿红，商品关键字+日期+记录id
		String date = DateUtil.date2String(new Date(), "yyyyMMdd");
		String serailNumber = "ST" + date + id;

		// 更新商品记录对应的商品序列号
		tStore = new TStore();
		tStore.setSerailNumber(serailNumber);
		storeService.update(id, tStore);

		return "" + result;
	}

	@ResponseBody
	@RequestMapping(value = "/ajax/delete")
	public String ajaxDelete(HttpServletRequest request,
			Model model, @RequestParam(required = true) int id)
			throws Exception {
		int result = storeService.delete(id);

		return "" + result;
	}

	@RequestMapping(value = "/ajax/update")
	public @ResponseBody int ajaxUpdate(HttpServletRequest request,
			Model model, @RequestParam(required = true) int id,
			@RequestParam(required = true) TStore tStore) throws Exception {
		storeService.update(id, tStore);

		return 0;
	}

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,
			@RequestParam(required = true) int pageNumber,
			@RequestParam(required = true) int pageSize) throws Exception {
		// 根据登录人的session获得store_id
		int storeId = 0;
		List<TStore> tStores = storeService.query(pageNumber, pageSize);

		model.addAttribute("content",
				com.alibaba.fastjson.JSONObject.toJSONString(tStores));

		return "show";
	}

}