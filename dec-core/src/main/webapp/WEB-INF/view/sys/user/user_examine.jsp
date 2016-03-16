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
                              资料管理
            <small>个人资料</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
              <form action="${path}/common/common_updateInfo.action?user.id=${user.id}" method="post" id="infoForm">
	          	<div class="row">
				  <div class="col-md-1"><span class="title">  帐       号：</span></div>
				  <div class="col-md-11">
				  	<input type="text" name="user.code" value="${user.code}" class="form-control" readonly="readonly">
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title">  姓       名：</span></div>
				  <div class="col-md-11">
				  	 <input type="text" name="user.name" value="${user.name}" class="form-control" readonly="readonly">
				  </div>
				</div>
				<div class="row">
          		  <div class="col-md-1"><span class="title">  性       别：</span></div>
  				  <div class="col-md-11">
  					 <s:if test="#session.user.sex == 1">
  					 	<input type="hidden" name="user.sex" value="1"><span class="title">男</span>
  					 </s:if>
  					 <s:elseif test="#session.user.sex == 2">
  					 	<input type="hidden" name="user.sex" value="2"><span class="title">女</span>
  					 </s:elseif>
  				  </div>	     
	          	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>电       话：</span></div>
				  <div class="col-md-11">
				  	 <input type="text" name="user.phone" value="${user.phone}" class="form-control">
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>邮       箱：</span></div>
				  <div class="col-md-11">
				  	 <input type="text" name="user.email" value="${user.email}" class="form-control">
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  创建时间：</span></div>
				  <div class="col-md-11">
				  	 <input type="text" name="user.createTime" value="<s:date name="#session.user.createTime" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control" readonly="readonly">
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  所属角色：</span></div>
				  <div class="col-md-11">
				  	  <input type="text" name="user.roleName" value="${user.roleName}" class="form-control" readonly="readonly">
				  </div>
	           	</div>
	           	<s:if test="#session.user.roleName == '学员'">
	           		<div class="row">
		           	  <div class="col-md-1"><span class="title">  所属班级：</span></div>
					  <div class="col-md-11">
					  	<input type="text" name="user.clazzName" value="${user.clazzName}" class="form-control" readonly="readonly">
					  </div>
		           	</div>
	           	</s:if>
	           	<div class="row">
	           	  <div class="col-md-1"></div>
				  <div class="col-md-11">
				  	<input type="submit" value="修改" class="btn btn-primary btn-sm">
				  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
				  </div>
	           	</div>
	         </form>
	        </div>
          </div>
        </section>
	</body>
	${success}${error}
</html>