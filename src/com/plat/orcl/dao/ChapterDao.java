package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Chapter;

public interface ChapterDao {

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.CourseDao#addCourse(com.plat.orcl.domain.Course)
	 */
	void addChapter(Chapter ch);

	void deleteChapterByString(String xid, String yid);

	void updateChapter(Chapter ch);

	List<Chapter> findAll();

	List<Chapter> findChapterByString(String xid, String yid);

	List<Chapter> findChapterAllByString(String xid, String yid);

}