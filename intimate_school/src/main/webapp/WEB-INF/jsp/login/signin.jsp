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
	<title>登陆</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
	    function jumptoSignUp() {
			window.location.href="<%=basePath %>user/signup.do";
		}
	    
    	function checkForm(){
    		var userName = $("#inputUserName").val();
			var password = $("#inputPassword").val();
			
			if(userName.length < 1 || password.length < 1) {
				alert("你的人生不完整")
				return false;
			}
			if(password.length < 6 || password.length > 12) {
				alert("人生的密码为6-12位")
				return false;
			}
			
			$.ajax({  
		        type : "POST",  
		         url : "<%=basePath %>login/identity.do",
		         dataType: 'json',
		         data : {"userName":userName, "password":password },  
		         async : false,  
		         success : function(data){
		        	 result = data;
		         }  
		    });
			
			if(result.success == false) {
				alert("校验失败：" + result.msg);
				return false;
			} else {
				if(result.report == false) {
					alert("用户名/密码不正确");
					return false;
				} else {
					return true;
				}
			}
    	}
    </script>
    
    <style type="text/css">
    	body {
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #eee;
		}
		
		.form-signin {
		  max-width: 330px;
		  padding: 15px;
		  margin: 0 auto;
		}
		.form-signin .form-signin-heading,
		.form-signin .checkbox {
		  margin-bottom: 10px;
		}
		.form-signin .checkbox {
		  font-weight: normal;
		}
		.form-signin .form-control {
		  position: relative;
		  height: auto;
		  -webkit-box-sizing: border-box;
		     -moz-box-sizing: border-box;
		          box-sizing: border-box;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.form-signin input[type="email"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
    </style>
</head>
<body>
	<div class="container">

      <form class="form-signin" id="signInForm" method="post" onsubmit="return checkForm()" action="<%=basePath %>login/doLogin.do" >
        <h2 class="form-signin-heading">请登录</h2>
        <label for="inputUserName" class="sr-only">用户名</label>
        <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="用户名"  required autofocus>
        	
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="submitForm">登录</button>
        <button class="btn btn-lg btn-default btn-block" type="button" id="toSignUp" onclick="javascript:jumptoSignUp()">去注册</button>
      </form>

    </div>

</body>
</html>