<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>页面不存在</title>
		<link href="<%=request.getContextPath() %>/static/common/css/error500.css" rel="stylesheet" type="text/css">
	</head>
	<body bgcolor="#EBECEE">
		<!-----页面外围控制整体布局start------>
		<div id="wrapper">
			<!------中间部分start------>
			<div id="container">
				<div id="title">
					<p>
						友情提示
					</p>
				</div>
				<div id="content">
					很抱歉，服务器内部错误!
				</div>
				<div id="bottom">
					<a href="<%=request.getContextPath()%>/index.jsp">返回</a>
				</div>
			</div>
			<!------中间部分end------>
		</div>
	</body>
</html>