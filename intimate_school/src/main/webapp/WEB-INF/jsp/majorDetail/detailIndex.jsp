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
	<title>学校-专业详情</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	
	<link href="<%=basePath %>static/css/dashboard.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    
     <script type="text/javascript">
	     $(document).ready(function(){
	   	  $("#confirmSubmit").click(function(){
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
			
			var commentContent = $("#addNewComment").val();
			if(commentContent.length < 1) {
				alert("没有写评论哦~");
				return;
			}
			var isAnonymous;
			if($("#isAnonymous").is(':checked')) {
				isAnonymous = 1;
			} else {
				isAnonymous = 0;
			}
			var majorDetailId = $("#majorDetailId").val();
			
			var result;
	    	$.ajax({
	            type: "POST",
	            url : "<%=basePath %>ajaxMajorDetail/addComment.do",
		         	dataType: 'json',
		        	async : false, 
	            data:{"isAnonymous":isAnonymous, "majorDetailId":majorDetailId, "commentContent":commentContent},
	            success: function(data) {
	            	result = data;
	            }
	        });
	    	if(result.success == true) {
	    		alert("添加成功");
	    		location.reload();
	    	} else {
	    		alert("添加失败");
	    	}
			
	   	  });
	   	});
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
								value="${majorDetailVo.schoolName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorName" class="col-sm-2 control-label">专业名称</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="majorName" id="majorName"
								value="${majorDetailVo.majorName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="enrollmentNo" class="col-sm-2 control-label">招生人数</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="enrollmentNo" id="enrollmentNo"
								value="${majorDetailVo.enrollmentNo }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="participantNo" class="col-sm-2 control-label">报名人数</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="participantNo" id="participantNo"
								value="${majorDetailVo.participantNo }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="offerNo" class="col-sm-2 control-label">录取人数</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" name="offerNo" id="offerNo"
								value="${majorDetailVo.offerNo }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">分数线</label>
						<div class="col-sm-5">
							<a data-toggle="modal" href="<%=basePath %>majorSearch/queryPassingScore.do?majorDetailId=${majorDetailVo.majorDetailId }" data-target="#passingScoreModal" class="btn btn-primary" role="button">分数线</a>
						</div>
					</div>
					
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-7">
								<textarea class="form-control" rows="5" placeholder="新增评论" id="addNewComment"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="checkbox" id="isAnonymous" value="1">匿名?
								<button type="button" id="confirmSubmit" class="btn btn-primary">确认添加</button>
							</div>
						</div>
					
					<c:if test="${commentList!=null}">
						<c:forEach var="item" items="${commentList }">
							<div class="form-group">
								<label class="col-sm-2 control-label">
									<c:if test="${item.isAnonymous == 0}">
										${item.userName} 说：
									</c:if>
									<c:if test="${item.isAnonymous == 1}">
										匿名用户 说：
									</c:if>
								</label>
								<div class="col-sm-7">
									<textarea class="form-control" rows="5"
									disabled>${item.commentContent}</textarea>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</form>
			</div>
		</div>
		<div class="hidden">
			<input id="majorDetailId" value="${majorDetailVo.majorDetailId }" />
		</div>
		
	</div>
	<div class="modal fade" id="passingScoreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    </div>
	  </div>
	</div>
</body>
</html>