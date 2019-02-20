package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.util.DateUtil;
import com.zs.pms.util.MD5;
import com.zs.pms.vo.QueryLogin;
import com.zs.pms.vo.QueryUser;

@Controller//这是一个控制器
@Service
public class LoginController {
	@Autowired
	UserService us;
//去登录页面
	@RequestMapping("/tologin.do")
	public String tologin() {
		return "login";
	}
	@RequestMapping("/login.do")
	public String login(QueryLogin login,ModelMap model,HttpSession session) {
		//1,验证验证码
		String ocode=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//生成的验证码和输入的相等
		if(!ocode.equals(login.getChkcode())) {
			model.addAttribute("MSG", "验证码输入有误，请重新输入");
			return "login";
		}
		//2.验证用户名和密码
		//装载数据
		QueryUser query=new QueryUser();
		query.setLoginname(login.getUsername());
		//MD5加密
		MD5 md5=new MD5();
		query.setPassword(md5.getMD5ofStr(login.getPassword()));//密码
		query.setIsenabled(1);
		List<TUser> users = us.queryByCon(query);
		//登录失败
		if(users==null||users.size()!=1) {
	    model.addAttribute("MSG", "用户名密码匹配有误或者账号被锁定");	
	    return "login";
		}
		//登录成功装session
		session.setAttribute("CUSER", users.get(0));
		return "main";
	}
	@RequestMapping("/top.do")
	public String top(ModelMap model) {
		DateUtil date=new DateUtil();		
		model.addAttribute("TODAY",date.getStrDate());
		return "top";
	}
	@RequestMapping("/right.do")
	public String main() {
		return "right";
	}
	@RequestMapping("/left.do")
	//去左侧页面
	public String menu(HttpSession session,ModelMap model) {
		//获得当前登录用户
		TUser cu= (TUser) session.getAttribute("CUSER");
		//获得该用户的权限列表(先通过登录用户的id获得该用户拥有的权限再通过用户权限查出用户的权限列表（一级栏目加子栏目）)
		List<TPermission> pers = us.queryByUid(cu.getId());
		List<TPermission> menu = us.getMenu(pers);
		//返回菜单
		model.addAttribute("MENU", menu);
		return "left";
	}
}
