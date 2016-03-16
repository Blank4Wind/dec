<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body>
        <section class="content-header">
          <h1>
                角色管理        
            <small>角色修改</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          <div class="main_content_wrap">
	          <form action="${path}/sys/role_sureUpdate.action?id=${roleEntity.id}" method="post" autocomplete="off" id="infoForm">
	          		<div class="row">
					<div class="col-md-1"><span class="title"><b>*</b>角色名称：</span></div>
					  <div class="col-md-11">
					  	<input type="text" class="form-control" name="roleEntity.name" value="${roleEntity.name }">
					  </div>
					</div>
		          	<div class="row">
		           	  <div class="col-md-1"><span class="title"><b>*</b>状       态：</span></div>
					  <div class="col-md-11">
					  	<select class="form-control" name="roleEntity.state">
					  		<s:if test="roleEntity.state == 1">
					  			<option value="1" selected="selected">有效</option>
					  			<option value="2">无效</option>
					  		</s:if>
					  		<s:if test="roleEntity.state == 2">
					  			<option value="1">有效</option>
					  			<option value="2" selected="selected">无效</option>
					  		</s:if>
					  	</select>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-1"><span class="title">  备       注：</span></div>
					  <div class="col-md-11">
					  	<textarea class="form-control" id="memo" name="roleEntity.memo">${roleEntity.memo }</textarea>
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
    <script src="${path}/static/sys/role/js/update_role.js"></script>
	</body>
	${error}
</html>