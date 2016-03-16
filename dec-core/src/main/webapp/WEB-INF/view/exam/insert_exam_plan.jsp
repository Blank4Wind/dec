<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link href="${path}/static/exam/css/insert_plan.css" rel="stylesheet">
	</head>
	<body>
		<div style="overflow:auto; height: 620px;">
        <section class="content-header">
          <h1>
                             考试安排管理
            <small>新增考试安排</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
          	<div class="row">
			  <div class="col-md-6">
	          	<form action="${path}/exam/plan_insert.action" method="post"   autocomplete="off" id="infoForm">
		          	<div class="row">
					  <div class="col-md-2"><span class="title"><b>*</b>开始时间：</span></div>
					  <div class="col-md-10">
					  	<input id="d4311" readonly="readonly"  class="Wdate form-control" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" name="planEntity.examTimeStart"/>
					  </div>
					</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>结束时间：</span></div>
					  <div class="col-md-10">
					  	<input id="d4312" readonly="readonly"  class="Wdate form-control" type="text" name="planEntity.examTimeStop"/>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>考试教室：</span></div>
					  <div class="col-md-10">
					  	<input type="text" name="planEntity.examClassroom" class="form-control">
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>试卷名称：</span></div>
					  <div class="col-md-10">
					  	<s:select class="form-control" name="planEntity.examPaperId" list="paperlist" listKey="id" listValue="name" headerKey="" headerValue="请选择" id="paper"></s:select>
					  </div>
		           	</div>
		           	<div class="row">
							<div class="col-md-2">
								<span class="title"><b>*</b>接受方式：</span>
							</div>
							<div class="col-md-10">
								<label><input id="r1" type="radio" checked="checked" name="clazz" value="banji"/>班级</label> 　　<label><input id="r2" type="radio"  name="clazz" value="student"/>学员</label>
							</div>
					</div>
		           	<div class="row" id="studentsdd">
		           	  <div class="col-md-2"><span class="title">  接收人员：</span></div>
					  <div class="col-md-10">
					  	<input id="studentsId" name="planEntity.toUserIds" type="hidden" class="form-control">
						<div id="studentsName" class="form-control">
						</div>
					  </div>
		           	</div>
		           	<div class="row"  id="classdd">
		           	  <div class="col-md-2"><span class="title">  接收班级：</span></div>
					  <div class="col-md-10">
					  	<s:select list="classlist" name="planEntity.toClassId" listKey="id" listValue="name" headerKey="" headerValue="--请选择班级--" cssClass="form-control"></s:select>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>状       态：</span></div>
					  <div class="col-md-10">
					  	<select class="form-control"  name="planEntity.state">
					  		<option value="0">请选择</option>
					  		<option value="1">发送</option>
					  		<option value="2">待发送</option>
					  	</select>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title">  备       注：</span></div>
					  <div class="col-md-10">
					  	<textarea name="planEntity.memo" class="form-control" id="memo"></textarea>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"></span></div>
					  <div class="col-md-10">
					  	<input type="submit" value="新增" class="btn btn-primary btn-sm">
					 	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
					  </div>
		           	</div>
		         </form>
		      </div>
			  <div class="col-md-6">
					<div class="stu-box" style="display:none;">
						<div class="stu-box-title">学员名单</div>
						<ul class="first-ul">
						    <c:forEach items="${classlist}" var="class">
							<li class="icon-block" onclick="showStudent('${class.id}');"><input class="groupcloselm_ico ${class.id}"
								type="button">${class.name}</li>
							</c:forEach>
							<c:forEach items="${userlist}" var="u">	
								<div class="a${u.classId} info" style="display: none" onclick="addStudent('${u.id}','${u.name}');">${u.name }</div>
							</c:forEach>		
						</ul>
					</div>
			  </div>
		    </div>
		    </div>
          </div>
        </section>
        </div>
   	<script src="${path}/static/common/plugins/My97DatePicker/WdatePicker.js"></script>
   	<script src="${path}/static/exam/js/insert-plan.js"></script>
	</body>
	${error}
</html>