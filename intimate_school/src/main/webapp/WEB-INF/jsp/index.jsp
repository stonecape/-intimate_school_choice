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
	<title>知心院校库</title>
	<link href="<%=basePath %>static/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath %>static/css/main.css" rel="stylesheet">
	
	<script src="<%=basePath %>static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath %>static/js/bootstrap.min.js"></script>
    
</head>
<body>
	<jsp:include page="${basePath}/static/common/nav.jsp"/> 

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>知心院校库</h1>
        <p>全国共有868个研究生招生单位，45893个硕士正在招生。知心院校库可以为每位即将开始备考生涯的学子们提供全面院校专业信息，成为莘莘学子们考研生涯中最知心的指明灯</p>
        
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          <h2>专业搜索</h2>
          <p>专业的选择决定未来的方向，点击这里，收获新的自己 </p>
          <p><a class="btn btn-default" href="<%=basePath %>majorSearch/toMajorSearchView.do" role="button">搜专业 &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>院校搜索</h2>
          <p>院校选择决定发展环境，点击这里，寻找施展自己的天地</p>
          <p><a class="btn btn-default" href="#" role="button">搜学校 &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h2>收藏对比</h2>
          <p>不想错失每一次机会？点击这里，梳理眼花缭乱的自己</p>
          <p><a class="btn btn-default" href="#" role="button">我的收藏 &raquo;</a></p>
        </div>
      </div>

      <hr>

      <footer>
        <p>&copy; 2016 Zhang Juxin All Rights Reserved</p>
      </footer>
    </div> <!-- /container -->

</body>
</html>