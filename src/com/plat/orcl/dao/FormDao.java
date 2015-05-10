package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.Form;

public interface FormDao {

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#addForm(com.plat.orcl.domain.Form)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#addForm(com.plat.orcl.domain.Form)
	 */
	void addForm(Form f);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#deleteFormByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#deleteFormByString(java.lang.String, java.lang.String)
	 */
	void deleteFormByString(String xid, String yid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#updateForm()
	 */
	void updateForm(Form f);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findAll()
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findAll()
	 */
	List<Form> findAll();

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findFormByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findFormByString(java.lang.String, java.lang.String)
	 */
	List<Form> findFormByString(String xid, String yid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findFormAllByString(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FormDao#findFormAllByString(java.lang.String, java.lang.String)
	 */
	List<Form> findFormAllByString(String xid, String yid);

}