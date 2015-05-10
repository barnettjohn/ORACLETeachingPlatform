<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


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

    </head>
    <body>
    	
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
				 <div class="control-group normal_text"> <h3><img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" /></h3></div>
               
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input name="pid" type="text" placeholder="学号" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="password" type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                
                <div class="form-actions">
                    <span class="pull-left"><a href="${pageContext.request.contextPath}/servlet/RegisterUIServlet" class="btn btn-info" id="to-login">注册</a></span>
                    <span class="pull-right"><a type="submit" href="javascript:void(0)" 
                    onclick="document.getElementById('loginform').submit();"class="btn btn-success" /> 登陆</a></span>
                </div>
                <div class="control-group"><h1 style="color:red">${message}</div>
            </form>
            
            
            
            <form id="recoverform" action="${pageContext.request.contextPath}/servlet/RegisterUIServlet" class="form-vertical">
				<!-- <p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				 --><p class="normal_text">输入更改密码的必要信息</p>
				
<!--                     <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
                -->
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" placeholder="新密码" />
                        </div>
                    </div>
                </div>
                
                <!-- 
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="email" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lb"><i class="icon-qrcode"></i></span><input type="text" placeholder="班级" />
                        </div>
                    </div>
                </div> -->
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登陆界面</a></span>
                    <span class="pull-right"><a href="javascript:void(0)" onclick="document.getElementById('recoverform').submit();" class="btn btn-info"/>提交</a></span>
                </div>
            </form>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>  
        <script src="${pageContext.request.contextPath}/js/matrix.login.js"></script> 
    </body>
</html>
