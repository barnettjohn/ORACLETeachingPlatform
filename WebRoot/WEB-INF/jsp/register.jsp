<%@ page language="java" import="java.util.*"   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html >
  <head>
        <title>ORACLE教学平台</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matrix-login.css" />
        <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
		<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/img/icon.ico">

    </head>
    <body>
        <div id="loginbox">            
            <form id="loginform" action="${pageContext.request.contextPath}/servlet/RegisterServlet" class="form-vertical" method="post">
				<!-- <p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				 --><p class="normal_text">输入注册信息</p>
				
				<div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-user"></i></span><input name="pid" type="text" placeholder="学号" value="${form.pid}" />
                            <div class="message">${form.errors.pid}</div>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input name="pname" type="text" placeholder="姓名" value="${form.pname}" />
                            <div class="message">${form.errors.pname}</div>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="password" type="password" placeholder="密码" value="${form.password}"/>
                            <div class="message">${form.errors.password}</div>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="password2" type="password" placeholder="新密码" value="${form.password2}"/>
                            <div class="message">${form.errors.password2}</div>
                        </div>
                    </div>
                </div>
                
                
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input name="email" type="text" placeholder="email" value="${form.email}"/>
                            <div class="message">${form.errors.email}</div>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-qrcode"></i></span><input name="pclass" type="text" placeholder="班级" value="${form.pclass}"/>
                            <div class="message">${form.errors.pclass}</div>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-info" id="to-login">&laquo; 返回登陆界面</a></span>
					<span class="pull-right"><a type="submit" href="javascript:void(0)" 
					onclick="document.getElementById('loginform').submit();" class="btn btn-success" /> 提交</a></span>
                </div>
            </form>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>  
        <script src="${pageContext.request.contextPath}/js/matrix.login.js"></script> 
    </body>
</html>
