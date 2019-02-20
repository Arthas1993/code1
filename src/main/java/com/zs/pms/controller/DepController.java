package com.zs.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.po.TDep;
import com.zs.pms.service.DepService;

@Controller
public class DepController {
@Autowired
DepService ds;
/**
 * ajax获得二级部门
 */
@RequestMapping("/user/getdeps.do")
/**
 * 方法返回的对象或对象的集合自动以json的形式写入response中合
 * @param pid
 * @return
 */
public List<TDep> getDeps(int pid){	
	return ds.queryByPid(pid);
}

}
