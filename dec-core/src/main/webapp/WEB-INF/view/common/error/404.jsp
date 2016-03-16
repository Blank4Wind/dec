<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>对不起,你要访问的页面暂时没有找到。</title>
	</head>
	<style>
		#container{
			width:100%;
			height:100%;
		}
		#content{
			width:1024px;
			height:auto;
			margin:0 auto;
			text-align:center;			
		}
		.back{
			display:block;
			text-decoration: none;
		}
	
	</style>
	<body>
		<div id="container">
			<div id="content">
				对不起，您要访问的页面暂时没有找到。您可以...<br>
				<a href="<%=request.getContextPath()%>/index.jsp" class="back">返回首页</a>
			</div>
		</div>
	</body>
</html>