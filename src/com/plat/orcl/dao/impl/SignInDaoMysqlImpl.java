package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.SignInDao;
import com.plat.orcl.domain.SignIn;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class SignInDaoMysqlImpl implements SignInDao {
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.SignInDao#addSignIn(com.plat.orcl.domain.SignIn)
	 */
	@Override
	public void addSignIn(SignIn si) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into sign_in values(null,?,now())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, si.getPid());

			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.SignInDao#deleteSignIn(java.lang.String)
	 */
	@Override
	public void deleteSignIn(String siid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(siid) == null||String.valueOf(siid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from sign_in where siid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, siid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteSignInByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(xid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from sign_in where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.SignInDao#findAll()
	 */
	@Override
	public List<SignIn> findAll() {
		List<SignIn> list = new ArrayList<SignIn>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from sign_in";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				
				SignIn si = new SignIn();
				si.setSiid(rs.getInt("siid"));
				si.setPid(rs.getString("pid"));
				si.setTime(rs.getTimestamp("time"));
				si.setP(pd.findPersonById(rs.getString("pid")));
				
				list.add(si);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public SignIn findSignInBySiid(String siid) {
		
		SignIn si = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from sign_in where siid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, siid);
			rs = stmt.executeQuery();
			if(rs.next()){
				si = new SignIn();
				si.setSiid(rs.getInt("siid"));
				si.setPid(rs.getString("pid"));
				si.setTime(rs.getTimestamp("time"));
				si.setP(pd .findPersonById(rs.getString("pid")));
				
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return si;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.SignInDao#findSignInByPid(java.lang.String)
	 */
	@Override
	public List<SignIn> findSignInByPid(String pid) {
	
		List<SignIn> list = new ArrayList<SignIn>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from sign_in where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			while(rs.next()){
				SignIn si = new SignIn();
				si.setSiid(rs.getInt("siid"));
				si.setPid(rs.getString("pid"));
				si.setTime(rs.getTimestamp("time"));
				si.setP(pd.findPersonById(rs.getString("pid")));
				
				list.add(si);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.SignInDao#findSignInByTime(java.lang.String, java.lang.String)
	 */
	@Override
	public List<SignIn> findSignInByTime(String time1 , String time2) {
		
		List<SignIn> list = new ArrayList<SignIn>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from sign_in where TO_DAYS(?) - TO_DAYS(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, time1);
			stmt.setString(2, time2);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				SignIn si = new SignIn();
				si.setSiid(rs.getInt("siid"));
				si.setPid(rs.getString("pid"));
				si.setTime(rs.getTimestamp("time"));
				si.setP(pd.findPersonById(rs.getString("pid")));
				
				list.add(si);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
