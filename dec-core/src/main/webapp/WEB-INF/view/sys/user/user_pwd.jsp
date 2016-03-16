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
                                       密码管理        
            <small>密码修改</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
              <form action="${path}/common/common_updatePwd.action" target="_parent" method="post" id="infoForm" autocomplete="off">
	          	<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b> 原  密  码：</span></div>
				  <div class="col-md-11">
				  	<input type="password" class="form-control" name="oldpwd">
				  </div>
				</div>
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b> 新  密  码：</span></div>
				  <div class="col-md-11">
				  	 <input type="password" class="form-control" name="newpwd">
				  </div>
				</div>
				<div class="row">
          		  <div class="col-md-1"><span class="title"><b>*</b> 确认密码：</span></div>
  				  <div class="col-md-11">
  					 <input type="password" class="form-control" name="newpwd2">
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
    <script src="${path}/static/sys/user/js/user_pwd.js"></script>
	</body>
	${error}
</html>