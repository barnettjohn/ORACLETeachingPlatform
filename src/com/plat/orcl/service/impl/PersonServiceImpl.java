package com.plat.orcl.service.impl;

import java.util.ArrayList;

import com.plat.orcl.dao.PersonDao;
import com.plat.orcl.dao.impl.PersonDaoMysqlImpl;
import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.PersonExistException;
import com.plat.orcl.exception.PersonNullException;
import com.plat.orcl.utils.ServiceUtil;

public class PersonServiceImpl {
	
	private PersonDao dao = new PersonDaoMysqlImpl();
	
	//关于person的注册业务
	public void register(Person p) throws PersonExistException{
		
		Person find = dao.findPersonById(p.getPid());
		//判断用户是否存在
		if(find != null){
			throw new PersonExistException();
		}else{
			p.setPassword(ServiceUtil.md5(p.getPassword()));
			dao.addPerson(p);
		}
		
	}
	
	//关于person的登陆业务
	public Person login(String id ,String password) throws PersonNullException{
		
		password = ServiceUtil.md5(password);
		Person p = dao.findPersonAllByIdPassword(id, password);
		if(p == null){
			throw new PersonNullException();
		}
		return p;
	}
	//找学生
	public ArrayList<Person> findPerson(String xid,String yid){
		
		return (ArrayList<Person>) dao.findPersonByString(xid, yid);
		
	}
}
