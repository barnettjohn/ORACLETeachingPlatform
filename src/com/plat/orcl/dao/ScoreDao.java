package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Score;

public interface ScoreDao {

	void addScore(Score s);
	
	void deleteScoreByCid(String cid);

	void deleteScoreByPid(String pid);
	
	void deleteScore(String pid, String cid);

	void deleteScoreByString(String xid, String yid);

	void updateScore(Score s);

	List<Score> findAll();

	List<Score> findScoreByPid(String pid);

	List<Score> findScoreAllByPid(String pid);
	
	List<Score> findScoreByString(String xid, String yid);

	List<Score> findScoreAllByString(String xid, String yid);

	void updateScoreByString(Score s, String sn, double score);

	void updateScoreBySql(String set, String where);


}