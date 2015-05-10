package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.ScoreDao;
import com.plat.orcl.domain.Score;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class ScoreDaoMysqlImpl implements ScoreDao {
	
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
	private static CourseDaoMysqlImpl cd = new CourseDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.SocreDao#addScore(com.plat.orcl.domain.Score)
	 */
	@Override
	public void addScore(Score s) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into score values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getCid());
			stmt.setString(2, s.getPid());
			stmt.setDouble(3, s.getStu());
			stmt.setDouble(4, s.getTea());
			stmt.setDouble(5, s.getFinalscore());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteScore(String pid,String cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("pid为空");
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("cid为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from score where pid=? and cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			stmt.setString(2, cid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.SocreDao#deleteScore(java.lang.String)
	 */
	@Override
	public void deleteScoreByPid(String pid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(pid) == null||String.valueOf(pid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from score where pid=?";
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
	public void deleteScoreByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from score where "+xid+"=?";
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
	public void deleteScoreByCid(String cid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(cid) == null||String.valueOf(cid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from score where cid=?";
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
	 * @see com.plat.orcl.dao.impl.SocreDao#updateScore(com.plat.orcl.domain.Score)
	 */
	@Override
	public void updateScore(Score s) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update score set stu=?,tea=?,finalscore=? where pid=? and cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, s.getStu());
			stmt.setDouble(2, s.getTea());
			stmt.setDouble(3, s.getFinalscore());
			stmt.setString(4, s.getPid());
			stmt.setString(5, s.getCid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	@Override
	public void updateScoreByString(Score s,String sn,double score) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update score set "+sn+"="+score+" where pid=? and cid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, s.getPid());
			stmt.setString(2, s.getCid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	@Override
	public void updateScoreBySql(String set,String where) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update score set "+set+" where "+where+"";
			stmt = conn.prepareStatement(sql);
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.SocreDao#findAll()
	 */
	@Override
	public List<Score> findAll() {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from score";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				Score s = new Score();
				s.setCid(rs.getString("cid"));
				s.setPid(rs.getString("pid"));
				s.setStu(rs.getDouble("stu"));
				s.setTea(rs.getDouble("tea"));
				s.setFinalscore(rs.getDouble("finalscore"));
				s.setP(pd .findPersonById(rs.getString("pid")));
				s.setC(cd .findCourseById(rs.getString("cid")));
				
				list.add(s);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.SocreDao#findScoreByPid(java.lang.String)
	 */
	@Override
	public List<Score> findScoreByPid(String pid) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from score where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			while(rs.next()){
				Score s = new Score();
				s.setCid(rs.getString("cid"));
				s.setPid(rs.getString("pid"));
				s.setStu(rs.getDouble("stu"));
				s.setTea(rs.getDouble("tea"));
				s.setFinalscore(rs.getDouble("finalscore"));
				
				list.add(s);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<Score> findScoreAllByPid(String pid) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from score where pid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			rs = stmt.executeQuery();
			while(rs.next()){
				Score s = new Score();
				s.setCid(rs.getString("cid"));
				s.setPid(rs.getString("pid"));
				s.setStu(rs.getDouble("stu"));
				s.setTea(rs.getDouble("tea"));
				s.setFinalscore(rs.getDouble("finalscore"));
				s.setP(pd.findPersonById(rs.getString("pid")));
				s.setC(cd.findCourseById(rs.getString("cid")));
				
				list.add(s);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<Score> findScoreByString(String xid,String yid) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			rs = stmt.executeQuery();
			while(rs.next()){
				Score s = new Score();
				s.setCid(rs.getString("cid"));
				s.setPid(rs.getString("pid"));
				s.setStu(rs.getDouble("stu"));
				s.setTea(rs.getDouble("tea"));
				s.setFinalscore(rs.getDouble("finalscore"));;
				s.setP(pd.findPersonAllById(rs.getString("pid")));
				s.setC(cd.findCourseById(rs.getString("cid")));
				
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public List<Score> findScoreAllByString(String xid,String yid) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			rs = stmt.executeQuery();
			while(rs.next()){
				Score s = new Score();
				s.setCid(rs.getString("cid"));
				s.setPid(rs.getString("pid"));
				s.setStu(rs.getDouble("stu"));
				s.setTea(rs.getDouble("tea"));
				s.setFinalscore(rs.getDouble("finalscore"));;
				s.setP(pd.findPersonById(rs.getString("pid")));
				s.setC(cd.findCourseById(rs.getString("cid")));
				
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
}
