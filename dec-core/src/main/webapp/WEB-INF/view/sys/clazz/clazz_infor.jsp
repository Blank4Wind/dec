<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body>
        <section class="content-header">
          <h1>
                                           班级管理
            <small>新增班级</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          <div class="main_content_wrap">
           	<form action="${path}/sys/clazz_updateClassEntityInfo.action" method="post" id="infoForm"><s:hidden name="classEntity.id"></s:hidden>
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>班级名称：</span></div>
				  <div class="col-md-11">
				  	<s:textfield name="classEntity.name" cssClass="form-control" readonly="true"></s:textfield>
				  </div>
				</div>           		
				
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>状      态：</span></div>
				  <div class="col-md-11">
	           		<s:if test="classEntity.state==1">
		           		<select name="classEntity.state" class="form-control">
		           			<option value="1">启用</option>
		           			<option value="2">禁用</option>
	           			</select>
	           		</s:if>	
	           		<s:else>
		           		<select name="classEntity.state" class="form-control">
		           			<option value="2">禁用</option>	
		           		    <option value="1">启用</option>
	           			</select>		           		    
	           		</s:else>
				  </div>
				</div>            		
           		
				<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>课      程：</span></div>
				  <div class="col-md-11">
				  	<s:select name="classEntity.courseName" list="listClazzName" value="classEntity.courseName" cssClass="form-control"></s:select>
				  </div>
				</div>           		
           		
				<div class="row">
				  <div class="col-md-1"><span class="title">  操 作  者：</span></div>
				  <div class="col-md-11">
				  	<s:textfield name="classEntity.userName" disabled="true" cssClass="form-control"></s:textfield>
				  </div>
				</div>             		
           		
				<div class="row">
				  <div class="col-md-1"><span class="title">  操作时间：</span></div>
				  <div class="col-md-11">
				  	<span class="title"><s:date format="yyyy-MM-dd HH:mm:ss" name="classEntity.operateTime"></s:date></span>
				  </div>
				</div>            		
           		
				<div class="row">
				  <div class="col-md-1"><span class="title">  备       注：</span></div>
				  <div class="col-md-11">
				  	<s:textarea name="classEntity.memo" cssClass="form-control" id="memo"></s:textarea>
				  </div>
				</div>  
				
				<div class="row">
				  <div class="col-md-1"></div>
				  <div class="col-md-11">
					<s:submit value="修改" cssClass="btn btn-primary btn-sm"></s:submit>
				 	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
				  </div>
				</div> 				           		
           	</form>
           </div>
          </div>
        </section>
        <script type="text/javascript" src="${path}/static/sys/class/js/update_clazz.js"></script>
	</body>
	${error}
</html>