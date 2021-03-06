<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>专业详情</title>
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
	            url : "<%=basePath %>ajaxCollection/addNewCollection.do",
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
	<jsp:include page="${basePath}/static/common/nav.jsp" />

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right"
			style="margin-top: 15px; top: 10px;">

			<div class="col-xs-12 col-sm-9">
				<form class="form-horizontal" id="submitForm">
					<div class="form-group">
						<label for="majorName" class="col-sm-2 control-label">专业名称</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorName"
								id="majorName" value="${majorInfo.majorName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorClass" class="col-sm-2 control-label">专业门类</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorClass"
								id="majorClass" value="${majorInfo.majorClass }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorType" class="col-sm-2 control-label">专业类型</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorType"
								id="majorType" value="${majorInfo.majorType }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorIntroduction" class="col-sm-2 control-label">专业介绍</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="5" name="majorIntroduction"
								id="majorIntroduction" disabled>${majorInfo.majorIntroduction}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="majorDirection" class="col-sm-2 control-label">专业方向</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="5" name="majorDirection"
								id="majorDirection" disabled>${majorInfo.majorDirection}</textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
		<hr>

		<table class="table table-hover">
			<tr>
				<th>学校名称</th>
				<th>招生人数</th>
				<th>报名人数</th>
				<th>录取人数</th>
				<th>操作</th>
			</tr>
			<c:if test="${detailVoList!=null}">
				<c:forEach var="item" items="${detailVoList }">
					<tr>
						<td>${item.schoolName }</td>
						<td>${item.enrollmentNo }</td>
						<td>${item.participantNo }</td>
						<td>${item.offerNo }</td>
						<td><a data-toggle="modal"
							href="<%=basePath %>majorSearch/queryPassingScore.do?majorDetailId=${item.majorDetailId }"
							data-target="#passingScoreModal" class="btn btn-primary"
							role="button">分数线</a> <a
							href="<%=basePath %>majorDetail/toMajorDetailView.do?majorDetailId=${item.majorDetailId }"
							class="btn btn-success" role="button">详情</a> <c:if
								test="${item.collect == false}">
								<button type="button" class="btn btn-warning"
									onclick="javascript:collectMajorDetail(${item.majorDetailId })">收藏</button>
							</c:if> <c:if test="${item.collect == true}">
								<button type="button" class="btn btn-default">已收藏</button>
							</c:if> <c:if test="${isManager == 1 }">
								<a
									href="<%=basePath %>admin/toUpdateMajorDetail.do?majorDetailId=${item.majorDetailId }"
									class="btn btn-info" role="button">管理员编辑</a>
							</c:if></td>
					</tr>

				</c:forEach>

			</c:if>

		</table>
	</div>

	<div class="modal fade" id="passingScoreModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content"></div>
		</div>
	</div>


</body>
</html>