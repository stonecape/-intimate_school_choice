<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
    <%-- <script src="<%=basePath %>static/js/bootstrap.min.js"></script> --%>
    
    <script type="text/javascript">
    	function jumptoSignUp() {
    		window.location.href="<%=basePath %>user/signup.do";
    	}
    	
    	function jumptoLogout() {
    		window.location.href="<%=basePath %>login/doLogout.do";
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
          <a class="navbar-brand" href="<%=basePath %>">Intimate School Choice</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <c:choose>
     	  <c:when test="${sessionScope.loginUser == null}">
	          <form class="navbar-form navbar-right" id="signInForm" method="post" onsubmit="return checkForm()" action="<%=basePath %>login/doLogin.do">
	            <div class="form-group">
	              <input type="text" id="inputUserName" name="userName" placeholder="用户名" class="form-control">
	            </div>
	            <div class="form-group">
	              <input type="password" id="inputPassword" name="password" placeholder="密码" class="form-control">
	            </div>
	            <button type="submit" class="btn btn-success" id="submitForm">登陆 </button>
	            <button type="button" class="btn btn-default" id="signupButton" onclick="javascript:jumptoSignUp()">注册</button>
	          </form>
          </c:when>
           <c:otherwise>
           		<a class="navbar-brand navbar-right" href="#">欢迎你 ${sessionScope.loginUser.realName} <button type="button" class="btn btn-default" id="logout" onclick="javascript:jumptoLogout()">登出 </button></a>
           		
           </c:otherwise>
        </c:choose>
          
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
 </body>