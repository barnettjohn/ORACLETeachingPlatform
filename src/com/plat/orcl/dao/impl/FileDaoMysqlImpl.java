package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.FileDao;
import com.plat.orcl.domain.File;
import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class FileDaoMysqlImpl implements FileDao {
	
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
	private static FileDownloadDaoMysqlImpl fdd = new FileDownloadDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.FileDao#addFile(com.plat.orcl.domain.File)
	 */
	@Override
	public void addFile(File f) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into file_info values(?,?,?,?,now(),?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, f.getFid());
			stmt.setString(2, f.getFname());
			stmt.setString(3, f.getPidup());
			stmt.setString(4, f.getUrl());
			stmt.setDate(5, new Date(f.getAuthority().getTime()));
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDao#deleteFile(java.lang.String)
	 */
	@Override
	public void deleteFile(String fid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(fid) == null||String.valueOf(fid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from file_info where fid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteFileCascade(String fid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(fid) == null||String.valueOf(fid).trim().equals("")) throw new IdIsNullException("id为空");
			
			new FileDownloadDaoMysqlImpl().deleteFileDownloadByString("fid", fid);
			
			conn = JdbcUtil.getConn();
			String sql = "delete from file_info where fid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteFileByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from file_info where "+xid+"=?";
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
	public void deleteFileByStringCascade(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<File> list = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			list = this.findFileByString(xid, yid);
			
			FileDownloadDaoMysqlImpl fdl = new FileDownloadDaoMysqlImpl();
			
			if(list.size()!=0 && list != null){
				for(File f : list){
					fdl.deleteFileDownloadByString("fid",f.getFid());
				}
			}
			
			conn = JdbcUtil.getConn();
			String sql = "delete from file_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
		
	

	/*public void updateFile(File f) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update course_info set cname=?,detail=? where cid=? and pid=?";
			stmt.setString(1, f.getCname());
			stmt.setString(2, f.getDetail());
			stmt.setString(3, f.getCid());
			stmt.setString(4, f.getPid());
			
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}*/

	/*public List<File> findAll() {
		List<File> list = new ArrayList<File>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_info";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				File c = new File();
				c.setCid(rs.getString("cid"));
				c.setPid(rs.getString("pid"));;
				c.setCname(rs.getString("cname"));;
				c.setDetail(rs.getString("detail"));;
				
				list.add(c);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}*/

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDao#findFileByName(java.lang.String)
	 */
	@Override
	public  List<File> findFileByName(String fname) {
		List<File> list = new ArrayList<File>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_info where fname=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fname);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				File f = new File();
				f.setFid(rs.getString("fid"));
				f.setFname(rs.getString("fname"));
				f.setPidup(rs.getString("pidup"));
				f.setUrl(rs.getString("url"));
				f.setTime(rs.getTimestamp("time"));
				f.setAuthority(rs.getTimestamp("authority"));
				f.setP(pd .findPersonById(rs.getString("pidup")));

				list.add(f);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDao#findFileById(java.lang.String)
	 */
	@Override
	public  File findFileById(String fid) {
		File f = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_info where fid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				f = new File();
				f.setFid(rs.getString("fid"));
				f.setFname(rs.getString("fname"));
				f.setPidup(rs.getString("pidup"));
				f.setUrl(rs.getString("url"));
				f.setTime(rs.getTimestamp("time"));
				f.setAuthority(rs.getTimestamp("authority"));
				f.setP(pd.findPersonById(rs.getString("pidup")));

			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return f;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDao#findFileByTime(java.lang.String, java.lang.String)
	 */
	@Override
	public  List<File> findFileByTime(String time1,String time2) {
		List<File> list = new ArrayList<File>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_info where TO_DAYS(?) - TO_DAYS(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, time1);
			stmt.setString(2, time2);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				File f = new File();
				f.setFid(rs.getString("fid"));
				f.setFname(rs.getString("fname"));
				f.setPidup(rs.getString("pidup"));
				f.setUrl(rs.getString("url"));
				f.setTime(rs.getTimestamp("time"));
				f.setAuthority(rs.getTimestamp("authority"));
				f.setP(pd.findPersonById(rs.getString("pidup")));
				
				list.add(f);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	
	@Override
	public  List<File> findFileByString(String xid,String yid) {
		List<File> list = new ArrayList<File>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				File f = new File();
				f.setFid(rs.getString("fid"));
				f.setFname(rs.getString("fname"));
				f.setPidup(rs.getString("pidup"));
				f.setUrl(rs.getString("url"));
				f.setTime(rs.getTimestamp("time"));
				f.setAuthority(rs.getTimestamp("authority"));
				f.setP(pd.findPersonById(rs.getString("pidup")));

				list.add(f);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
	@Override
	public  List<File> findFileAllByString(String xid,String yid) {
		List<File> list = new ArrayList<File>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_info where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				File f = new File();
				f.setFid(rs.getString("fid"));
				f.setFname(rs.getString("fname"));
				f.setPidup(rs.getString("pidup"));
				f.setUrl(rs.getString("url"));
				f.setTime(rs.getTimestamp("time"));
				f.setAuthority(rs.getTimestamp("authority"));
				f.setP(new PersonDaoMysqlImpl().findPersonById(rs.getString("pidup")));
				
				f.setFileDownloads(fdd .findFileDownloadByString("fid", rs.getString("fid")));
				
				list.add(f);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}
	
}
