<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>ORACLE教学平台</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/colorpicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uniform.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/select2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/matrix-style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/matrix-media.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-wysihtml5.css" />
<link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/img/icon.ico">

</head>
<body>

	<!--Header-part-->
	<div id="header">
		<h1>
			<a href="dashboard.html">ORACLE教学平台</a>
		</h1>
	</div>
	<!--close-Header-part-->

	<!--top-Header-menu-->
	<div id="user-nav" class="navbar navbar-inverse">
		<ul class="nav">
			<li class="dropdown" id="profile-messages"><a title="" href="#"
				data-toggle="dropdown" data-target="#profile-messages"
				class="dropdown-toggle"><i class="icon icon-user"></i> <span
					class="text">欢迎${person.pname}老师</span><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a data-toggle="modal" href="#modal-add-event2" ><i
							class="icon-user"></i> 我的简介</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
					<li class="divider"></li>
					<li><a
						href="${pageContext.request.contextPath}/servlet/LoginUIServlet"><i
							class="icon-key"></i> 登出</a></li>
				</ul></li>
			<li class="dropdown" id="menu-messages"><a href="#"
				data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"><i class="icon icon-envelope"></i> <span
					class="text">消息</span> <span class="label label-important">5</span>
					<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a class="sAdd" title="" href="#"><i class="icon-plus"></i>
							新信息</a></li>
					<li class="divider"></li>
					<li><a class="sInbox" title="" href="#"><i
							class="icon-envelope"></i> 收信箱</a></li>
					<li class="divider"></li>
					<li><a class="sOutbox" title="" href="#"><i
							class="icon-arrow-up"></i> 发送信息</a></li>
					<li class="divider"></li>
					<li><a class="sTrash" title="" href="#"><i
							class="icon-trash"></i> 垃圾箱</a></li>
				</ul></li>
			<li class=""><a title="" href="#"><i class="icon icon-cog"></i>
					<span class="text">设置</span></a></li>
			<li class=""><a title=""
				href="${pageContext.request.contextPath}/servlet/LoginUIServlet"><i
					class="icon icon-share-alt"></i> <span class="text">登出</span></a></li>
		</ul>
	</div>

	<div class="modal hide fade" id="modal-add-event2">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>个人简介</h3>
		</div>
		<div class="modal-body  nopadding" >
			<table class="table table-bordered table-striped">
				<tbody>
					<tr class="odd gradeX">
						<td>姓名：</td>
						<td>${person.pname}</td>
						<td rowspan="3"><img width="40" height="40" alt="User"
							src="${pageContext.request.contextPath}/img/demo/av1.jpg">
						</td>
					</tr>
					<tr class="even gradeC">
						<td>班级：</td>
						<td>${person.pclass}</td>
					</tr>
					<tr class="odd gradeA">
						<td>email：</td>
						<td>${person.email}</td>
					</tr>
					<tr class="even gradeA">
						<td>组 别：</td>
						<td colspan="2">${person.group}</td>

					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
	<!--close-top-Header-menu-->

	<!--start-top-serch-->

	<div id="search">
		<input type="text" placeholder="搜索..." />
		<button type="submit" class="tip-bottom" title="Search">
			<i class="icon-search icon-white"></i>
		</button>
	</div>

	<!--close-top-serch-->

	<!--sidebar-menu-->

	<div id="sidebar">
		<a href="#" class="visible-phone"><i class="icon icon-home"></i>
			主页</a>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/servlet/IndexUIServlet"><i
					class="icon icon-home"></i> <span>主页</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/servlet/ResourceUIServlet"><i
					class="icon icon-signal"></i> <span>资源管理</span></a></li>
			<li><a
				href="${pageContext.request.contextPath}/servlet/CourseUIServlet"><i
					class="icon icon-inbox"></i> <span>课程设置</span></a></li>
			<li class="submenu"><a href="#"><i class="icon icon-file"></i> <span>测试模块</span></a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/TestUIServlet?flag=1">题库管理</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/TestUIServlet?flag=2">试卷编写</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/TestUIServlet?flag=3">试卷管理</a></li>
				</ul>
			</li>
			<li class="submenu"><a href="#"><i class="icon icon-file"></i> <span>评分模块</span></a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/ScoreUIServlet?flag=1">设计评分表</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/ScoreUIServlet?flag=2">教师评分</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/ScoreUIServlet?flag=3">分数统计</a></li>
				</ul>
			</li>

			<li class="submenu"><a href="#"><i
					class="icon icon-info-sign"></i> <span>Error</span> <span
					class="label label-important">4</span></a>
				<ul>
					<li><a href="error403.html">Error 403</a></li>
					<li><a href="error404.html">Error 404</a></li>
					<li><a href="error405.html">Error 405</a></li>
					<li><a href="error500.html">Error 500</a></li>
				</ul></li>
			<li class="content"><span>Monthly Bandwidth Transfer</span>
				<div
					class="progress progress-mini progress-danger active progress-striped">
					<div style="width: 77%;" class="bar"></div>
				</div> <span class="percent">77%</span>
				<div class="stat">21419.94 / 14000 MB</div></li>
			<li class="content"><span>Disk Space Usage</span>
				<div class="progress progress-mini active progress-striped">
					<div style="width: 87%;" class="bar"></div>
				</div> <span class="percent">87%</span>
				<div class="stat">604.44 / 4000 MB</div></li>
		</ul>
	</div>
	<!--sidebar-menu-->

	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="${pageContext.request.contextPath}/servlet/IndexUIServlet"
					title="主页" class="tip-bottom"><i class="icon-home"></i> 主页</a> <a
					href="#">测试模块</a><a href="${pageContext.request.contextPath}/servlet/TestUIServlet"
					class="current" >题库管理</a> <a href="#" class="current">题目修改</a>
			</div>
			<h1 style="font-family: Microsoft Yahei">题库管理</h1>
		</div>
		<div class="container-fluid">
			<hr>
			
			<div class="row-fluid">
				<div class="span12">
				
					<div class="widget-box collapsible">
						<div class="widget-title"> <a href="#collapseAdd" data-toggle="collapse"> <span class="icon"><i class="icon-pencil"></i></span>
						  	<h5>修改题目</h5>
						  	</a> </div>
						<div class="collapse in" id="collapseAdd">
						<form action="${pageContext.request.contextPath}/servlet/EditQuestionServlet" 
							class="form-horizontal" method="post">
							<input id="pupid" name="pupid" type="hidden" value="${person.pid}" />
							<input id="qid" name="qid" type="hidden" value="${question.qid}" />
							<div class="control-group">
								<label class="control-label">题目名称：</label>
								<div class="controls">
								  <input id="qname" name="qname" type="text" value="${question.qname}" />
								</div>
							</div>
				            <div class="control-group">
								<label class="control-label">题目内容：</label>
								<div class="controls">
								  <textarea  id="qcontent" name="qcontent" >${question.qcontent}</textarea>
								</div>
				            </div>
				            <div class="control-group">
								<label class="control-label">正确答案：</label>
								<div class="controls">
								  <input id="qright" name="qright" type="text" value="${question.qright}" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">答案解析：</label>
								<div class="controls">
									<textarea  id="qdetail" name="qdetail" > ${question.qdetail}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">所属章节：</label>
								<div class="controls">
									<select id="chaptid" name="chaptid" class="span4"  value="${question.chapter.chaptid} · ${question.chapter.chaptna}">
										<c:forEach items="${chapterlist}" var="chapter">
											<c:if test="${chapter.chaptna!=null}">
												<option>${chapter.chaptid} · ${chapter.chaptna}</option>
											</c:if>
										</c:forEach>
										
									</select>
								</div>
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-success">确认修改</button>
				            </div>
						</form>
						</div>
						
			        </div>
				</div>
			</div>
		</div>
		<!--Footer-part-->
		<div class="row-fluid">
			<div id="footer" class="span12">
				2013 &copy; Matrix Admin. Brought to you by <a
					href="http://themedesigner.in/">Themedesigner.in</a>
			</div>
		</div>
		<!--end-Footer-part-->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script> 
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
		<script src="${pageContext.request.contextPath}/js/jquery.ui.custom.js"></script> 
		<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap-colorpicker.js"></script> 
		<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script> 
		<script src="${pageContext.request.contextPath}/js/jquery.toggle.buttons.html"></script> 
		<script src="${pageContext.request.contextPath}/js/masked.js"></script> 
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script> 
		<%-- <script src="${pageContext.request.contextPath}/js/select2.min.js"></script>  --%>
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script> 
		<script src="${pageContext.request.contextPath}/js/matrix.js"></script> 
		<script src="${pageContext.request.contextPath}/js/matrix.tables.js"></script> 
		<script src="${pageContext.request.contextPath}/js/matrix.form_common.js"></script> 
		<script src="${pageContext.request.contextPath}/js/wysihtml5-0.3.0.js"></script> 
		<script src="${pageContext.request.contextPath}/js/jquery.peity.min.js"></script> 
		<script src="${pageContext.request.contextPath}/js/bootstrap-wysihtml5.js"></script>
		
		<script>
			$('.textarea_editor').wysihtml5();
		</script>
</body>
</html>
