package com.plat.orcl.service.impl;

import java.util.ArrayList;

import com.plat.orcl.dao.QuestionDao;
import com.plat.orcl.dao.TestDao;
import com.plat.orcl.dao.TestQuestionDao;
import com.plat.orcl.dao.TestScoreDao;
import com.plat.orcl.dao.impl.QuestionDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestQuestionDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestScoreDaoMysqlImpl;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.Test;
import com.plat.orcl.domain.TestQuestion;


public class TestServiceImpl {
	private QuestionDao qd= new QuestionDaoMysqlImpl();
	private TestDao td = new TestDaoMysqlImpl();
	private TestScoreDao tsd = new TestScoreDaoMysqlImpl();
	private TestQuestionDao tqd = new TestQuestionDaoMysqlImpl();
	
	public void deleteQuestion(String qid){
		qd.deleteQuestionByString("qid", qid);
	}
	
	public Question uploadQuestion(Question q){
		qd.addQuestion(q);
		return q;
	}
	
	public Question updateQuestion(Question q){
		qd.updateQuestion(q);
		return q;
	}
	
	public Question getOneQuestion(String qid){
		if(qd.findQuestionByString("qid", qid).size()==0){
			return null;
		}else{
			return qd.findQuestionByString("qid", qid).get(0);
		}
	}
	
	public ArrayList<Question> getAllQuestion(){
		return (ArrayList<Question>) qd.findAll();
	}
	
	public void saveTest(Test t,String[] qlist){
		td.addTest(t);
		for(String q:qlist){
			TestQuestion tq = new TestQuestion();
			tq.setTestid(t.getTestid());
			tq.setQid(q);
			tqd.addTestQuestion(tq);
		}
	}
	public ArrayList<Test> getAllTest(){
		
		return (ArrayList<Test>) td.findAll();
	}
	
}
