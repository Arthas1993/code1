import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService {
	@Autowired
UserService us;
	
	public void TestUserService() {
		us.hello();
	}
	
	public void TestLogin() {
		List<TPermission> queryByUid = us.queryByUid(3084);
		for (TPermission per : queryByUid) {
			System.out.println(per.getPname());
		}
		System.out.println("------------整理后");
		List<TPermission> menu = us.getMenu(queryByUid);
		for (TPermission per2 : menu) {
			System.out.println(per2.getPname());
			for (TPermission per3 : per2.getChildren()) {
				System.out.println("   "+per3.getPname());
			}
		}
	}
	
	public void TestQuery() {
		QueryUser query=new QueryUser();
		//query.setLoginname("test1");
		query.setSex("男");
		System.out.println(us.queryByCon(query).size());
	}
	
	public void TestDelete() {
		int [] ids= {3003,3004};
		us.deleteByIds(ids);
	}
	
	public void TestUpdate() {
		TUser user =new TUser();
		user.setId(3084);
		user.setSex("女");
		user.setUpdator(3001);
		us.update(user);
	}
	@Test
	public void TestInsert() {
		TUser user =new TUser();
		TDep dept=new TDep();
		dept.setId(3);
		user.setLoginname("insert222");
		user.setPassword("asd222");
		user.setCreator(3084);
		user.setSex("男");
		user.setPic("aaaa");
		user.setEmail("aaa");
		user.setDept(dept);
		user.setIsenabled(-1);
		user.setRealname("小王");
		int insertUser = us.insertUser(user);
		System.out.println(insertUser);
	}
	
	public void TestDeleteById() {
		us.deleteById(4000);
	}
	
	public void TestByPage() {
		QueryUser query=new QueryUser();
		query.setSex("男");
		List<TUser> query1 = us.queryUserByPage(1, query);
		for (TUser user : query1) {
			System.out.println(user.getId()+"    "+user.getLoginname());
		}
		System.out.println("一共"+us.queryPageCount(query)+"页");
	}
	
}
