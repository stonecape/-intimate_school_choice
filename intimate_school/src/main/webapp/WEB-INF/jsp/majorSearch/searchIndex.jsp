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
	<title>查专业</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath %>static/css/dashboard.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath %>static/js/bootstrap.min.js"></script>
	<script src="<%=basePath %>static/js/bootstrap-paginator.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var currentPage = $("#currentPage").val();
		var pageCount = $("#totalPages").val();

		var options = {
			bootstrapMajorVersion : 2, //版本
			currentPage : currentPage, //当前页数
			totalPages : pageCount, //总页数
			useBootstrapTooltip:true,
			itemTexts : function(type, page, current) {
				switch (type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
				}
			},
			onPageClicked : function(event, originalEvent, type, page) {
				$("#pageNo").val(page);
				$("#majorSearchForm").submit();
			}
		}
		$('#pageDiv').bootstrapPaginator(options);
		
		
		$("#submitForm").click(function() {
			$("#pageNo").val(0);
		});
	});
</script>
</head>
<body>

	<jsp:include page="${basePath}/static/common/nav.jsp" />
	
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right"
			style="margin-top: 15px; top: 10px;">
			<form class="form-inline"  id="majorSearchForm" method="post" action="<%=basePath %>majorSearch/queryMajorInfo.do">
				<div class="form-group">
					<input type="text"
						class="form-control" id="majorName" name="majorName" placeholder="专业名称" value="${majorName }">
				</div>
				<div class="form-group">
					<div class="col-sm-5">
							<select class="form-control" name="majorClass" id="majorClass">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${allMajorClass}">
									<c:if test="${item==majorClass }">
										<option value="${item }" selected="selected" >${item }</option>
									</c:if>
									<c:if test="${item!=majorClass }">
										<option value="${item }">${item }</option>
									</c:if>
								</c:forEach>
							</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-5">
							<select class="form-control" name="majorType" id="majorType">
								<option value="">--请选择--</option>
								<c:forEach var="item" items="${allMajorType}">
									<c:if test="${item==majorType }">
										<option value="${item }" selected="selected">${item }</option>
									</c:if>
									<c:if test="${item!=majorType }">
										<option value="${item }">${item }</option>
									</c:if>
								</c:forEach>
							</select>
					</div>
				</div>
				<input type="hidden" value="${pageNo }" name="pageNo" id="pageNo"/>
				<button type="submit" class="btn btn-default" id="submitForm">搜索</button>
			</form>
		</div>
		<!--/row-->

		<hr>
		<table class="table">
			<tr>
				<th>名称</th>
				<th>门类</th>
				<th>类型</th>
				<th>操作</th>
			</tr>
			<c:if test="${rows.datas!=null}">
				<c:forEach var="item" items="${rows.datas }">
					<tr>
						<td>${item.majorName }</td>
						<td>${item.majorClass }</td>
						<td>${item.majorType }</td>
						<td><a href="<%=basePath %>majorSearch/queryMajorInfoDetail.do?majorId=${item.majorId }">查看详情</a></td>
					</tr>
				
				</c:forEach>
			
			</c:if>
			
		</table>
		
		<div id="pageDiv"></div>
		
		<input type="hidden" value="${rows.pageNum }" id="currentPage"/>
		<input type="hidden" value="${rows.pageCount }" id="totalPages"/>

	</div>
</body>
</html>