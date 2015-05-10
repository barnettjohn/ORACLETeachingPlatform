package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.CourseDao;
import com.plat.orcl.domain.Course;
import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class CourseDaoMysqlImpl implements CourseDao {
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
	private static CoursePlanDaoMysqlImpl cpd = new CoursePlanDaoMysqlImpl();
	private static ScoreDaoMysqlImpl sd = new ScoreDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.CourseDao#addCourse(com.plat.orcl.domain.Course)
	 */
	@Override
	public void addCourse(Course c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into course_info values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, c.getCid());
			stmt.setString(2, c.getPid());
			stmt.setString(3, c.getCname());
			stmt.setString(4, c.getDetail());
			stmt.setString(5, c.getChnum());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CourseDao#deleteCourse(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteCourse(String cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(cid) == null||String.valueOf(cid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from course_info where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteCourseCascade(String cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(cid) == null||String.valueOf(cid).trim().equals("")) throw new IdIsNullException("id为空");
			new ScoreDaoMysqlImpl().deleteScoreByCid(cid);
			new CoursePlanDaoMysqlImpl().deleteCoursePlanByCid(cid);
			
			conn = JdbcUtil.getConn();
			String sql = "delete from course_info where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteCourseByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from course_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteCourseByStringCascade(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Course> list = null;

		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");

			list = this.findCourseByString(xid, yid);
			
			ScoreDaoMysqlImpl sd = new ScoreDaoMysqlImpl();
			CoursePlanDaoMysqlImpl cpd = new CoursePlanDaoMysqlImpl();
			
			if(list.size()!=0 && list != null){
				for(Course c : list){
					sd.deleteScoreByCid(c.getCid());
					cpd.deleteCoursePlanByCid(c.getCid());
				}
			}
			conn = JdbcUtil.getConn();
			String sql = "delete from course_info where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.CourseDao#updateCourse(com.plat.orcl.domain.Course)
	 */
	@Override
	public void updateCourse(Course c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update course_info set pid=?,cname=?,detail=?,chnum=? where cid=?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, c.getPid());
			stmt.setString(2, c.getCname());
			stmt.setString(3, c.getDetail());
			stmt.setString(4, c.getChnum());
			stmt.setString(5, c.getCid());

			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CourseDao#findAll()
	 */
	@Override
	public List<Course> findAll() {
		List<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_info";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Course c = new Course();
				c.setCid(rs.getString("cid"));
				c.setPid(rs.getString("pid"));
				c.setCname(rs.getString("cname"));
				c.setDetail(rs.getString("detail"));
				c.setChnum(rs.getString("chnum"));
				list.add(c);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CourseDao#findCoursecateById(java.lang.String, java.lang.String)
	 */
	@Override
	public Course findCourseById(String cid) {
		Course c = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_info where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				c = new Course();
				c.setCid(rs.getString("cid"));
				c.setPid(rs.getString("pid"));
				c.setCname(rs.getString("cname"));
				c.setDetail(rs.getString("detail"));
				c.setChnum(rs.getString("chnum"));

			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return c;
	}
	
	@Override
	public Course findCourseAllById(String cid) {
		Course c = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_info where cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				c = new Course();
				c.setCid(rs.getString("cid"));
				c.setPid(rs.getString("pid"));
				c.setCname(rs.getString("cname"));
				c.setDetail(rs.getString("detail"));
				c.setChnum(rs.getString("chnum"));
				
				c.setTeacher(pd.findPersonById(rs.getString("pid")));
				c.setCoursePlans(cpd .findCoursePlanByCid(cid));
				c.setScores(sd .findScoreByString("cid", cid));
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return c;
	}

	
	@Override
	public List<Course> findCourseByString(String xid,String yid) {
		List<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Course c = new Course();
				c.setCid(rs.getString("cid"));
				c.setPid(rs.getString("pid"));
				c.setCname(rs.getString("cname"));
				c.setDetail(rs.getString("detail"));
				c.setChnum(rs.getString("chnum"));

				list.add(c);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<Course> findCourseAllByString(String xid,String yid) {
		List<Course> list = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from course_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Course c = new Course();
				c.setCid(rs.getString("cid"));
				c.setPid(rs.getString("pid"));
				c.setCname(rs.getString("cname"));
				c.setDetail(rs.getString("detail"));
				c.setChnum(rs.getString("chnum"));

				c.setTeacher(pd.findPersonById(rs.getString("pid")));
				c.setCoursePlans(cpd.findCoursePlanByCid(rs.getString("cid")));
				c.setScores(sd.findScoreByString("cid", rs.getString("cid")));
				
				list.add(c);	
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
