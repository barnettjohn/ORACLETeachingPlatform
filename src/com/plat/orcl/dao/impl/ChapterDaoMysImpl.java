package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.ChapterDao;
import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;
import com.plat.orcl.utils.PubUtil;

public class ChapterDaoMysImpl implements ChapterDao {
	private static CourseDaoMysqlImpl cd = new CourseDaoMysqlImpl();
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
	 * @see com.plat.orcl.dao.impl.CourseDao#addCourse(com.plat.orcl.domain.Course)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.ChapterDao#addChapter(com.plat.orcl.domain.Chapter)
	 */
	public void addChapter(Chapter ch) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into chapter values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.getChapterCode(conn, ch, "chaptid", "chapter"));
			stmt.setString(2, ch.getCid());
			stmt.setString(3, ch.getChaptna());
			stmt.setString(4, ch.getDetial());
			stmt.setString(5, ch.getDifficulty());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.ChapterDao#deleteChapterByString(java.lang.String, java.lang.String)
	 */
	public void deleteChapterByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from chapter where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.ChapterDao#updateChapter(com.plat.orcl.domain.Chapter)
	 */
	public void updateChapter(Chapter ch) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update chapter set cid=?,chaptna=?,detial=?,difficulty=? where chaptid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ch.getCid());
			stmt.setString(2, ch.getChaptna());
			stmt.setString(3, ch.getDetial());
			stmt.setString(4, ch.getDifficulty());
			stmt.setString(5, ch.getChaptid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.ChapterDao#findAll()
	 */
	public List<Chapter> findAll() {
		List<Chapter> list = new ArrayList<Chapter>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from chapter";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Chapter c = new Chapter();
				c.setChaptid(rs.getString("chaptid"));
				c.setCid(rs.getString("cid"));
				c.setChaptna(rs.getString("chaptna"));
				c.setDetial(rs.getString("detial"));
				c.setDifficulty(rs.getString("difficulty"));
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
	 * @see com.plat.orcl.dao.impl.ChapterDao#findChapterByString(java.lang.String, java.lang.String)
	 */
	public List<Chapter> findChapterByString(String xid,String yid) {
		List<Chapter> list = new ArrayList<Chapter>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from chapter where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Chapter c = new Chapter();
				c.setChaptid(rs.getString("chaptid"));
				c.setCid(rs.getString("cid"));
				c.setChaptna(rs.getString("chaptna"));
				c.setDetial(rs.getString("detial"));
				c.setDifficulty(rs.getString("difficulty"));
				
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
	 * @see com.plat.orcl.dao.impl.ChapterDao#findChapterAllByString(java.lang.String, java.lang.String)
	 */
	public List<Chapter> findChapterAllByString(String xid,String yid) {
		List<Chapter> list = new ArrayList<Chapter>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from chapter where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Chapter c = new Chapter();
				c.setChaptid(rs.getString("chaptid"));
				c.setCid(rs.getString("cid"));
				c.setChaptna(rs.getString("chaptna"));
				c.setDetial(rs.getString("detial"));
				c.setDifficulty(rs.getString("difficulty"));
				
				c.setC(cd .findCourseById(rs.getString("cid")));
				c.setP((ArrayList<Person>) pd .findPersonByString("group", rs.getString("chaptid")));
				list.add(c);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	public static synchronized String getChapterCode( Connection conn, Chapter chapter,String fieldString, String table) throws SQLException {
		
		String newCode = null;
		String cid=chapter.getCid();
		String contCode = null;
			
		Statement stmt = null;
					
		String strSQL = "select max(" + fieldString + ") as " + fieldString
				+ " from " + table+" where cid = '"+cid+"' ";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(strSQL);
			int code = 1;
			if (rs.next()) {
				String strFeild = rs.getString("chaptid");
				if (!PubUtil.isEmptyString(strFeild)) {
					code = Integer.parseInt(strFeild.substring(PubUtil.COURSE_ID_LENGTH,
							strFeild.length())) + 1;
					newCode = cid + PubUtil.appendZero(code, 2);
				}else{
					newCode = cid + PubUtil.appendZero(0, 2);
				}
			}
			
			rs.close();
			rs = null;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (null != stmt) {
					stmt.close();
					stmt = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return newCode;
	}
}
