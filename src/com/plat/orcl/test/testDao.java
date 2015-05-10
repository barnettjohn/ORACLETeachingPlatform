package com.plat.orcl.test;

import org.junit.Test;

import com.plat.orcl.dao.impl.CoursePlanDaoMysqlImpl;
import com.plat.orcl.dao.impl.PersonDaoMysqlImpl;
import com.plat.orcl.dao.impl.TimePlanDaoMysqlImpl;
import com.plat.orcl.domain.CoursePlan;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.TimePlan;

public class testDao {
	
	@Test
	public void timeDaoTest() {
		TimePlanDaoMysqlImpl tpdao = new TimePlanDaoMysqlImpl();
		TimePlan tp = new TimePlan();
		tp.setTid("11");
		tp.setTdetail("asdlkf");
		tp.setTsign("~");
		tp.setTweek("星期一");
		
		tpdao.addTimePlan(tp);
	}
	
	@Test
	public void timeDaoTest2() {
		CoursePlanDaoMysqlImpl cpd = new CoursePlanDaoMysqlImpl();
		CoursePlan cp = new CoursePlan();
		cp.setCid("1");
		cp.setTid("11");
		cpd.addCoursePlan(cp);
		
	}
	
	@Test
	public void timeDaoTest3() {
		TimePlanDaoMysqlImpl tpd = new TimePlanDaoMysqlImpl();
		TimePlan tp = tpd.findTimePlanById("11");
		
		System.out.println(tp.getCoursePlans().get(0).getTp().getTid());
		
	}
	
	@Test
	public void timeDaoTest4() {
		PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
		Person p = pd.findPersonAllById("20111236");
		
		System.out.println(p.getSignIns().get(0).getTime().toLocaleString());
		
	}
	
	@Test
	public void testEmail(){
		System.out.println("aa@qq.com".matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"));
	}
	
	
}
