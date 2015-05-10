package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Question;

public interface QuestionDao {

	
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#addQuestion(com.plat.orcl.domain.Question)
	 */
	void addQuestion(Question q);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#deleteQuestionByString(java.lang.String, java.lang.String)
	 */
	void deleteQuestionByString(String xid, String yid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#updateQuestion(com.plat.orcl.domain.Question)
	 */
	void updateQuestion(Question q);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#findAll()
	 */
	List<Question> findAll();

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.QuestionDao#findQuestionByString(java.lang.String, java.lang.String)
	 */
	List<Question> findQuestionByString(String xid, String yid);

}