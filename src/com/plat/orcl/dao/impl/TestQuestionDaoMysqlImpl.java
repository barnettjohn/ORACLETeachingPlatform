package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.TestQuestionDao;
import com.plat.orcl.domain.TestQuestion;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class TestQuestionDaoMysqlImpl implements TestQuestionDao {
	private static QuestionDaoMysqlImpl qd = new QuestionDaoMysqlImpl();
	private static TestDaoMysqlImpl td = new TestDaoMysqlImpl();

	/*
	 *模板代码：
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "";
			stmt = conn.prepareStatement(sql);
		}catch(Exception e){
			throw new DaoException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	 */
	
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestQuestionDao#addTestQuestion(com.plat.orcl.domain.TestQuestion)
	 */
	public void addTestQuestion(TestQuestion tq) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into test_question values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tq.getTestid());
			stmt.setString(2, tq.getQid());
						
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestQuestionDao#deleteTestQuestion(java.lang.String, java.lang.String)
	 */
	public void deleteTestQuestion(String testid,String qid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(testid) == null||String.valueOf(testid).trim().equals("")) throw new IdIsNullException("testid为空");
			if( String.valueOf(qid) == null||String.valueOf(qid).trim().equals("")) throw new IdIsNullException("qid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from test_question where testid=? and qid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, testid);
			stmt.setString(2, qid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestQuestionDao#deleteTestQuestionByString(java.lang.String, java.lang.String)
	 */
	public void deleteTestQuestionByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from test_question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestQuestionDao#findAll()
	 */
	public List<TestQuestion> findAll() {
		List<TestQuestion> list = new ArrayList<TestQuestion>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_question";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				TestQuestion tq = new TestQuestion();
				tq.setTestid(rs.getString("testid"));
				tq.setQid(rs.getString("qid"));
				
				list.add(tq);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestQuestionDao#findTestQuestionByString(java.lang.String, java.lang.String)
	 */
	public List<TestQuestion> findTestQuestionByString(String xid,String yid) {
		List<TestQuestion> list = new ArrayList<TestQuestion>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TestQuestion tq = new TestQuestion();
				tq.setTestid(rs.getString("testid"));
				tq.setQid(rs.getString("qid"));
				
				list.add(tq);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestQuestionDao#findTestQuestionAllByString(java.lang.String, java.lang.String)
	 */
	public List<TestQuestion> findTestQuestionAllByString(String xid,String yid) {
		List<TestQuestion> list = new ArrayList<TestQuestion>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TestQuestion tq = new TestQuestion();
				tq.setTestid(rs.getString("testid"));
				tq.setQid(rs.getString("qid"));
				
				tq.setQ((qd .findQuestionByString("qid", rs.getString("qid"))).get(0));
				tq.setT((td .findTestByString("testid", rs.getString("testid"))).get(0));
				
				list.add(tq);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
