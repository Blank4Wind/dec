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
            <small>修改考试安排</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
           	<div class="main_content_wrap">
          	<div class="row">
			  <div class="col-md-6">
	          	<form action="${path}/exam/plan_update.action?planEntity.id=${planEntity.id}" method="post"  autocomplete="off" id="infoForm">
		          	<div class="row">
					  <div class="col-md-2"><span class="title"><b>*</b>开始时间：</span></div>
					  <div class="col-md-10">
					  	<input id="d4311" readonly="readonly" class="Wdate form-control" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" name="planEntity.examTimeStart" value='<s:date name="planEntity.examTimeStart" format="yyyy-MM-dd HH:mm:ss" />'/>
					  </div>
					</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>结束时间：</span></div>
					  <div class="col-md-10">
					  	<input id="d4312" readonly="readonly"  class="Wdate form-control" type="text"  name="planEntity.examTimeStop" value='<s:date name="planEntity.examTimeStop" format="yyyy-MM-dd HH:mm:ss" />'/>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>考试教室：</span></div>
					  <div class="col-md-10">
					  	<input type="text"  name="planEntity.examClassroom" class="form-control" value="${planEntity.examClassroom}">
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>试卷名称：</span></div>
					  <div class="col-md-10">
					  	<select class="form-control" name="planEntity.examPaperId" id="paper">
					  		<option value="">请选择</option>
					  		<s:iterator value="paperlist" var="paper">
					  			<option value="${paper.id}" <c:if test="${planEntity.examPaperId == paper.id}">selected</c:if> >${paper.name}</option>
					  		</s:iterator>
					  	</select>
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
								<span class="title">  接收人员：</span>
							</div>
							<div class="col-md-10">
								<s:hidden id="studentsId" name="planEntity.toUserIds"
									cssClass="form-control"/>
								<div id="studentsName" class="form-control">
									<c:if test="${not empty planEntity.user}">
										<s:iterator value="planEntity.user" var="use">
											<div class="${use.id}" style="float:left;" ondblclick="deleteStudent('${use.id}')" title="双击取消">${use.name}</div>
										</s:iterator>
									</c:if>
								</div>
							</div>
						</div>
			           	<div class="row" id="classdd">
			           	  <div class="col-md-2"><span class="title">  接收班 级：</span></div>
						  <div class="col-md-10">
						  	<select id="classId" class="form-control" name="planEntity.toClassId">
						  		<option value=""
										<c:if test="${empty  planEntity.toClassId}">selected="selected"</c:if>>请选择</option>
						  		<s:iterator value="classlist" var="clazz">
						  			<option value="${clazz.id}" <c:if test="${planEntity.toClassId == clazz.id}">selected</c:if> >${clazz.name}</option>
						  		</s:iterator>
						  	</select>
						  </div>
			           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"><b>*</b>状       态：</span></div>
					  <div class="col-md-10">
					  	<select class="form-control" name="planEntity.state">
					  		<option value="0">请选择</option>
					  		<option value="1" <c:if test="${planEntity.state == 1}">selected</c:if> >发送</option>
					  		<option value="2" <c:if test="${planEntity.state == 2}">selected</c:if>>待发送</option>
					  	</select>
					  </div>
		           	</div>
		           	<div class="row" id="rowClass">
		           	  <div class="col-md-2"><span class="title">  操作人员：</span></div>
					  <div class="col-md-10">
					  	<span class="title">${planEntity.opoinName}</span>
					  </div>
		           	</div>
		           	<div class="row" id="rowClass">
		           	  <div class="col-md-2"><span class="title">  操作时间：</span></div>
					  <div class="col-md-10">
					  	<span class="title"><s:date name="planEntity.operateTime" format="yyyy-MM-dd HH:mm:ss"/></span>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title">  备       注：</span></div>
					  <div class="col-md-10">
					  	<textarea name="planEntity.memo" class="form-control" id="memo">${planEntity.memo}</textarea>
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-2"><span class="title"></span></div>
					  <div class="col-md-10">
					  	<input type="submit" value="修改" class="btn btn-primary btn-sm">
					  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
					  </div>
		           	</div>
		         </form>
		      </div>
			  <div class="col-md-6">
			  	<s:if test="planEntity.toUserIds != null">
					<div class="stu-box">
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
				</s:if>
			  </div>
		    </div>
		    </div>
          </div>
        </section>
        </div>
   	<script src="${path}/static/common/plugins/My97DatePicker/WdatePicker.js"></script>
   	<script src="${path}/static/exam/js/update-plan.js"></script>
	</body>
	${error}
</html>