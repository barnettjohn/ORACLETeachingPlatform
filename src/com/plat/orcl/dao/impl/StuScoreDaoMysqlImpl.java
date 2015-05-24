package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.StuScoreDao;
import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.StuScore;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class StuScoreDaoMysqlImpl implements  StuScoreDao {
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
	private static FormDaoMysqlImpl fd = new FormDaoMysqlImpl();


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
	 * @see com.plat.orcl.dao.impl.StuScoreDao#addStuScore(com.plat.orcl.domain.StuScore)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#addStuScore(com.plat.orcl.domain.StuScore)
	 */
	public void addStuScore(StuScore ts) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into stu_score values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ts.getPid());
			stmt.setString(2, ts.getPidup());
			stmt.setString(3, ts.getFormid());
			stmt.setDouble(4, ts.getScore());
			stmt.setString(5, ts.getDetail());
						
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#deleteStuScoreById(java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#deleteStuScoreById(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void deleteStuScoreById(String pid,String pidup,String formid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("pid为空");
			if( String.valueOf(pidup) == null||String.valueOf(pidup).trim().equals("")) throw new IdIsNullException("pidup为空");
			if( String.valueOf(formid) == null||String.valueOf(formid).trim().equals("")) throw new IdIsNullException("formid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from stu_score where pid=? and pidup=? and formid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.setString(2, pidup);
			stmt.setString(3, formid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#deleteStuScoreByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#deleteStuScoreByString(java.lang.String, java.lang.String)
	 */
	public void deleteStuScoreByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from stu_score where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.StuScoreDao#updateStuScore(com.plat.orcl.domain.StuScore)
	 */
	public void updateStuScore(StuScore s) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update stu_score set score=?,detail=? where pid=? and pidup=? and formid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, s.getScore());
			stmt.setString(2, s.getDetail());
			stmt.setString(3, s.getPid());
			stmt.setString(4, s.getPidup());
			stmt.setString(5, s.getFormid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findAll()
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findAll()
	 */
	public List<StuScore> findAll() {
		List<StuScore> list = new ArrayList<StuScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from stu_score";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				StuScore ts = new StuScore();
				ts.setPid(rs.getString("pid"));
				ts.setPidup(rs.getString("pidup"));
				ts.setFormid(rs.getString("formid"));
				ts.setScore(rs.getDouble("score"));
				ts.setDetail(rs.getString("detail"));
				ts.setP(pd .findPersonById(rs.getString("pid")));
				ts.setPup(pd.findPersonById(rs.getString("pidup")));
				ts.setForm(fd .findFormByString("formid", rs.getString("formid")).get(0));
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
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findStuScoreByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findStuScoreByString(java.lang.String, java.lang.String)
	 */
	public List<StuScore> findStuScoreByString(String xid,String yid) {
		List<StuScore> list = new ArrayList<StuScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from stu_score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				StuScore ts = new StuScore();
				ts.setPid(rs.getString("pid"));
				ts.setPidup(rs.getString("pidup"));
				ts.setFormid(rs.getString("formid"));
				ts.setScore(rs.getDouble("score"));
				ts.setDetail(rs.getString("detail"));
				ts.setP(pd.findPersonById(rs.getString("pid")));
				ts.setPup(pd.findPersonById(rs.getString("pidup")));
				
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
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findStuScoreAllByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findStuScoreAllByString(java.lang.String, java.lang.String)
	 */
	public List<StuScore> findStuScoreAllByString(String xid,String yid) {
		List<StuScore> list = new ArrayList<StuScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from stu_score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				StuScore ts = new StuScore();
				ts.setPid(rs.getString("pid"));
				ts.setPidup(rs.getString("pidup"));
				ts.setFormid(rs.getString("formid"));
				ts.setScore(rs.getDouble("score"));
				ts.setDetail(rs.getString("detail"));
				
				ts.setP(pd.findPersonById(rs.getString("pid")));
				ts.setPup(pd.findPersonById(rs.getString("pidup")));
				ts.setForm(fd.findFormByString("formid", rs.getString("formid")).get(0));
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
