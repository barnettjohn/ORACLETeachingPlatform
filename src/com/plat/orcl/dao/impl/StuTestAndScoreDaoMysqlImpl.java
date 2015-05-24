package com.plat.orcl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.plat.orcl.exception.DaoFindException;
import com.plat.orcl.utils.JdbcUtil;
import com.plat.orcl.web.formbean.StuTestAndScore;

public class StuTestAndScoreDaoMysqlImpl {
	
	public List<StuTestAndScore> findStuTestAndScoreByString(String xid,String yid) {
		
		List<StuTestAndScore> list = new ArrayList<StuTestAndScore>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			String sql = "select * from stu_score where "+xid+"=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, yid);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				StuTestAndScore ts = new StuTestAndScore();
				/*ts.setPid(rs.getString("pid"));
				ts.setPidup(rs.getString("pidup"));
				ts.setFormid(rs.getString("formid"));
				ts.setScore(rs.getDouble("score"));*/
				ts.setDetail(rs.getString("detail"));
				
				list.add(ts);
			}
		}catch(Exception e){
			throw new DaoFindException(e);
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}

		return list;
	}


}
