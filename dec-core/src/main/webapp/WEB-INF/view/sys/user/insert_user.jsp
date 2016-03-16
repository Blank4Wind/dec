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
            <small>新增用户</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
          	<s:form action="user!insertUser" method="post" autocomplete="off" id="infoForm">
          		<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>帐       号：</span></div>
				  <div class="col-md-11">
				  <s:textfield cssClass="form-control" name="user.code"></s:textfield>
				  </div>
				</div>
	          	<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>姓       名：</span></div>
				  <div class="col-md-11">
				    <s:textfield cssClass="form-control" name="user.name"></s:textfield>
				  </div>
				</div>
					<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>性       别：</span></div>
				  <div class="col-md-11">
				   <s:select list="#{'1':'男','2':'女'}" cssClass="form-control" name="user.sex"></s:select>
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>电       话：</span></div>
				  <div class="col-md-11">
				  	 <s:textfield cssClass="form-control" name="user.phone"></s:textfield>
				  </div>
				</div>
					<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>邮       箱：</span></div>
				  <div class="col-md-11">
				  	 <s:textfield cssClass="form-control" name="user.email"></s:textfield>
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>所属角色：</span></div>
				  <div class="col-md-11">
				  	   <s:select list="roleList" cssClass="form-control" name="roleId" listKey="id" listValue="name" id="select_one"/>
				  </div>
				</div>
				<div class="row" id="class_id" style="display:none;">
				  <div class="col-md-1"><span class="title">  所属班级：</span></div>
				  <div class="col-md-11">
				  	<s:select name="clazzId" list="classList" listKey="id" listValue="name" cssClass="form-control"></s:select>
				  </div>
				</div>
					
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  备       注：</span></div>
				  <div class="col-md-11">
				  <s:textarea cssClass="form-control" cssname="user.address" name="user.memo" id="memo"></s:textarea>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"></div>
				  <div class="col-md-11">
				  	<s:submit value="添加" cssClass="btn btn-primary btn-sm" id="btn"></s:submit>
				  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
				  </div>
	           	</div>
	        </s:form>
	        </div>
          </div>
        </section>
        </div>
        <script type="text/javascript" src="${path}/static/sys/user/js/insert_user.js"></script>
	</body>
	${error}
</html>