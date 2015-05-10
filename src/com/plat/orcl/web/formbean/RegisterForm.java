package com.plat.orcl.web.formbean;

import java.util.HashMap;
import java.util.Map;

public class RegisterForm {
	private String pid;
	private String pname;
	private String password;
	private String password2;
	private String email;
	private String pclass;
	
	private Map errors = new HashMap();

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPclass() {
		return pclass;
	}

	public void setPclass(String pclass) {
		this.pclass = pclass;
	}

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}
	
	public boolean check(){
		//学号不为空
		//姓名不为空
		//密码必须和第一次一样
		//email格式校验
		//班级不为空
		boolean isOk = true ; 
		
		if(this.pid == null|| this.pid.trim().equals("")) {
			isOk = false;
			errors.put("pid", "学号不能为空！");
		}else{
			if(this.pid.matches("^([\u4E00-\u9FA5]+)$")){
				{
					isOk = false;
					errors.put("pid", "学号不为汉字");
				}
			}
		}
		
		if(this.pname == null|| this.pname.trim().equals("")) {
			isOk = false;
			errors.put("pname", "姓名不能为空！");
		}
		
		if(this.password == null|| this.password.trim().equals("")) {
			isOk = false;
			errors.put("password", "密码不能为空！");
		}
		
		if(this.password2 == null|| this.password2.trim().equals("")) {
			isOk = false;
			errors.put("password2", "确认密码不能为空！");
		}else{
			if(!this.password2.equals(this.password)) 
			{
				isOk = false;
				errors.put("password2", "两次输入的密码不相同");
			}
		}
		
		if(this.email == null|| this.email.trim().equals("")) {
			isOk = false;
			errors.put("email", "邮箱不能为空！");
		}else{
			if(!this.email.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")){
				isOk = false;
				errors.put("email", "邮箱格式不匹配");
			}
		}
		
		if(this.pclass == null|| this.pclass.trim().equals("")) {
			isOk = false;
			errors.put("pclass", "班级不能为空！");
		}
		
		
		
		return isOk;
		
	}
	
}
