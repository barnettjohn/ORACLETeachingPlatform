package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Test;

public interface TestDao {

	void addTest(Test t);

	void deleteTestByString(String xid, String yid);

	List<Test> findAll();

	List<Test> findTestByString(String xid, String yid);

	List<Test> findTestAllByString(String xid, String yid);

}