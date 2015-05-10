package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.dao.FileDownloadDao;
import com.plat.orcl.domain.FileDownload;
import com.plat.orcl.exception.DaoAddException;
import com.plat.orcl.exception.DaoDeleteException;
import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.exception.DaoUpdateException;
import com.plat.orcl.exception.IdIsNullException;
import com.plat.orcl.utils.JdbcUtil;

public class FileDownloadDaoMysqlImpl implements FileDownloadDao {
	private static PersonDaoMysqlImpl pd = new PersonDaoMysqlImpl();
	private static FileDaoMysqlImpl fd = new FileDaoMysqlImpl();

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
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#addFileDownload(com.plat.orcl.domain.FileDownload)
	 */
	@Override
	public void addFileDownload(FileDownload fd) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "insert into file_download values(?,?,?,now())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fd.getFdid());
			stmt.setString(2, fd.getFid());
			stmt.setString(3, fd.getPid());
			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoAddException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#deleteFileDownload(java.lang.String)
	 */
	@Override
	public void deleteFileDownload(String fdid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(fdid) == null||String.valueOf(fdid).trim().equals("")) throw new IdIsNullException("id为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from file_download where fdid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fdid);
			
			stmt.executeUpdate();
		}catch(Exception e){
			throw new DaoDeleteException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}
	
	@Override
	public void deleteFileDownloadByString(String xid,String yid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			if( String.valueOf(yid) == null||String.valueOf(yid).trim().equals("")) throw new IdIsNullException(yid+"为空");
			
			conn = JdbcUtil.getConn();
			String sql = "delete from file_download where "+xid+"=?";
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
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#updateFileDownload(com.plat.orcl.domain.FileDownload)
	 */
	@Override
	public void updateFileDownload(FileDownload fd) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "update file_download set fid=? pid=?,time=now() where fdid=?";
			stmt.setString(1, fd.getFdid());
			stmt.setString(2, fd.getFid());
			stmt.setString(3, fd.getPid());

			stmt.executeUpdate();
			
		}catch(Exception e){
			throw new DaoUpdateException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

	}

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#findAll()
	 */
	@Override
	public List<FileDownload> findAll() {
		List<FileDownload> list = new ArrayList<FileDownload>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_download";
			stmt = conn.prepareStatement(sql);
			rs =  stmt.executeQuery();	
			while(rs.next()){
				FileDownload c = new FileDownload();
				c.setFdid(rs.getString("fdid"));
				c.setFid(rs.getString("fid"));
				c.setPid(rs.getString("pid"));
				c.setTime(rs.getTimestamp("time"));
				
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
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#findFileDownById(java.lang.String)
	 */
	@Override
	public FileDownload findFileDownById(String fdid) {
		FileDownload c = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_download where fdid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fdid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				c = new FileDownload();
				c.setFdid(rs.getString("fdid"));
				c.setFid(rs.getString("fid"));
				c.setPid(rs.getString("pid"));
				c.setTime(rs.getTimestamp("time"));
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return c;
	}
	
	@Override
	public FileDownload findFileDownAllById(String fdid) {
		FileDownload c = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_download where fdid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fdid);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				c = new FileDownload();
				c.setFdid(rs.getString("fdid"));
				c.setFid(rs.getString("fid"));
				c.setPid(rs.getString("pid"));
				c.setTime(rs.getTimestamp("time"));
				c.setP(pd .findPersonById(rs.getString("pid")));
				c.setF(fd .findFileById(rs.getString("fid")));
			
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return c;
	}
	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#findFileDownloadByString(java.lang.String, java.lang.String)
	 */
	@Override
	public List<FileDownload> findFileDownloadByString(String xid,String yid) {
		List<FileDownload> list = new ArrayList<FileDownload>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_download where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				FileDownload c = new FileDownload();
				c.setFdid(rs.getString("fdid"));
				c.setFid(rs.getString("fid"));
				c.setPid(rs.getString("pid"));
				c.setTime(rs.getTimestamp("time"));
				
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
	public List<FileDownload> findFileDownloadAllByString(String xid,String yid) {
		List<FileDownload> list = new ArrayList<FileDownload>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from file_download where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				FileDownload c = new FileDownload();
				c.setFdid(rs.getString("fdid"));
				c.setFid(rs.getString("fid"));
				c.setPid(rs.getString("pid"));
				c.setTime(rs.getTimestamp("time"));
				c.setP(pd.findPersonById(rs.getString("pid")));
				c.setF(fd.findFileById(rs.getString("fid")));
				
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
