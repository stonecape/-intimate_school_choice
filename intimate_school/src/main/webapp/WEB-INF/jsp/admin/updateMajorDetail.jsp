<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>维护专业数据</title>
<link href="<%=basePath%>static/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>static/css/bootstrap-table.css"
	rel="stylesheet">

<link href="<%=basePath%>static/css/dashboard.css" rel="stylesheet">

<script src="<%=basePath%>static/js/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/js/bootstrap-table.js"></script>

<script type="text/javascript">
    	$(document).ready(function(){
    	  $("#confirmSubmit").click(function(){
    	    if(checkForm()) {
    	    	var result;
    	    	$.ajax({
    	            type: "POST",
    	            url : "<%=basePath%>ajaxAdmin/updateMajorDetail.do",
					dataType : 'json',
					async : false,
					data : $('#submitForm').serialize(),// 要提交的表单 
					success : function(data) {
						result = data;
					}
				});
				if (result.success == true) {
					alert("修改成功");
				} else {
					alert(result.msg);
				}
			}
		});
	});

	function checkForm() {
		var enrollmentNo = $("#enrollmentNo").val();
		var participantNo = $("#participantNo").val();
		var offerNo = $("#offerNo").val();
		var lesson1Name = $("#lesson1Name").val();
		var lesson2Name = $("#lesson2Name").val();
		var lesson3Name = $("#lesson3Name").val();
		var lesson4Name = $("#lesson4Name").val();

		if (enrollmentNo.length < 1 || participantNo.lengh < 1
				|| offerNo.length < 1 || lesson1Name.length < 1
				|| lesson2Name.length < 1 || lesson3Name.length < 1
				|| lesson4Name.length < 1) {
			alert("参数不完整");
			return false;
		}

		var flag = 1;
		$("#passingScoreTable :input").each(function() {
			if ($(this).val().length < 1) {
				flag = 0;
			}
		});

		if (flag == 0) {
			alert("分数线表格不完整");
			return false;
		}

		return true;

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
						<label for="schoolId" class="col-sm-2 control-label">学校</label>
						<div class="col-sm-5">
							<input class="form-control" type="text" 
								value="${majorDetailVo.schoolName }" disabled>
						</div>
					</div>
					<div class="form-group">
						<label for="majorId" class="col-sm-2 control-label">专业</label>
						<div class="col-sm-5">
							<input class="form-control" type="text"
								value="${majorDetailVo.majorName }" disabled>
						</div>
					</div>

					<div class="form-group">
						<label for="enrollmentNo" class="col-sm-2 control-label">招生人数</label>
						<div class="col-sm-5">
							<input type="number" min="0" class="form-control"
								name="enrollmentNo" id="enrollmentNo"
								value="${majorDetailVo.enrollmentNo }">
						</div>
					</div>

					<div class="form-group">
						<label for="participantNo" class="col-sm-2 control-label">报名人数</label>
						<div class="col-sm-5">
							<input type="number" min="0" class="form-control"
								name="participantNo" id="participantNo"
								value="${majorDetailVo.participantNo }">
						</div>
					</div>

					<div class="form-group">
						<label for="offerNo" class="col-sm-2 control-label">录取人数</label>
						<div class="col-sm-5">
							<input type="number" min="0" class="form-control" name="offerNo"
								id="offerNo" value="${majorDetailVo.offerNo }">
						</div>
					</div>

					<div class="form-group">
						<label for="lesson1Name" class="col-sm-2 control-label">科目1名称</label>
						<div class="col-sm-5">
							<input type="text" min="0" class="form-control"
								name="lesson1Name" id="lesson1Name"
								value="${majorDetail.lesson1Name }">
						</div>
					</div>

					<div class="form-group">
						<label for="lesson2Name" class="col-sm-2 control-label">科目2名称</label>
						<div class="col-sm-5">
							<input type="text" min="0" class="form-control"
								name="lesson2Name" id="lesson2Name"
								value="${majorDetail.lesson2Name }">
						</div>
					</div>

					<div class="form-group">
						<label for="lesson3Name" class="col-sm-2 control-label">科目3名称</label>
						<div class="col-sm-5">
							<input type="text" min="0" class="form-control"
								name="lesson3Name" id="lesson3Name"
								value="${majorDetail.lesson3Name }">
						</div>
					</div>

					<div class="form-group">
						<label for="lesson4Name" class="col-sm-2 control-label">科目2名称</label>
						<div class="col-sm-5">
							<input type="text" min="0" class="form-control"
								name="lesson4Name" id="lesson4Name"
								value="${majorDetail.lesson4Name }">
						</div>
					</div>

					<table class="table" id="passingScoreTable">
						<tr>
							<td><input type="month" class="form-control" id="year1"
								name="year1" value="${passingScoreList[0].year }-12"></td>
							<td><input type="number" min="0" class="form-control"
								name="totalPassingScore1" id="totalPassingScore1"
								value="${passingScoreList[0].totalPassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson1PassingScore1" id="lesson1PassingScore1"
								value="${passingScoreList[0].lesson1PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson2PassingScore1" id="lesson2PassingScore1"
								value="${passingScoreList[0].lesson2PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson3PassingScore1" id="lesson3PassingScore1"
								value="${passingScoreList[0].lesson3PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson4PassingScore1" id="lesson4PassingScore1"
								value="${passingScoreList[0].lesson4PassingScore }"></td>
						</tr>
						<tr>
							<td><input type="month" class="form-control" id="year2"
								name="year2" value="${passingScoreList[1].year }-12"></td>
							<td><input type="number" min="0" class="form-control"
								name="totalPassingScore2" id="totalPassingScore2"
								value="${passingScoreList[1].totalPassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson1PassingScore2" id="lesson1PassingScore2"
								value="${passingScoreList[1].lesson1PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson2PassingScore2" id="lesson2PassingScore2"
								value="${passingScoreList[1].lesson2PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson3PassingScore2" id="lesson3PassingScore2"
								value="${passingScoreList[1].lesson3PassingScore }""></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson4PassingScore2" id="lesson4PassingScore2"
								value="${passingScoreList[1].lesson4PassingScore }"></td>
						</tr>
						<tr>
							<td><input type="month" class="form-control" id="year3"
								name="year3" value="${passingScoreList[2].year }-12"></td>
							<td><input type="number" min="0" class="form-control"
								name="totalPassingScore3" id="totalPassingScore3"
								value="${passingScoreList[2].totalPassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson1PassingScore3" id="lesson1PassingScore3"
								value="${passingScoreList[2].lesson1PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson2PassingScore3" id="lesson2PassingScore3"
								value="${passingScoreList[2].lesson2PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson3PassingScore3" id="lesson3PassingScore3"
								value="${passingScoreList[2].lesson3PassingScore }"></td>
							<td><input type="number" min="0" class="form-control"
								name="lesson4PassingScore3" id="lesson4PassingScore3"
								value="${passingScoreList[2].lesson4PassingScore }"></td>
						</tr>
					</table>
					<input type="hidden"  class="form-control" id="majorDetailId"
								name="majorDetailId" value="${majorDetailVo.majorDetailId }">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" id="confirmSubmit" class="btn btn-primary">确认修改</button>
						</div>
					</div>
				</form>
			</div>
			<!--/.col-xs-12.col-sm-9-->

			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<div class="list-group">
					<a href="<%=basePath %>admin/toAddNewMajorDetail.do" class="list-group-item">添加专业</a> 
					<a href="#"	class="list-group-item active">查看/编辑专业</a>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->
		</div>
		<!--/row-->

		<hr>

	</div>

</body>
</html>