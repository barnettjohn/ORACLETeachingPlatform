package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.PersonDao;
import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class PersonDaoMysqlImpl implements PersonDao {
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
	private static ScoreDaoMysqlImpl sd = new ScoreDaoMysqlImpl();
	private static SignInDaoMysqlImpl sid = new SignInDaoMysqlImpl();
	private static CourseDaoMysqlImpl cd = new CourseDaoMysqlImpl();
	private static FileDaoMysqlImpl fd = new FileDaoMysqlImpl();
	private static FileDownloadDaoMysqlImpl fdl = new FileDownloadDaoMysqlImpl();
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.PersonDao#addPerson(com.plat.orcl.domain.Person)
	 */
	@Override
	public void addPerson(Person p) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into person_info values(?,?,?,?,?,?,'s')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getPid());
			stmt.setString(2, p.getPname());
			stmt.setString(3, p.getPassword());
			stmt.setString(4, p.getPclass());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getGroup());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.PersonDao#deletePerson(java.lang.String)
	 */
	@Override
	public void deletePerson(String pid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from person_info where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deletePersonByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from person_info where "+xid+"=?";
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
	public void deletePersonCascade(String pid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");
			conn = JdbcUtil.getConn();
			
			new ScoreDaoMysqlImpl().deleteScoreByPid(pid);
			new SignInDaoMysqlImpl().deleteSignInByString("pid",pid);
			new CourseDaoMysqlImpl().deleteCourseByString("pid",pid);
			new FileDaoMysqlImpl().deleteFileByString("pidup",pid);
			new FileDownloadDaoMysqlImpl().deleteFileDownloadByString("pid",pid);
			
			String sql = "delete from person_info where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
		
	@Override
	public void deletePersonByStringCascade(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Person> list = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			list = this.findPersonByString(xid, yid);
			
			
			if(list.size()!=0 && list != null){
				for(Person p : list){
					sd.deleteScoreByPid(p.getPid());
					sid.deleteSignInByString("pid",p.getPid());
					cd.deleteCourseByString("pid",p.getPid());
					fd.deleteFileByString("pidup",p.getPid());
					fdl.deleteFileDownloadByString("pid",p.getPid());
				}
			}
			
			conn = JdbcUtil.getConn();
			String sql = "delete from person_info where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.PersonDao#updatePerson(com.plat.orcl.domain.Person)
	 */
	@Override
	public void updatePerson(Person p) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update person_info set pname=?,password=?,pclass=?,email=?,group=?,pstate=? where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getPname());
			stmt.setString(2, p.getPassword());
			stmt.setString(3, p.getPclass());
			stmt.setString(4, p.getEmail());
			stmt.setString(5, p.getGroup());
			stmt.setString(6, p.getPstate());
			stmt.setString(7, p.getPid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.PersonDao#findAll()
	 */
	@Override
	public List<Person> findAll() {
		List<Person> list = new ArrayList<Person>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from person_info";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Person p = new Person();
				p.setPid(rs.getString("pid"));
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
				
				list.add(p);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.PersonDao#findPersonById(java.lang.String)
	 */
	@Override
	public Person findPersonById(String pid) {
		Person p = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");

			conn = JdbcUtil.getConn();
			String sql = "select * from person_info where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			if(rs.next()){
				p = new Person();
				p.setPid(rs.getString("pid"));
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return p;
	}
	
	@Override
	public Person findPersonByIdPassword(String pid,String password) {
		Person p = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");

			conn = JdbcUtil.getConn();
			String sql = "select * from person_info where pid=? and password=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				p = new Person();
				p.setPid(rs.getString("pid"));
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return p;
	}
	
	
	
	@Override
	public Person findPersonAllById(String pid) {
		Person p = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");

			conn = JdbcUtil.getConn();
			String sql = "select * from person_info where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			if(rs.next()){
				p = new Person();
				p.setPid(rs.getString("pid"));
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
				
				p.setScores(sd.findScoreByPid(pid));
				p.setSignIns(sid.findSignInByPid(pid));
				p.setTeaCourses(cd.findCourseByString("pid", pid));
				p.setFileUps(fd.findFileByString("pidup", pid));
				p.setFileDowns(fdl.findFileDownloadByString("pid", pid));
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return p;
	}
	
	@Override
	public Person findPersonAllByIdPassword(String pid,String password) {
		Person p = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");

			conn = JdbcUtil.getConn();
			String sql = "select * from person_info where pid=? and password=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next()){
				p = new Person();
				p.setPid(rs.getString("pid"));
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
				
				p.setScores(sd.findScoreByPid(pid));
				p.setSignIns(sid.findSignInByPid(pid));
				p.setTeaCourses(cd.findCourseByString("pid", pid));
				p.setFileUps(fd.findFileByString("pidup", pid));
				p.setFileDowns(fdl.findFileDownloadByString("pid", pid));
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return p;
	}
	
	@Override
	public List<Person> findPersonByString(String xid, String yid) {

		List<Person> list = new ArrayList<Person>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException("id为空");

			conn = JdbcUtil.getConn();
			String sql = "select * from person_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Person p = new Person();
				p.setPid(rs.getString("pid"));
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
				
				list.add(p);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<Person> findPersonAllByString(String xid, String yid) {

		List<Person> list = new ArrayList<Person>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from person_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				Person p = new Person();
				String pid = rs.getString("pid");
				p.setPid(pid);
				p.setPname(rs.getString("pname"));
				p.setPassword(rs.getString("password"));
				p.setPclass(rs.getString("pclass"));
				p.setEmail(rs.getString("email"));
				p.setGroup(rs.getString("group"));
				p.setPstate(rs.getString("pstate"));
				
				p.setScores(sd.findScoreByPid(pid));
				p.setSignIns(sid.findSignInByPid(pid));
				p.setTeaCourses(cd.findCourseByString("pid", pid));
				p.setFileUps(fd.findFileByString("pidup", pid));
				p.setFileDowns(fdl.findFileDownloadByString("pid", pid));
				
				list.add(p);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
}
