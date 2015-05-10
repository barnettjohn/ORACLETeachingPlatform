package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.QuestionDao;
import com.plat.orcl.domain.Question;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;
import com.plat.orcl.utils.PubUtil;

public class QuestionDaoMysqlImpl implements QuestionDao {
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
	
	PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
	ChapterDaoMysImpl cd = new ChapterDaoMysImpl();
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#addQuestion(com.plat.orcl.domain.Question)
	 */
	public void addQuestion(Question q) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into question values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, q.getQid());
			stmt.setString(2, q.getQname());
			stmt.setString(3, q.getQcontent());
			stmt.setString(4, q.getQright());
			stmt.setString(5, q.getQdetail());
			stmt.setString(6, q.getChaptid());
			stmt.setString(7, q.getPupid());
			
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#deleteQuestionByString(java.lang.String, java.lang.String)
	 */
	public void deleteQuestionByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from question where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.QuestionDao#updateQuestion(com.plat.orcl.domain.Question)
	 */
	public void updateQuestion(Question q) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update question set qname=?,qcontent=?,qright=?,qdetail=?,chaptid=?,pupid=? where qid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, q.getQname());
			stmt.setString(2, q.getQcontent());
			stmt.setString(3, q.getQright());
			stmt.setString(4, q.getQdetail());
			stmt.setString(5, q.getChaptid());
			stmt.setString(6, q.getPupid());
			stmt.setString(7, q.getQid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#findAll()
	 */
	public List<Question> findAll() {
		List<Question> list = new ArrayList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from question";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Question q = new Question();
				q.setQid(rs.getString("qid"));
				q.setQname(rs.getString("qname"));
				q.setQcontent(rs.getString("qcontent"));
				q.setQright(rs.getString("qright"));
				q.setQdetail(rs.getString("qdetail"));
				q.setChaptid(rs.getString("chaptid"));
				q.setPupid(rs.getString("pupid"));
				
				
				q.setP(pd.findPersonById(rs.getString("pupid")));
				q.setChapter(cd.findChapterByString("chaptid", rs.getString("chaptid")).get(0));
				list.add(q);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#findQuestionByString(java.lang.String, java.lang.String)
	 */
	public List<Question> findQuestionByString(String xid,String yid) {
		List<Question> list = new ArrayList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setQid(rs.getString("qid"));
				q.setQname(rs.getString("qname"));
				q.setQcontent(rs.getString("qcontent"));
				q.setQright(rs.getString("qright"));
				q.setQdetail(rs.getString("qdetail"));
				q.setChaptid(rs.getString("chaptid"));
				q.setPupid(rs.getString("pupid"));
				q.setP(pd.findPersonById(rs.getString("pupid")));
				q.setChapter(cd.findChapterByString("chaptid", rs.getString("chaptid")).get(0));
				list.add(q);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#findQuestionAllByString(java.lang.String, java.lang.String)
	 
	public List<Question> findQuestionAllByString(String xid,String yid) {
		List<Question> list = new ArrayList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from Question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Question c = new Question();
				c.setChaptid(rs.getString("chaptid"));
				c.setCid(rs.getString("cid"));
				c.setChaptna(rs.getString("chaptna"));
				c.setDetial(rs.getString("detial"));
				c.setDifficulty(rs.getString("difficulty"));
				
				c.setC(new CourseDaoMysqlImpl().findCourseById(rs.getString("cid")));
				c.setP((ArrayList<Person>) new PersonDaoMysqlImpl().findPersonByString("group", rs.getString("chaptid")));
				list.add(c);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}*/
	
	
}
