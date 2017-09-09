//package com.sohu.action.financial;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping(value = "/financial/cost")
//public class CostAction {
//
//	@RequestMapping(value = "/list", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public ModelAndView list(ModelMap modelMap, HttpServletRequest request,
//			HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("view/financial/cost/list");
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/add", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public ModelAndView add(Model model, HttpServletRequest request,
//			HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("view/financial/cost/add");
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/addt", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public String addt(Model model, HttpServletRequest request) {
//
//		return "view/financial/cost/add";
//	}
//
//}
