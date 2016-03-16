<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@ include file="/WEB-INF/view/common/base.jspf" %>
  </head>
  <body class="skin-blue layout-boxed fixed">
    <!-- Site wrapper -->
    <div class="wrapper">
      <!-- =================引入头部文件=================== -->
      <%@ include file="/WEB-INF/view/common/header.jspf" %>

      <!-- ===============引入左边侧栏文件================== -->
      <%@ include file="/WEB-INF/view/common/slidebar.jspf" %>


      
      
      <!-- ===============左边侧栏文件 完================== -->
      <!-- =================主体内容显示=================== -->
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
       	<s:if test="#session.user.roleName == '管理员'">
       		<iframe name="tag" scrolling="no" width="100%" id="tag" height="610px" frameborder="0" src="${path}/sys/user_findUserByCondition.action?user2.state=1"></iframe>
       	</s:if>
       	<s:if test="#session.user.roleName == '教师'">
       		<iframe name="tag" scrolling="no" width="100%" id="tag" height="610px" frameborder="0" src="${path}/qes/qestions_qestionList.action?queryParam.state=1"></iframe>
       	</s:if>
       	<s:if test="#session.user.roleName == '学员'">
       		<iframe name="tag" scrolling="no" width="100%" id="tag" height="610px" frameborder="0" src="${path}/stu/stu_getExamPlan.action"></iframe>
       	</s:if>
      </div><!-- /.content-wrapper -->
     </div>
  </body>
</html>