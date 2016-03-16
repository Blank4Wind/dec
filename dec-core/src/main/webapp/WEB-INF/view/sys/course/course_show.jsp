<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link rel="stylesheet" href="${path}/static/common/plugins/ztree-v3.5/css/zTreeStyle/zTreeStyle.css">
	</head>
	<body>
        <section class="content-header">
          <h1>
                             课程管理
            <small>课程显示</small>
          </h1>
          <ol class="breadcrumb">
            <a href="${path}/sys/course_toInsertCoursePage.action" class="btn btn-primary btn-sm">新增课程</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="row">
          		<div class="col-md-2" style="border-right: 1px solid #DEDEDE;height: 700px;">
	  				<ul id="treeDemo" class="ztree"></ul>
	  			</div>
  				<div class="col-md-10">
  					<iframe name="dataShow" scrolling="no" id="dataShow" width="100%" height="500" frameborder="0" src="${path}/sys/course_courseData.action?id=c4e634eef81e439e8dae1577804e16e0"></iframe>
  				</div>
          	</div>
          </div>
        </section>
        <script src="${path}/static/common/plugins/ztree-v3.5/js/jquery.ztree.core-3.5.min.js"></script>
        <script>
        	var courseJson = '${courseJson}';
        </script>
        <Script src="${path}/static/sys/course/js/course_show.js"></Script>
	</body>
	${success}
</html>