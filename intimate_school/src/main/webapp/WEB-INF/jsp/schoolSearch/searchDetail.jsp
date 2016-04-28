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
	<title>学校详情</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	
	<link href="<%=basePath %>static/css/dashboard.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    
     <script type="text/javascript">
		function collectMajorDetail(majorDetailId) {
			var isLoginResult;
			$.ajax({
	            type: "POST",
	            url : "<%=basePath %>ajaxLogin/checkIsLogin.do",
		         	dataType: 'json',
		        	async : false, 
	            success: function(data) {
	            	isLoginResult = data;
	            }
	        });
			
			if(isLoginResult.isLogin == false) {
				alert("登陆，让我知道你是谁~");
				return;
			}
			
			var result;
			$.ajax({
	            type: "POST",
	            url : "<%=basePath %>ajaxCollectionController/addNewCollection.do",
		         	dataType: 'json',
		        	async : false, 
	            data:{"majorDetailId": majorDetailId}, 
	            success: function(data) {
	            	result = data;
	            }
	        });
			if(result.success == true) {
	    		alert("收藏成功");
	    	} else {
	    		alert("收藏失败");
	    	}
			location.reload();
		}    	
    </script>
</head>
<body>
	<jsp:include page="${basePath}/static/common/nav.jsp"/>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right"
			style="margin-top: 15px; top: 10px;">

			<div class="col-xs-12 col-sm-9">
				<form class="form-horizontal" id="submitForm" >
					<div class="form-group">
						<label for="schoolName" class="col-sm-2 control-label">学校名称</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="schoolName" id="schoolName"
								value="${schoolInfo.schoolName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="province" class="col-sm-2 control-label">省市</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="province" id="province"
								value="${schoolInfo.province }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="area" class="col-sm-2 control-label">地区</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="area" id="area"
								value="${schoolInfo.area }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="schoolProperty" class="col-sm-2 control-label">属性</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="schoolProperty" id="schoolProperty"
								value="${schoolInfo.schoolProperty }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="schoolType" class="col-sm-2 control-label">类型</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="schoolType" id="schoolType"
								value="${schoolInfo.schoolType }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="schoolCharacter" class="col-sm-2 control-label">性质</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="schoolCharacter" id="schoolCharacter"
								value="${schoolInfo.schoolCharacter }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="schoolRank" class="col-sm-2 control-label">排名</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="schoolRank" id="schoolRank"
								value="${schoolInfo.schoolRank }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="schoolTel" class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="schoolTel" id="schoolTel"
								value="${schoolInfo.schoolTel }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="schoolIntroduction" class="col-sm-2 control-label">学校介绍</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="5"
							name="schoolIntroduction" id="schoolIntroduction" disabled>${schoolInfo.schoolIntroduction}</textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
		<hr>
		
		<table class="table table-hover">
			<tr>
				<th>专业名称</th>
				<th>招生人数</th>
				<th>报名人数</th>
				<th>录取人数</th>
				<th>操作</th>
			</tr>
			<c:if test="${detailVoList!=null}">
				<c:forEach var="item" items="${detailVoList }">
					<tr>
						<td>${item.majorName }</td>
						<td>${item.enrollmentNo }</td>
						<td>${item.participantNo }</td>
						<td>${item.offerNo }</td>
						<td>
							<button type="button" class="btn btn-info">
								<a data-toggle="modal" href="<%=basePath %>majorSearch/queryPassingScore.do?majorDetailId=${item.majorDetailId }" data-target="#passingScoreModal">分数线</a>
							</button>
							<c:if test="${item.collect == false}">
								<button type="button" class="btn btn-warning" onclick="javascript:collectMajorDetail(${item.majorDetailId })">收藏</button>
							</c:if>
							<c:if test="${item.collect == true}">
								<button type="button" class="btn btn-default" >已收藏</button>
							</c:if>
						</td>
					</tr>
				
				</c:forEach>
			
			</c:if>
			
		</table>
	</div>
	
<div class="modal fade" id="passingScoreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    </div>
  </div>
</div>


</body>
</html>