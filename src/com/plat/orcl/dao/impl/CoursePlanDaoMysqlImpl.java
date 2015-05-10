package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.CoursePlanDao;
import com.plat.orcl.domain.CoursePlan;
import com.plat.orcl.domain.TimePlan;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class CoursePlanDaoMysqlImpl implements CoursePlanDao {
	private static CourseDaoMysqlImpl cd=new CourseDaoMysqlImpl();
	private static TimePlanDaoMysqlImpl tp=new TimePlanDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.CoursePlanDao#addCoursePlan(com.plat.orcl.domain.CoursePlan)
	 */
	@Override
	public void addCoursePlan(CoursePlan cp) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into course_plan values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cp.getCid());
			stmt.setString(2, cp.getTid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CoursePlanDao#deleteCourseByCid(java.lang.String)
	 */
	@Override
	public void deleteCoursePlanByCid(String cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(cid) == null||String.valueOf(cid).trim().equals("")) throw new IdIsNullException("cid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from course_plan where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CoursePlanDao#deleteCourseByTid(java.lang.String)
	 */
	@Override
	public void deleteCoursePlanByTid(String tid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(tid) == null||String.valueOf(tid).trim().equals("")) throw new IdIsNullException("tid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from course_plan where tid=?";
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
	 * @see com.plat.orcl.dao.impl.CoursePlanDao#deleteCoursePlan(com.plat.orcl.domain.CoursePlan)
	 */
	@Override
	public void deleteCoursePlan(CoursePlan cp) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(cp.getCid()) == null||String.valueOf(cp.getCid()).trim().equals("")) throw new IdIsNullException("cid为空");
			if( String.valueOf(cp.getTid()) == null||String.valueOf(cp.getTid()).trim().equals("")) throw new IdIsNullException("tid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from course_plan where cid=? and tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cp.getCid());
			stmt.setString(2, cp.getTid());
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void updateCoursePlan(CoursePlan oldcp ,CoursePlan newcp) {
		try {
			this.deleteCoursePlan(oldcp);
			this.addCoursePlan(newcp);
		} catch (Exception e) {
			throw new DaoUpdateException(e);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CoursePlanDao#findAll()
	 */
	@Override
	public List<CoursePlan> findAll() {
		List<CoursePlan> list = new ArrayList<CoursePlan>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_plan";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				CoursePlan cp = new CoursePlan();
				cp.setCid(rs.getString("cid"));
				cp.setTid(rs.getString("tid"));
				cp.setC(cd.findCourseById(rs.getString("cid")));
				cp.setTp(tp.findTimePlanById(rs.getString("tid")));
				
				list.add(cp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CoursePlanDao#findCoursePlanByCid(java.lang.String)
	 */
	@Override
	public List<CoursePlan> findCoursePlanByCid(String cid) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CoursePlan> list = new ArrayList<CoursePlan>();
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_plan where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				CoursePlan cp = new CoursePlan();
				cp.setCid(rs.getString("cid"));
				cp.setTid(rs.getString("tid"));
				
				list.add(cp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<CoursePlan> findCoursePlanAllByCid(String cid) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CoursePlan> list = new ArrayList<CoursePlan>();
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_plan where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				CoursePlan cp = new CoursePlan();
				cp.setCid(rs.getString("cid"));
				cp.setTid(rs.getString("tid"));
				cp.setC(cd.findCourseById(rs.getString("cid")));
				cp.setTp(tp.findTimePlanById(rs.getString("tid")));
				
				list.add(cp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<CoursePlan> findCoursePlanByTid(String tid) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CoursePlan> list = new ArrayList<CoursePlan>();
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_plan where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				CoursePlan cp = new CoursePlan();
				cp.setCid(rs.getString("cid"));
				cp.setTid(rs.getString("tid"));
				
				list.add(cp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<CoursePlan> findCoursePlanAllByTid(String tid) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CoursePlan> list = new ArrayList<CoursePlan>();
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_plan where tid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				CoursePlan cp = new CoursePlan();
				cp.setCid(rs.getString("cid"));
				cp.setTid(rs.getString("tid"));
				cp.setC(cd.findCourseById(rs.getString("cid")));
				cp.setTp(tp.findTimePlanById(rs.getString("tid")));
				
				list.add(cp);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
}
