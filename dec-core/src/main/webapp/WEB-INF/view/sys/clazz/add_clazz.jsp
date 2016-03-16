<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body>
        <section class="content-header">
          <h1>
                             班级管理
            <small>添加班级</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
				<form  action="${path}/sys/clazz_addClass.action" method="post" id="infoForm" autocomplete="off">
					<div class="row">
					  <div class="col-md-1"><b>*</b><span class="title">班级名称：</span></div>
					  <div class="col-md-11">
					  	<s:textfield name="classEntity.name" id="account"  class="form-control"></s:textfield>
					  </div>
					</div>
					
					<div class="row">
					  <div class="col-md-1"><b>*</b><span class="title">选择课程：</span></div>
					  <div class="col-md-11">
					  	<s:select name="courseEntity.name"  class="form-control" list="listClazzName"></s:select>
					  </div>
					</div>
					
					<div class="row">
					  <div class="col-md-1"><span class="title">  备注信息：</span></div>
					  <div class="col-md-11">
					  	<s:textarea name="classEntity.memo" id="memo" class="form-control"></s:textarea>
					  </div>
					</div>
					
					<div class="row">
					  <div class="col-md-1"></div>
					  <div class="col-md-11">
					  	<s:submit value="新增" cssClass="btn btn-primary btn-sm" id="btn"></s:submit>
					  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
					  </div>
					</div>					
				</form>
			 </div>
          </div>
        </section>
	</body>
	<script src="${path}/static/sys/class/js/insert_clazz.js"></script>
	${error}
</html>