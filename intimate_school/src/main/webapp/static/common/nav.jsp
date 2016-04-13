<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>知心院校库</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath %>static/css/main.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.7.2.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    	function jumptoSignUp() {
    		window.location.href="<%=basePath %>user/signup.do";
    	}
    </script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Intimate School Choice</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" placeholder="用户名" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="密码" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">登陆</button>
            <button type="button" class="btn btn-default" id="signupButton" onclick="javascript:jumptoSignUp()">注册</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
 </body>