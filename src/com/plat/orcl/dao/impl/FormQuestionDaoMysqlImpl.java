package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.FormQuestionDao;
import com.plat.orcl.domain.FormQuestion;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class FormQuestionDaoMysqlImpl implements  FormQuestionDao {
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
	
	
	
	private FormDaoMysqlImpl fd = new FormDaoMysqlImpl();

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#addFormQuestion(com.plat.orcl.domain.FormQuestion)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#addFormQuestion(com.plat.orcl.domain.FormQuestion)
	 */
	public void addFormQuestion(FormQuestion tq) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into form_question values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tq.getFormqid());
			stmt.setString(2, tq.getFormid());
			stmt.setString(3, tq.getContent());
			stmt.setInt(4, tq.getPower());
			stmt.setString(5, tq.getDetail());
						
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#deleteFormQuestion(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#deleteFormQuestion(java.lang.String)
	 */
	public void deleteFormQuestion(String formqid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(formqid) == null||String.valueOf(formqid).trim().equals("")) throw new IdIsNullException("formqid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from form_question where formqid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, formqid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#deleteFormQuestionByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#deleteFormQuestionByString(java.lang.String, java.lang.String)
	 */
	public void deleteFormQuestionByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from form_question where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findAll()
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findAll()
	 */
	public List<FormQuestion> findAll() {
		List<FormQuestion> list = new ArrayList<FormQuestion>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from form_question";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				FormQuestion tq = new FormQuestion();
				tq.setFormqid(rs.getString("formqid"));
				tq.setFormid(rs.getString("formid"));
				tq.setContent(rs.getString("content"));
				tq.setPower(rs.getInt("power"));
				tq.setDetail(rs.getString("detail"));
				tq.setF((fd.findFormByString("formid", rs.getString("formid"))).get(0));

				list.add(tq);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findFormQuestionByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findFormQuestionByString(java.lang.String, java.lang.String)
	 */
	public List<FormQuestion> findFormQuestionByString(String xid,String yid) {
		List<FormQuestion> list = new ArrayList<FormQuestion>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from form_question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				FormQuestion tq = new FormQuestion();
				tq.setFormqid(rs.getString("formqid"));
				tq.setFormid(rs.getString("formid"));
				tq.setContent(rs.getString("content"));
				tq.setPower(rs.getInt("power"));
				tq.setDetail(rs.getString("detail"));
				
				list.add(tq);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findFormQuestionAllByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findFormQuestionAllByString(java.lang.String, java.lang.String)
	 */
	public List<FormQuestion> findFormQuestionAllByString(String xid,String yid) {
		List<FormQuestion> list = new ArrayList<FormQuestion>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from form_question where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				FormQuestion tq = new FormQuestion();
				tq.setFormqid(rs.getString("formqid"));
				tq.setFormid(rs.getString("formid"));
				tq.setContent(rs.getString("content"));
				tq.setPower(rs.getInt("power"));
				tq.setDetail(rs.getString("detail"));
				
				
				tq.setF((fd.findFormByString("formid", rs.getString("formid"))).get(0));
				
				list.add(tq);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
