package com.plat.orcl.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.plat.orcl.domain.Person;
import com.plat.orcl.service.impl.FileServiceImpl;
import com.plat.orcl.utils.PubUtil;
import com.plat.orcl.utils.WebUtil;
import com.plat.orcl.web.formbean.FileForm;


@MultipartConfig(location = "E:\\upload" , maxFileSize = 1024 * 1024 * 10 )
@WebServlet("/servlet/UploadServlet")
public class UploadServlet extends HttpServlet {

	private static final String SAVE_PATH = "E:\\upload";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FileServiceImpl fs = new FileServiceImpl();
		Person p = (Person) request.getSession().getAttribute("person");
		String pState = p.getPstate();
		request.setCharacterEncoding("UTF-8");
		String authority = request.getParameter("date");
		//System.out.println(s);
		System.out.println(request.getContentLength());
		ArrayList<com.plat.orcl.domain.File> l = fs.fileList(pState , p.getPid());
		ArrayList<FileForm> formList = new ArrayList<FileForm>();
		FileForm ff = null;
		for(com.plat.orcl.domain.File f:l){
			ff =new FileForm();
			WebUtil.copyBean(f, ff);
			formList.add(ff);
		}
		request.setAttribute("filelist", formList);
		
		
		
		File f = new File(SAVE_PATH + File.separator);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		Part part = null;
		try {
			part = request.getPart("file1");
			
			if (part == null) {
				this.person2Right(pState, "上传文件出现异常，请检查输入是否有误！", 
						"/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
				return;
				
			}
		} catch (IllegalStateException ise) {
		   // 上传文件超过注解所标注的maxRequestSize或maxFileSize值
			ise.printStackTrace();
			this.person2Right(pState, "超过注解所标注的maxRequestSize或maxFileSize值！", 
					"/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
			return;
		} catch (IOException ieo) {
		   // 在接收数据时出现问题
		   ieo.printStackTrace();
		} catch (Exception e) {
		   e.printStackTrace();
		}
		
		if (part == null) {
			this.person2Right(pState, "上传文件出现异常，请检查输入是否有误！", 
					"/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
			return;
		}
		String h = part.getHeader("content-disposition");
		String filename = h.substring(h.lastIndexOf("=") + 2, h.length() - 1);
		
		if(PubUtil.isEmptyString(filename)){
			this.person2Right(pState, "请选择上传文件！", 
					"/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
			return;
		}
		String fileExName = PubUtil.getExtensionName(filename);
		String fileNoEx = PubUtil.getFileNameNoEx(filename);
		String fileID = fileNoEx+PubUtil.createGUID()+"."+fileExName;
		
		String savePath = SAVE_PATH + File.separator + fileID;
		try {
			part.write(savePath);
		} catch (IOException e) {
			e.printStackTrace();
			this.person2Right(pState, "上传文件出现异常，数据写入硬盘时出现错误！", 
					"/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
			return;
			
		}
		System.out.println("文件已上传服务器");
		com.plat.orcl.domain.File file = new com.plat.orcl.domain.File();
		
		file.setFid(fileID);
		file.setFname(filename);
		file.setUrl(savePath);
		file.setPidup(p.getPid());
		Date a = new Date();
		if(!PubUtil.isEmptyString(authority)){
			try {
					SimpleDateFormat sim=new SimpleDateFormat("yyyy/MM/dd");
					a = sim.parse(authority);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		file.setAuthority(a);
		fs.upload(file);
		System.out.println("文件信息已写入数据库");
		
		ArrayList<FileForm> formList2 = this.getFileFormList(fs, request);
		request.setAttribute("filelist", formList2);
		
		//response.setCharacterEncoding("UTF-8");
		//response.sendRedirect(request.getContextPath()+"/servlet/resourcetea.jsp");
		this.person2Right(pState, "上传成功！", 
				"/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
		return;
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	public ArrayList<FileForm> getFileFormList(FileServiceImpl fs,HttpServletRequest request){
		Person p = (Person) request.getSession().getAttribute("person");
		ArrayList<com.plat.orcl.domain.File> l = fs.fileList(p.getPstate() , p.getPid());
		ArrayList<FileForm> formList = new ArrayList<FileForm>();
		FileForm ff = null;
		for(com.plat.orcl.domain.File f:l){
			ff =new FileForm();
			WebUtil.copyBean(f, ff);
			formList.add(ff);
		}
		return formList;
	}
	
	public void person2Right(String pState,String message,String urltea,String urlstu,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if (pState.equals("s")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urlstu).forward(request, response);
			return;
		}
		else if(pState.equals("t")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urltea).forward(request, response);
			return;
		}else {
			request.setAttribute("message", message);
			request.getRequestDispatcher(urltea).forward(request, response);
			return;
		}
	}
	
	public static void person2Right(String pState,String message,String urltea,String urlstu,String urladmin ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if (pState.equals("s")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urlstu).forward(request, response);
			return;
		}
		else if(pState.equals("t")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urltea).forward(request, response);
			return;
		}else if(pState.equals("a")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urladmin).forward(request, response);
			return;
		}
	}

}
