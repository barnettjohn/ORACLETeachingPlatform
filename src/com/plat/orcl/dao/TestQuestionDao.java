package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.TestQuestion;

public interface TestQuestionDao {

	void addTestQuestion(TestQuestion tq);

	void deleteTestQuestion(String testid, String qid);

	void deleteTestQuestionByString(String xid, String yid);

	List<TestQuestion> findAll();

	List<TestQuestion> findTestQuestionByString(String xid, String yid);

	List<TestQuestion> findTestQuestionAllByString(String xid, String yid);

}