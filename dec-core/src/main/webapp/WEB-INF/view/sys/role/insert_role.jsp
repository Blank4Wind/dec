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
            <small>新增角色</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
	          	<form action="${path}/sys/role_addRole.action" method="post" autocomplete="off" id="infoForm">
		          	<div class="row">
					  <div class="col-md-1"><b>*</b><span class="title">角色名称：</span></div>
					  <div class="col-md-11">
					  	<input type="text" class="form-control" name="roleEntity.name">
					  </div>
					</div>
		           	<div class="row">
		           	  <div class="col-md-1"><span class="title"> 备        注：</span></div>
					  <div class="col-md-11">
					  	<textarea class="form-control" id="memo" name="roleEntity.memo"></textarea>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-1"></div>
					  <div class="col-md-11">
					  	<input type="submit" value="新增" class="btn btn-primary btn-sm" id="btn">
					  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
					  </div>
		           	</div>
		         </form>
		      </div>
          </div>
        </section>
     <script src="${path}/static/sys/role/js/insert_role.js"></script>
	</body>
	${error}
</html>