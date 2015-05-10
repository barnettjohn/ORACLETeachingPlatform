package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.TimPanDao;
import com.plat.orcl.domain.TimePlan;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class TimePlanDaoMysqlImpl implements TimPanDao {
	private static CoursePlanDaoMysqlImpl cpd = new CoursePlanDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.TimPanDao#addTimePlan(com.plat.orcl.domain.TimePlan)
	 */
	@Override
	public void addTimePlan(TimePlan tp) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into time_plan values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tp.getTid());
			stmt.setString(2, tp.getTdetail());
			stmt.setString(3, tp.getTsign());
			stmt.setString(4, tp.getTweek());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TimPanDao#deleteTimePlan(java.lang.String)
	 */
	@Override
	public void deleteTimePlan(String tid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(tid) == null||String.valueOf(tid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from time_plan where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteTimePlanCascade(String tid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(tid) == null||String.valueOf(tid).trim().equals("")) throw new IdIsNullException("id为空");
			
			new CoursePlanDaoMysqlImpl().deleteCoursePlanByTid(tid);
			
			conn = JdbcUtil.getConn();
			String sql = "delete from time_plan where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TimPanDao#updateTimePlan(com.plat.orcl.domain.TimePlan)
	 */
	@Override
	public void updateTimePlan(TimePlan tp) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update time_plan set tdetail=?,tsign=?,tweek=? where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tp.getTdetail());
			stmt.setString(2, tp.getTsign());
			stmt.setString(3, tp.getTweek());
			stmt.setString(4, tp.getTid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TimPanDao#findAll()
	 */
	@Override
	public List<TimePlan> findAll() {
		List<TimePlan> list = new ArrayList<TimePlan>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from time_plan";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				TimePlan tp = new TimePlan();
				tp.setTid(rs.getString("tid"));
				tp.setTdetail(rs.getString("tdetail"));
				tp.setTsign(rs.getString("tsign"));
				tp.setTweek(rs.getString("tweek"));
				list.add(tp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.TimPanDao#findTimePlanById(java.lang.String)
	 */
	@Override
	public TimePlan findTimePlanById(String tid) {
		TimePlan tp = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from time_plan where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				tp = new TimePlan();
				tp.setTid(rs.getString("tid"));
				tp.setTdetail(rs.getString("tdetail"));
				tp.setTsign(rs.getString("tsign"));
				tp.setTweek(rs.getString("tweek"));
				
			}
			
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return tp;
	}
	
	@Override
	public TimePlan findTimePlanAllById(String tid) {
		TimePlan tp = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from time_plan where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				tp = new TimePlan();
				tp.setTid(rs.getString("tid"));
				tp.setTdetail(rs.getString("tdetail"));
				tp.setTsign(rs.getString("tsign"));
				tp.setTweek(rs.getString("tweek"));
				
				tp.setCoursePlans(cpd.findCoursePlanByTid(tid));
				
			}
			
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return tp;
	}
	
	@Override
	public List<TimePlan> findTimePlanByString(String xid,String yid) {
		List<TimePlan> list = new ArrayList<TimePlan>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from time_plan where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TimePlan tp = new TimePlan();
				tp.setTid(rs.getString("tid"));
				tp.setTdetail(rs.getString("tdetail"));
				tp.setTsign(rs.getString("tsign"));
				tp.setTweek(rs.getString("tweek"));
			
				list.add(tp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<TimePlan> findTimePlanAllByString(String xid,String yid) {
		List<TimePlan> list = new ArrayList<TimePlan>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from time_plan where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				TimePlan tp = new TimePlan();
				tp.setTid(rs.getString("tid"));
				tp.setTdetail(rs.getString("tdetail"));
				tp.setTsign(rs.getString("tsign"));
				tp.setTweek(rs.getString("tweek"));
				tp.setCoursePlans(cpd.findCoursePlanByTid(rs.getString("tid")));
			
				list.add(tp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	
}
