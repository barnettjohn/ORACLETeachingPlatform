package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.FormDao;
import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.FormQuestion;
import com.plat.orcl.domain.StuScore;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class FormDaoMysqlImpl implements FormDao {
	private static ChapterDaoMysImpl chd = new ChapterDaoMysImpl();
	private static FormQuestionDaoMysqlImpl fqd = new FormQuestionDaoMysqlImpl();
	private static StuScoreDaoMysqlImpl ssd = new StuScoreDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.FormDao#addForm(com.plat.orcl.domain.Form)
	 */
	
	public void addForm(Form f) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into Form values(?,?,?,now(),?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, f.getFormid());
			stmt.setString(2, f.getFname());
			stmt.setString(3, f.getChaptid());
			stmt.setString(4, f.getDetail());
						
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#deleteFormByString(java.lang.String, java.lang.String)
	 */

	public void deleteFormByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from form where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.FormDao#updateForm()
	 */
	
	public void updateForm(Form f) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update form set fname=?,chaptid=?,detial=?,time=now() where formid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, f.getFname());
			stmt.setString(2, f.getChaptid());
			stmt.setString(3, f.getDetail());
			stmt.setString(4, f.getFormid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findAll()
	 */
	
	public List<Form> findAll() {
		List<Form> list = new ArrayList<Form>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from form";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Form t = new Form();
				t.setFormid(rs.getString("formid"));
				t.setFname(rs.getString("fname"));
				t.setChaptid(rs.getString("chaptid"));
				t.setTime(rs.getTimestamp("time"));
				t.setDetail(rs.getString("detail"));
				
				t.setChapter(chd .findChapterByString("chaptid", rs.getString("chaptid")).get(0));
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
	 * @see com.plat.orcl.dao.impl.FormDao#findFormByString(java.lang.String, java.lang.String)
	 */
	
	public List<Form> findFormByString(String xid,String yid) {
		List<Form> list = new ArrayList<Form>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from Form where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Form t = new Form();
				t.setFormid(rs.getString("formid"));
				t.setFname(rs.getString("fname"));
				t.setChaptid(rs.getString("chaptid"));
				t.setTime(rs.getTimestamp("time"));
				t.setDetail(rs.getString("detail"));
				
				t.setChapter(chd.findChapterByString("chaptid", rs.getString("chaptid")).get(0));
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
	 * @see com.plat.orcl.dao.impl.FormDao#findFormAllByString(java.lang.String, java.lang.String)
	 */
	
	public List<Form> findFormAllByString(String xid,String yid) {
		List<Form> list = new ArrayList<Form>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from Form where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Form t = new Form();
				t.setFormid(rs.getString("formid"));
				t.setFname(rs.getString("fname"));
				t.setChaptid(rs.getString("chaptid"));
				t.setTime(rs.getTimestamp("time"));
				t.setDetail(rs.getString("detail"));
				
				t.setChapter(chd.findChapterByString("chaptid", rs.getString("chaptid")).get(0));
				t.setFormquestionList((ArrayList<FormQuestion>) fqd .findFormQuestionByString("formid", rs.getString("formid")));
				t.setStuScoreList((ArrayList<StuScore>) ssd .findStuScoreByString("formid", rs.getString("formid")));
				
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
