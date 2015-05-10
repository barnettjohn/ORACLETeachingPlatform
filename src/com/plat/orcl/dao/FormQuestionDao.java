package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.FormQuestion;

public interface FormQuestionDao {

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#addFormQuestion(com.plat.orcl.domain.FormQuestion)
	 */
	void addFormQuestion(FormQuestion tq);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#deleteFormQuestion(java.lang.String, java.lang.String)
	 */
	void deleteFormQuestion(String formqid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#deleteFormQuestionByString(java.lang.String, java.lang.String)
	 */
	void deleteFormQuestionByString(String xid, String yid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findAll()
	 */
	List<FormQuestion> findAll();

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findFormQuestionByString(java.lang.String, java.lang.String)
	 */
	List<FormQuestion> findFormQuestionByString(String xid, String yid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormQuestionDao#findFormQuestionAllByString(java.lang.String, java.lang.String)
	 */
	List<FormQuestion> findFormQuestionAllByString(String xid, String yid);

}