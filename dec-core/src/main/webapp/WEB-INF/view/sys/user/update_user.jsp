<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body>
	  <div style="overflow:auto;">
        <section class="content-header">
          <h1>
                             用户管理
            <small>修改用户</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          <div class="main_content_wrap">
          	<form action="${path}/sys/user_sureUpdate.action?id=${user1.id}" method="post" id="infoForm">
          		<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>帐       号：</span></div>
				  <div class="col-md-11">
				  <s:textfield cssClass="form-control" name="user.code" value="%{user1.code}" readonly="true"></s:textfield>
				  </div>
				</div>
	          	<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>姓       名：</span></div>
				  <div class="col-md-11">
				    <s:textfield cssClass="form-control" name="user.name" value="%{user1.name}"></s:textfield>
				  </div>
				</div>
					<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>性       别：</span></div>
				  <div class="col-md-11">
				   <s:select list="#{'1':'男','2':'女'}" cssClass="form-control" value="%{user1.sex}" name="user.sex"></s:select>
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>电       话：</span></div>
				  <div class="col-md-11">
				  	 <s:textfield cssClass="form-control" value="%{user1.phone}" name="user.phone"></s:textfield>
				  </div>
				</div>
					<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>邮       箱：</span></div>
				  <div class="col-md-11">
				  	 <s:textfield cssClass="form-control" value="%{user1.email}" name="user.email"></s:textfield>
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>所属角色：</span></div>
				  <div class="col-md-11">
				  <select class="form-control" name="roleId" id="select_one">
				  		<s:iterator value="roleList" var="role">
				  			<option value="${role.id}" <c:if test="${user1.roleId == role.id}">selected</c:if> >${role.name}</option>
				  		</s:iterator>
				 </select>
				  </div>
				</div>
				<s:if test="roleName == '学员'">
					<div class="row" id="class_id">
					  <div class="col-md-1"><span class="title">  所属班级：</span></div>
					  <div class="col-md-11">
					  <select class="form-control" name="clazzId">
				  		<s:iterator value="classList" var="clazz">
				  			<option value="${clazz.id}" <c:if test="${user1.classId == clazz.id}">selected</c:if> >${clazz.name}</option>
				  		</s:iterator>
				 	 </select>
					  </div>
					</div>
				</s:if>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  备       注：</span></div>
				  <div class="col-md-11">
				  <s:textarea cssClass="form-control" cssname="user.address" value="%{user1.memo}" name="user.memo" id="memo"></s:textarea>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"></div>
				  <div class="col-md-11">
				  	<s:submit value="修改" cssClass="btn btn-primary btn-sm" id="btn"></s:submit>
				  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
				  </div>
	           	</div>
	        <form>
	        </div>
          </div>
        </section>
        </div>
        <script type="text/javascript" src="${path}/static/sys/user/js/update_user.js"></script>
        </body>
        ${error}
</html>