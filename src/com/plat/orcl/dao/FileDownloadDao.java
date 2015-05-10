package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.FileDownload;

public interface FileDownloadDao {

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#addFileDownload(com.plat.orcl.domain.FileDownload)
	 */
	void addFileDownload(FileDownload fd);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#deleteFileDownload(java.lang.String, java.lang.String)
	 */
	void deleteFileDownload(String fdid);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#updateFileDownload(com.plat.orcl.domain.FileDownload)
	 */
	void updateFileDownload(FileDownload fd);

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#findAll()
	 */
	List<FileDownload> findAll();

	/* (non-Javadoc)
	 * @see com.plat.orcl.dao.impl.FileDownloadDao#findFileDownloadcateById(java.lang.String, java.lang.String)
	 */
	FileDownload findFileDownById(String fdid);

	List<FileDownload> findFileDownloadByString(String xid, String yid);

	void deleteFileDownloadByString(String xid, String yid);

	FileDownload findFileDownAllById(String fdid);

	List<FileDownload> findFileDownloadAllByString(String xid, String yid);

}