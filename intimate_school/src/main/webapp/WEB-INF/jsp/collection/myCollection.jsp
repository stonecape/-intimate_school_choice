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
<title>我的收藏</title>
<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">

<link href="<%=basePath %>static/css/dashboard.css" rel="stylesheet">

<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
<script src="<%=basePath %>static/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function deleteCollection(majorDetailId) {
		$.ajax({  
	        type : "POST",  
	         url : "<%=basePath %>ajaxCollection/delete.do",
	         dataType: 'json',
	         data : {"majorDetailId":majorDetailId},  
	         async : false,  
	         success : function(data){
	        	 if(data.success == false) {
	 				alert("取消关注失败");
	 			} else {
	 				alert("取消关注成功");
	 			}
	        	 location.reload();
	         }  
	    });
	}
</script>
</head>
<body>
	<jsp:include page="${basePath}/static/common/nav.jsp" />

	<div class="container">
		<div class="row">
			<c:if test="${rows!=null}">
				<c:forEach var="item" items="${rows }">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<div class="caption">
								<h3>${item.schoolName }</h3>
								<p>${item.majorName }</p>
								<p>
									<a
										href="<%=basePath %>majorDetail/toMajorDetailView.do?majorDetailId=${item.majorDetailId }"
										class="btn btn-primary" role="button">看详情</a>
									<button class="btn btn-success" role="button" onclick="javascript:deleteCollection(${item.majorDetailId })">取消关注</button>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>