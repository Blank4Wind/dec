<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/WEB-INF/view/common/base.jspf" %>
    <link href="${path}/static/common/css/login.css" rel="stylesheet">
    <link href="${path}/static/common/css/supersized.css" rel="stylesheet">
  </head>
  <body>
    <div class="login-box">
      <div class="login-logo">
        <a href="javascript:void(0);" class="logo">&nbsp;</a>
      </div>
      <div class="login-box-body">
        <p class="login-box-msg" style="font-size:18px;">智益考控系统</p>
        <form action="${path}/common/common_login.action" method="post">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="帐号" name="code" maxlength="50"/>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="密码" name="password" maxlength="32"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback" id="kaptchabox">
            <input type="text" class="form-control" id="kaptcha" placeholder="验证码" name="kaptcha" maxlength="4"/>
            <div class="right_full">
            	<img src="${path}/Kaptcha.jpg" id="kaptchaImg">
            </div>
          </div>
          <div class="row">
            <div class="col-xs-8">    
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox"><span>记住密码</span>
                </label>
              </div>                        
            </div>
            <div class="col-xs-4">
              	<span class="rigth-show"><a href="javascript:void(0);">忘记密码?</a></span>
            </div>
          </div>
       	  <div class="form-group has-feedback">
       		<button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
       	  </div>
        </form>
      </div>
    </div>    
    <script src="${path}/static/common/js/login.js"></script>
    <script src="${path}/static/common/js/supersized.3.2.7.min.js"></script>
    <script src="${path}/static/common/js/supersized-init.js"></script>
  </body>
  ${error}
</html>