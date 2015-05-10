package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.TestScoreDao;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class TestScoreDaoMysqlImpl implements TestScoreDao {
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
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
	 * @see com.plat.orcl.dao.impl.TestScoreDao#addTestScore(com.plat.orcl.domain.TestScore)
	 */
	public void addTestScore(TestScore ts) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into test_score values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ts.getPid());
			stmt.setString(2, ts.getTestid());
			stmt.setDouble(3, ts.getScore());
						
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestScoreDao#deleteTestScoreByString(java.lang.String, java.lang.String)
	 */
	public void deleteTestScoreByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from test_score where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.TestScoreDao#findAll()
	 */
	public List<TestScore> findAll() {
		List<TestScore> list = new ArrayList<TestScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_score";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				TestScore ts = new TestScore();
				ts.setPid(rs.getString("pid"));
				ts.setTestid(rs.getString("testid"));
				ts.setScore(rs.getDouble("score"));
				
				list.add(ts);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/*public List<TestScore> findTestScoreById(String pid,String tid) {
		List<TestScore> list = new ArrayList<TestScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_score where pid=? and tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.setString(2, tid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TestScore ts = new TestScore();
				ts.setPid(rs.getString("pid"));
				ts.setTestid(rs.getString("testid"));
				ts.setScore(rs.getDouble("score"));
				
				ts.setP(new PersonDaoMysqlImpl().findPersonById(rs.getString("pid")));
				ts.setT((new TestDaoMysqlImpl().findTestByString("testid", rs.getString("testid"))).get(0));
				
				list.add(ts);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}*/
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestScoreDao#findTestScoreByString(java.lang.String, java.lang.String)
	 */
	public List<TestScore> findTestScoreByString(String xid,String yid) {
		List<TestScore> list = new ArrayList<TestScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TestScore ts = new TestScore();
				ts.setPid(rs.getString("pid"));
				ts.setTestid(rs.getString("testid"));
				ts.setScore(rs.getDouble("score"));
				
				list.add(ts);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestScoreDao#findTestScoreAllByString(java.lang.String, java.lang.String)
	 */
	public List<TestScore> findTestScoreAllByString(String xid,String yid) {
		List<TestScore> list = new ArrayList<TestScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test_score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TestScore ts = new TestScore();
				ts.setPid(rs.getString("pid"));
				ts.setTestid(rs.getString("testid"));
				ts.setScore(rs.getDouble("score"));
				
				ts.setP(pd .findPersonById(rs.getString("pid")));
				ts.setT((td .findTestByString("testid", rs.getString("testid"))).get(0));
				
				list.add(ts);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
