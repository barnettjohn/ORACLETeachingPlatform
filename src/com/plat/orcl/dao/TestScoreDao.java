package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.TestScore;

public interface TestScoreDao {

	void addTestScore(TestScore ts);

	void deleteTestScoreByString(String xid, String yid);

	List<TestScore> findAll();

	List<TestScore> findTestScoreByString(String xid, String yid);

	List<TestScore> findTestScoreAllByString(String xid, String yid);

}