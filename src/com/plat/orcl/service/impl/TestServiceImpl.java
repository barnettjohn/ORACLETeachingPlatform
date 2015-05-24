package com.plat.orcl.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.QuestionDao;
import com.plat.orcl.dao.TestDao;
import com.plat.orcl.dao.TestQuestionDao;
import com.plat.orcl.dao.TestScoreDao;
import com.plat.orcl.dao.impl.QuestionDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestQuestionDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestScoreDaoMysqlImpl;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.Test;
import com.plat.orcl.domain.TestQuestion;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.utils.JdbcUtil;
import com.plat.orcl.web.formbean.StuTestAndScore;


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
	
	public Test getOneTest(String testid){
		return td.findTestAllByString("testid", testid).get(0);
	}
	public ArrayList<Test> getAllTest(){
		
		return (ArrayList<Test>) td.findAll();
		
	}
	
	public ArrayList<StuTestAndScore> getStudentTestScores(Person p){
		//用这一个语句就可以找到 学生测试分数的列表 
		String sql = "SELECT t.testid,t.testname,t.time,t.detail,t.cid,ts.score FROM oracle.test t left join (SELECT distinct testid,score FROM oracle.test_score where pid = '"+p.getPid()+"') ts on ts.testid=t.testid ";
		return (ArrayList<StuTestAndScore>) this.findStuTestAndScoreByString(sql);
	}
	
	public List<StuTestAndScore> findStuTestAndScoreByString(String sql) {
		
		List<StuTestAndScore> list = new ArrayList<StuTestAndScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				StuTestAndScore ts = new StuTestAndScore();
				ts.setTestid(rs.getString("testid"));
				ts.setTestname(rs.getString("testname"));
				ts.setTime(rs.getTimestamp("time"));
				ts.setDetail(rs.getString("detail"));
				ts.setCid(rs.getString("cid"));
				ts.setScore(rs.getString("score"));
				
				list.add(ts);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	public void addTestScore(TestScore ts){
		tsd.addTestScore(ts);
	}
	
	public TestScore findTestScore(String pid , String testid,boolean isAll){
		if(isAll){
			return tsd.findTestScoreAllByString("pid='"+pid+"' and testid", testid).get(0);
		}else{
			return tsd.findTestScoreByString("pid="+pid+" and testid=", testid).get(0);
		}
	}
}
