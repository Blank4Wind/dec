<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/view/common/base.jspf"%>
<link href="${path}/static/common/plugins/umeditor/themes/default/css/umeditor.min.css" type="text/css" rel="stylesheet">
<link href="${path}/static/exam/css/insert_plan.css" rel="stylesheet">
</head>
<body>
   <div style="overflow:auto;height: 620px;">
	<section class="content-header">
		<h1>
			站内消息管理 <small>修改站内消息</small>
			<small><strong>(*为必填项)</strong></small>
		</h1>
	</section>
	<section class="content">
		<div class="box">
		 <div class="main_content_wrap">
			<div class="row">
				<div class="col-md-6">
					<form action="${path}/assist/assist_updateAssistMessages.action"
						method="post"   autocomplete="off" id="infoForm">
						<div class="row">
							<div class="col-md-2">
								<span class="title"><b>*</b>标　　 题：</span>
							</div>
							<div class="col-md-10">
								<s:textfield cssClass="form-control" name="assistDetils.title" />
								<s:hidden cssClass="form-control" name="assistDetils.id" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title"><b>*</b>内　 　容：</span>
							</div>
							<div class="col-md-10">
								<script type="text/plain" id="myEditor" style="width:400px;height:150px;" name="assistDetils.content">
										${assistDetils.content}
								</script>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title"><b>*</b>接受方式：</span>
							</div>
							<div class="col-md-10">
								<label><input id="r1" type="radio" name="clazz" value="banji"/>班级</label> 　　<label><input id="r2" type="radio"  name="clazz" value="student"/>学员</label>
							</div>
						</div>
						<div class="row" id="studentsdd">
							<div class="col-md-2">
								<span class="title">   接收人员：</span>
							</div>
							<div class="col-md-10">
								<s:hidden id="studentsId" name="assistDetils.receiverUserIds"
									cssClass="form-control"/>
								<div id="studentsName"
									style="width: 400px; height: 35px;line-height:35px; border: 1px solid #D2D6DE;">
									<c:if test="${not empty assistDetils.user2}">
										<s:iterator value="assistDetils.user2" var="use">
											<div class="${use.id}" style="float:left;" ondblclick="deleteStudent('${use.id}')" title="双击取消">${use.name}&nbsp;&nbsp;</div>
										</s:iterator>
									</c:if>
								</div>
							</div>
						</div>
						<div class="row" id="classdd">
							<div class="col-md-2">
								<span class="title">   接收班级：</span>
							</div>
							<div class="col-md-10">
								<select id="classId" name="assistDetils.receiverClassIds"
									class="form-control">
									<option value=""
										<c:if test="${empty  assistDetils.receiverClassIds}">selected="selected"</c:if>>请选择</option>
									<c:forEach items="${classEntity}" var="class">
										<option value="${class.id}"
											<c:if test="${assistDetils.receiverClassIds eq class.id}">selected="selected"</c:if>>${class.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title"> <b>*</b>发送时间：</span>
							</div>
							<div class="col-md-10">
								<input id="d4311" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="assistDetils.sendTime"/>' type="text" readonly="readonly" class="Wdate form-control"
									name="assistDetils.sendTime" type="text"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title"> <b>*</b>过期时间：</span>
							</div>
							<div class="col-md-10">
								<input id="d4312" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="assistDetils.expireTime"/>' readonly="readonly" class="Wdate form-control"
									name="assistDetils.expireTime" type="text"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title">  发  送  者：</span>
							</div>
							<div class="col-md-10">
								<s:textfield name="assistDetils.uname" cssClass="form-control" readonly="true" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title"> <b>*</b>状　　态：</span>
							</div>
							<div class="col-md-10">
								<select class="form-control" name="assistDetils.state">
									<!--  <option value="0" <c:if test="${assistDetils.state==0}">selected="selected"</c:if>>请选择</option>-->
									<option value="1" <c:if test="${assistDetils.state==1}">selected="selected"</c:if>>生　效</option>
									<option value="2" <c:if test="${assistDetils.state==2}">selected="selected"</c:if>>待生效</option>
									<option value="3" <c:if test="${assistDetils.state==3}">selected="selected"</c:if>>删　除</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<span class="title"></span>
							</div>
							<div class="col-md-10">
								<c:if test="${assistDetils.senderId eq user.id}"><input type="submit" value="修改" class="btn btn-primary btn-sm"></c:if>
								<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-6">
					<div class="stu-box" style="display:none;">
						<div class="stu-box-title">学员名单</div>
						<ul class="first-ul">
							<c:forEach items="${classEntity}" var="class">
								<li class="icon-block" onclick="showStudent('${class.id}');"><input
									class="groupcloselm_ico ${class.id}" type="button">${class.name}</li>
							</c:forEach>
							<c:forEach items="${userList}" var="u">
								<div class="a${u.classId} info" style="display: none"
									onclick="addStudent('${u.id}','${u.name}');">${u.name }</div>
							</c:forEach>
					   </ul>
					</div>
				</div>
			</div>
		   </div>
		</div>
	</section>
	</div>
	<script type="text/javascript" charset="utf-8" src="${path}/static/common/plugins/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${path}/static/common/plugins/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${path}/static/common/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>
	<script src="${path}/static/common/plugins/My97DatePicker/WdatePicker.js"></script>
	<script src="${path}/static/assist/js/update-message.js"></script>
	<script type="text/javascript">
	    //实例化编辑器
	    var um = UM.getEditor('myEditor');
	</script>
</body>
${error}
</html>