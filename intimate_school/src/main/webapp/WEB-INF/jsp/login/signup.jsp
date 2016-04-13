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
	<title>注册</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.7.2.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	function checkForm(){
    		var userName = $("#inputUserName").val();
			var realName = $("#inputRealName").val();
			var school = $("#school").val();
			var password = $("#inputPassword").val();
			var repassword = $("#inputRePassword").val();
			
			if(password != repassword) {
				alert("两次输入的密码不一致")
				return false;
			}
			
			if(password.length < 6 || password.length > 12) {
				alert("密码为6-12位")
				return false;
			}
			return true; 
    	}
    	function checkUserName() {
    		var userName = $("#inputUserName").val();
    		var result;
    		if(userName.length > 0) {
    			$.ajax({  
    		        type : "POST",  
    		         url : "<%=basePath %>ajaxUser/checkUserName.do",
    		         dataType: 'json',
    		         data : {"userName":userName },  
    		         async : false,  
    		         success : function(data){
    		        	 result = data;
    		         }  
    		    });
    			if(result.success == false) {
    				alert("用户名查重失败");
    			} else {
    				if(result.isExist == "1") {
    					alert("用户名已存在，不能重复注册");
    				}
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

      <form class="form-signin" id="signupForm" method="post" onsubmit="return checkForm()" action="<%=basePath %>user/addNewUser.do" >
        <h2 class="form-signin-heading">请注册</h2>
        <label for="inputUserName" class="sr-only">用户名</label>
        <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="用户名" 
        	onBlur="javascript:checkUserName()" required autofocus>
        	
        <label for="inputRealName" class="sr-only">真实姓名</label>
        <input type="text" id="inputRealName" name="realName" class="form-control" placeholder="真实姓名" required>
        
        <label for="inputSchool" class="sr-only">所在学校</label>
        <input type="text" id="inputSchool" name="school" class="form-control" placeholder="所在学校" required>
        
        <label for="inputPassword" class="sr-only">密码6-12位</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码6-12位" required>
        
        <label for="inputRePassword" class="sr-only">重复密码6-12位</label>
        <input type="password" id="inputRePassword" name="rePassword" class="form-control" placeholder="重复密码6-12位" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="submitForm">注册</button>
      </form>

    </div>

</body>
</html>