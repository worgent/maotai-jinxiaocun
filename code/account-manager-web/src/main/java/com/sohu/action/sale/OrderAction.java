//package com.sohu.action.sale;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping(value = "/sale/order")
//public class OrderAction {
//
//	@RequestMapping(value = "/list", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public ModelAndView list(ModelMap modelMap, HttpServletRequest request,
//			HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("view/sale/order/list");
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/add", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public ModelAndView add(ModelMap modelMap, HttpServletRequest request,
//			HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("view/sale/order/add");
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/detail", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public ModelAndView detail(ModelMap modelMap, HttpServletRequest request,
//			HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("view/sale/order/detail");
//		return modelAndView;
//	}
//
//}
