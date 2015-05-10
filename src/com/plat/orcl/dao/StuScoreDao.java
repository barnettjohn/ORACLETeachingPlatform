package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.StuScore;

public interface StuScoreDao {

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#addStuScore(com.plat.orcl.domain.StuScore)
	 */
	void addStuScore(StuScore ts);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#deleteStuScoreById(java.lang.String, java.lang.String, java.lang.String)
	 */
	void deleteStuScoreById(String pid, String pidup, String formid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#deleteStuScoreByString(java.lang.String, java.lang.String)
	 */
	void deleteStuScoreByString(String xid, String yid);

	void updateStuScore(StuScore s);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findAll()
	 */
	List<StuScore> findAll();

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findStuScoreByString(java.lang.String, java.lang.String)
	 */
	List<StuScore> findStuScoreByString(String xid, String yid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.StuScoreDao#findStuScoreAllByString(java.lang.String, java.lang.String)
	 */
	List<StuScore> findStuScoreAllByString(String xid, String yid);

}