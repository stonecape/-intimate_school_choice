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
	<title>专业详情</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	
	<link href="<%=basePath %>static/css/dashboard.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    
     <script type="text/javascript">
		function collectMajorDetail(majorDetailId) {
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
						<label for="majorName" class="col-sm-2 control-label">专业名称</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorName" id="majorName"
								value="${majorInfo.majorName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorClass" class="col-sm-2 control-label">专业门类</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorName" id="majorClass"
								value="${majorInfo.majorClass }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorType" class="col-sm-2 control-label">专业类型</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorName" id="majorType"
								value="${majorInfo.majorType }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorIntroduction" class="col-sm-2 control-label">专业介绍</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="5"
							name="majorIntroduction" id="majorIntroduction" disabled>${majorInfo.majorIntroduction}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="majorDirection" class="col-sm-2 control-label">专业方向</label>
						<div class="col-sm-5">
							<textarea class="form-control" rows="5"
							name="majorDirection" id="majorDirection" disabled>${majorInfo.majorDirection}</textarea>
						</div>
					</div>
				</form>
				<hr>
				<table class="table">
					<tr>
						<th>科目1</th>
						<th>科目2</th>
						<th>科目3</th>
						<th>科目4</th>
					</tr>
					<tr>
						<td>
							${majorInfo.lesson1Name }
						</td>
						
						<td>
							${majorInfo.lesson2Name }
						</td>
						
						<td>
							${majorInfo.lesson3Name }
						</td>
						
						<td>
							${majorInfo.lesson4Name }
						</td>
					</tr>
				</table>
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
						<td>
							<button type="button" class="btn btn-info">
								<a data-toggle="modal" href="<%=basePath %>majorSearch/queryPassingScore.do?majorDetailId=${item.majorDetailId }" data-target="#passingScoreModal">分数线</a>
							</button>
							<button type="button" class="btn btn-warning" onclick="javascript:collectMajorDetail(${item.majorDetailId })">收藏</button>
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