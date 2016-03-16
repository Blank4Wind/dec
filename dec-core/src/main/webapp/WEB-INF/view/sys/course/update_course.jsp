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
            <small>课程修改</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          <div class="main_content_wrap">
          	<form action="${path}/sys/course_updateCourse.action?courseEntity.id=${courseEntity.id}" method="post"  autocomplete="off" id="infoForm">
	          	<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>课   程    名：</span></div>
				  <div class="col-md-11">
				  	<input type="text" class="form-control"  name="courseEntity.name" value="<s:property value="courseEntity.name"/>">
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title">  父类课程名：</span></div>
				  <div class="col-md-11">
				  	<input type="text" class="form-control" name="parentName" value="${courseEntity.parentName}" id="parentName" readonly="readonly">
				  </div>
				</div>
				<div class="row">
	          		<div class="col-md-1">		
		  			</div>
	  				<div class="col-md-11">
	  					<ul id="treeDemo" class="ztree"></ul>
	  				</div>
	          	</div>
	          	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>状          态：</span></div>
				  <div class="col-md-11">
				  	<select class="form-control" name="courseEntity.state">
				  		<s:if test="courseEntity.state == 1">
				  			<option value="1" selected="selected">有效</option>
				  			<option value="2">无效</option>
				  		</s:if>
				  		<s:if test="courseEntity.state == 2">
				  			<option value="1">有效</option>
				  			<option value="2" selected="selected">无效</option>
				  		</s:if>
				  	</select>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  备          注：</span></div>
				  <div class="col-md-11">
				  	<textarea class="form-control" id="memo" name="courseEntity.memo"><s:property value="courseEntity.memo"/></textarea>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"></div>
				  <div class="col-md-11">
				  	<input type="submit" value="修改" class="btn btn-primary btn-sm" id="btn">
				  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
				  </div>
	           	</div>
	         </form>
	         </div>
          </div>
        </section>
    <script src="${path}/static/common/plugins/ztree-v3.5/js/jquery.ztree.core-3.5.min.js"></script>
	<script>
		var courseJson = '${courseJson}';
	</script>
	<script src="${path}/static/sys/course/js/update_course.js"></script>
	</body>
	${error}
</html>