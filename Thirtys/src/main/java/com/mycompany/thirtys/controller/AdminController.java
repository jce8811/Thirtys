package com.mycompany.thirtys.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.thirtys.service.AdminService;
import com.mycompany.thirtys.service.UserService;
import com.mycompany.thirtys.vo.PageMaker;
import com.mycompany.thirtys.vo.SearchCriteria;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private final AdminService adminService;
	
	@Inject
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void adminLogin() throws Exception {
		
		logger.info("admin_login GET");
		
	}
	@RequestMapping(value="/admin_Page", method = RequestMethod.GET)
	public void adminPage() throws Exception {
		logger.info("admin_Page GET");
	}
	
	@RequestMapping(value="/userList", method = RequestMethod.GET)
	public void userList(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		logger.info("userList GET");
		
		model.addAttribute("list", adminService.userList(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(scri);
		pageMaker.setTotlaCount(adminService.countUserList(scri));
		model.addAttribute("pageMaker", pageMaker);
	}
}
