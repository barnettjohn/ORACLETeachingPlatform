package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.TestDao;
import com.plat.orcl.domain.Test;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class TestDaoMysqlImpl implements TestDao {
	private static TestQuestionDaoMysqlImpl tqd = new TestQuestionDaoMysqlImpl();
	private static TestScoreDaoMysqlImpl tsd = new TestScoreDaoMysqlImpl();
	private static CourseDaoMysqlImpl cd = new CourseDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.TestDao#addTest(com.plat.orcl.domain.Test)
	 */
	public void addTest(Test t) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into test values(?,?,now(),?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, t.getTestid());
			stmt.setString(2, t.getTestname());
			stmt.setString(3, t.getDetail());
			stmt.setString(4, t.getCid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestDao#deleteTestByString(java.lang.String, java.lang.String)
	 */
	public void deleteTestByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from test where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.TestDao#findAll()
	 */
	public List<Test> findAll() {
		List<Test> list = new ArrayList<Test>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Test t = new Test();
				t.setTestid(rs.getString("testid"));
				t.setTestname(rs.getString("testname"));
				t.setTime(rs.getTimestamp("time"));
				t.setDetail(rs.getString("detail"));
				t.setCid(rs.getString("cid"));
				
				t.setqList(tqd .findTestQuestionAllByString("testid", rs.getString("testid")));
				t.setsList(tsd.findTestScoreAllByString("testid", rs.getString("testid")));
				t.setC(cd .findCourseById(rs.getString("cid")));
				list.add(t);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestDao#findTestByString(java.lang.String, java.lang.String)
	 */
	public List<Test> findTestByString(String xid,String yid) {
		List<Test> list = new ArrayList<Test>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Test t = new Test();
				t.setTestid(rs.getString("testid"));
				t.setTestname(rs.getString("testname"));
				t.setTime(rs.getTimestamp("time"));
				t.setDetail(rs.getString("detail"));
				t.setCid(rs.getString("cid"));

				list.add(t);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TestDao#findTestAllByString(java.lang.String, java.lang.String)
	 */
	public List<Test> findTestAllByString(String xid,String yid) {
		List<Test> list = new ArrayList<Test>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from test where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Test t = new Test();
				t.setTestid(rs.getString("testid"));
				t.setTestname(rs.getString("testname"));
				t.setTime(rs.getTimestamp("time"));
				t.setDetail(rs.getString("detail"));
				t.setCid(rs.getString("cid"));

				t.setqList(tqd.findTestQuestionAllByString("testid", rs.getString("testid")));
				t.setsList(tsd.findTestScoreByString("testid", rs.getString("testid")));
				t.setC(cd.findCourseById(rs.getString("cid")));

				list.add(t);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
