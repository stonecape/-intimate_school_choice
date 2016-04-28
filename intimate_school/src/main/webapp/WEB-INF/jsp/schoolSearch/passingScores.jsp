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
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    
</head>
<body>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">分数线</h4>
      </div>
		<table class="table table-hover">
			<tr>
				<th>年份</th>
				<th>总分数线</th>
				<th>${majorDetail.lesson1Name }</th>
				<th>${majorDetail.lesson2Name }</th>
				<th>${majorDetail.lesson3Name }</th>
				<th>${majorDetail.lesson4Name }</th>
			</tr>
			<c:if test="${rows!=null}">
				<c:forEach var="item" items="${rows }">
					<tr>
						<td>${item.year }</td>
						<td>${item.totalPassingScore }</td>
						<td>${item.lesson1PassingScore }</td>
						<td>${item.lesson2PassingScore }</td>
						<td>${item.lesson3PassingScore }</td>
						<td>${item.lesson4PassingScore }</td>
					</tr>
				
				</c:forEach>
			
			</c:if>
			
		</table>
		<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     	 </div>
	
</body>
</html>