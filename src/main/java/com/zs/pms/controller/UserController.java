package com.zs.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
@Service
public class UserController {
	@Autowired
    UserService us;
	@Autowired
	DepService ds;
	@RequestMapping("/user/list.do")
	public String list(String page,QueryUser query,ModelMap model) {
		if(page==null) {
			page="1";
		}
		
		//返回分页数据
		model.addAttribute("LIST", us.queryUserByPage(Integer.parseInt(page), query));
		//返回当前页数
		model.addAttribute("PAGE", page);
		//返回总页数
		model.addAttribute("PAGECONT", us.queryPageCount(query));
		//条件
		model.addAttribute("QUERY", query);
		return "user/list";
	}
	@RequestMapping("/user/toadd.do")
	public String toadd(ModelMap model) {
		//返回一级部门
		model.addAttribute("DLIST", ds.queryByPid(0));
		return "user/add";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
