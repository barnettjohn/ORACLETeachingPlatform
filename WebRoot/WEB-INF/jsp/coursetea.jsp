<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>ORACLE教学平台</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/matrix-style.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/matrix-media.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/colorpicker.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/datepicker.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/uniform.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/select2.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-wysihtml5.css" />

<link
	href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.gritter.css" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
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
					href="${pageContext.request.contextPath}/servlet/CourseUIServlet"
					class="current">课程设置</a>
			</div>
			<h1 style="font-family: Microsoft Yahei">课程设置</h1>
		</div>
		<div class="container-fluid">
			<hr>
			
			<div class="row-fluid">
				<div class="span12">
				<!-- <div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-align-justify"></i>
						</span>
						<h5>上传下载</h5>
					</div>
					<div class="widget-content nopadding">
						<div class="control-group">
							<div class="modal hide fade" id="modal-add-event">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<h3>Add a new event</h3>
								</div>
								<div class="modal-body">
									<p>Enter event name:</p>
									<p>
										<input id="event-name" type="text" />
									</p>
								</div>
								<div class="modal-footer">
									<a href="#" class="btn" data-dismiss="modal">Cancel</a> <a
										href="#" id="add-event-submit" class="btn btn-primary">Add
										event</a>
								</div>
							</div>
						</div>
					</div>
				</div> -->
					${message}
					<c:forEach var="course" items="${clist}">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon"> <i class="icon-pencil"></i>
								</span>
								<h5>您的课程信息</h5>
							</div>
							<div class="widget-content nopadding">
								<form name="form-wizard" id="form-wizard"
									class="form-horizontal" method="post"
									action="${pageContext.request.contextPath}/servlet/UpdateCourserServlet">
									<div id="form-wizard-1" class="step">
										<div class="control-group">
											<label class="control-label" style="color:red">课程名:</label> <label
												class="control-label" style="text-align:center;color:red">${course.cname}</label>
										</div>
										<div class="control-group">
											<label class="control-label">章节数:</label> <label
												class="control-label" style="text-align:center">${course.chnum}</label>
										</div>
										<div class="control-group">
											<label class="control-label">课程描述:</label> <label
												class="control-label" style="text-align:center">${course.detail}</label>
										</div>
										<div class="control-group"></div>

									</div>
									<div id="form-wizard-2" class="step">
										<input type="hidden" id="cid" name="cid" value="${course.cid}">
										<input type="hidden" id="pid" name="pid" value="${course.pid}">
										<div class="control-group">
											<label class="control-label" style="color:red">课程名:</label>
											<div class="controls">
												<input type="text" id="cname" name="cname"
													value="${course.cname}" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">章节数:</label>
											<div class="controls">
												<input type="text" id="chnum" name="chnum" class="number"
													value="${course.chnum}" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">课程描述:</label>
											<div class="controls">
												<input type="text" id="detail" name="detail"
													value="${course.detail}" />
											</div>
										</div>
									</div>
									<div class="form-actions">
										<input id="back" class="btn btn-primary" type="reset"
											value="Back" /> <input id="next" class="btn btn-primary"
											type="submit" value="Next" />
										<%--  <label class="control-label" >${message}</label> --%>
										<div id="status"></div>
									</div>
									<div id="submitted"></div>
								</form>
							</div>
						</div>


						<div class="widget-box collapsible">
							<div class="widget-title">
							<a href="#collapse${course.cid}" data-toggle="collapse">
								<span class="icon"> <i class="icon-align-justify"></i>
								</span>
								<h5>章节信息列表</h5>
							</a>
							</div>
							<div class="collapse in" id="collapse${course.cid}">
							<div class="widget-content">
								<form
									action="${pageContext.request.contextPath}/servlet/UpdateChapterServlet"
									method="post" class="form-horizontal">
									<table class="table table-bordered table-striped">
										<thead>
											<tr>
												<th>章节名</th>
												<th>章节人数（难以程度）</th>
												<th>章节备注</th>
												
											</tr>
										</thead>
										<tbody>
											<c:if test="${chapterlist!=null}">
												<c:forEach items="${chapterlist}" var="chapter">
													<c:if test="${chapter.cid==course.cid}">
														<tr class="odd gradeX">
															<input type="hidden" id="chaptid_${chapter.chaptid}"
																name="chaptid_${chapter.chaptid}"
																value="${chapter.chaptid}" />
															<input type="hidden" id="cid_${chapter.chaptid}"
																name="cid_${chapter.chaptid}" value="${chapter.cid}" />
															<td style="text-align:center;"><input type="text"
																id="chaptna_${chapter.chaptid}"
																name="chaptna_${chapter.chaptid}"
																value="${chapter.chaptna}" /></td>
															<td style="text-align:center;"><input type="text"
																id="difficulty_${chapter.chaptid}"
																name="difficulty_${chapter.chaptid}"
																value="${chapter.difficulty}" /></td>
															
															<td style="text-align:center;"><input type="text"
																id="detial_${chapter.chaptid}"
																name="detial_${chapter.chaptid}"
																value="${chapter.detial}" /></td>
														</tr>
													</c:if>
												</c:forEach>
											</c:if>
											<%-- <c:if test="${chapterlist==null}">
							              		<c:forEach begin="0" end="${course.chnum-1}" var="num" >
									                <tr class="odd gradeX">
									                  <input type="hidden" id="chaptid_${num}" name="chaptid_${num}" value="${course.cid}${num}"/>	
									                  <input type="hidden" id="cid_${num}" name="cid_${num}" value="${course.cid}"/>
									                  <td  style = "text-align:center;"><input class="required" type="text" id="chaptna_${num}" name="chaptna_${num}" /></td>
									                  <td  style = "text-align:center;"><input type="text" id="detial_${num}" name="detial_${num}" /></td>
									                </tr>
							              		</c:forEach>
							              	</c:if> --%>
										</tbody>
									</table>

									<div class="form-actions">
										<button type="submit" class="btn btn-primary">保存</button>
									</div>
								</form>
							</div>
							</div>
						</div>
					</c:forEach>

					<!-- <div class="widget-box collapsible">
						<div class="widget-title">
							<a href="#collapsea" data-toggle="collapse"> <span
								class="icon"><i class="icon-arrow-right"></i></span>
								<h5>Toggle, Open by default</h5>
							</a>
						</div>
						<div class="collapse in" id="collapsea">
							<div class="widget-content">This box is opened by default</div>
						</div>
						<div class="widget-title">
							<a href="#collapseb" data-toggle="collapse"> <span
								class="icon"><i class="icon-remove"></i></span>
								<h5>Toggle, closed by default</h5>
							</a>
						</div>
						<div class="collapse" id="collapseb">
							<div class="widget-content">This box is now open</div>
						</div>
						<div class="widget-title">
							<a href="#collapsec" data-toggle="collapse"> <span
								class="icon"><i class="icon-remove"></i></span>
								<h5>Toggle, closed by default</h5>
							</a>
						</div>
						<div class="collapse" id="collapsec">
							<div class="widget-content">This box is now open</div>
						</div>
					</div>
 -->
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
		<!--管理整个框架的js  -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script	src="${pageContext.request.contextPath}/js/jquery.ui.custom.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.wizard.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/bootstrap-colorpicker.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/jquery.toggle.buttons.html"></script>
		<script src="${pageContext.request.contextPath}/js/masked.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/select2.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/matrix.js"></script>
		<script src="${pageContext.request.contextPath}/js/matrix.wizard.js"></script>
		<script src="${pageContext.request.contextPath}/js/matrix.tables.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/matrix.form_common.js"></script>
		<script src="${pageContext.request.contextPath}/js/wysihtml5-0.3.0.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/jquery.peity.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/bootstrap-wysihtml5.js"></script>

		<script>
			$('.textarea_editor').wysihtml5();
		</script>
</body>
</html>
