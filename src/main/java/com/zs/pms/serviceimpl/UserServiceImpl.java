package com.zs.pms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.util.Constants;
import com.zs.pms.vo.QueryUser;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
   UserDao dao;
	@Override
	public void hello() {
		// TODO Auto-generated method stub
		System.out.println("hello spring");
	}

	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		return dao.queryByUid(id);
	}

	@Override
	public List<TPermission> getMenu(List<TPermission> pers) {
		// TODO Auto-generated method stub
		List<TPermission> list=new ArrayList<>();
		for (TPermission per1 : pers) {
			if(per1.getLev()==1) {
				list.add(per1);
				for (TPermission per2 : pers) {
					if (per1.getId()==per2.getPid()) {
						per1.addchildren(per2);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		
		return dao.queryByCon(query);
	}

	@Override
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteByIds(ids);
	}

	@Override
	public void update(TUser user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser user) {
		// TODO Auto-generated method stub
		dao.insertUser(user);
		int a=1/0;
		dao.insertUser(user);
		return user.getId();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<TUser> queryUserByPage(int page, QueryUser query) {
		// TODO Auto-generated method stub
		int start=Constants.PAGECOUNT*(page-1)+1;
		int end=Constants.PAGECOUNT*page;
		query.setStart(start);
		query.setEnd(end);
		List<TUser> queryUserByPage = dao.queryUserByPage(query);
		return queryUserByPage;
	}

	@Override
	public int queryPageCount(QueryUser query) {
		// TODO Auto-generated method stub
		int count=dao.queryCount(query);
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		}else {
		return count/Constants.PAGECOUNT+1;
		}
	}

}
