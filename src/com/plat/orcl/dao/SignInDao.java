package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.SignIn;

public interface SignInDao {

	void addSignIn(SignIn si);

	void deleteSignIn(String siid);
	
	void deleteSignInByString(String xid, String yid);

	List<SignIn> findAll();
	
	SignIn findSignInBySiid(String siid);

	List<SignIn> findSignInByPid(String pid);

	List<SignIn> findSignInByTime(String time1, String time2);

	
}