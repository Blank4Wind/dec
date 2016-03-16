<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/view/common/base.jspf"%>
<link href="${path}/static/sys/role/css/role_show.css" rel="stylesheet">
</head>
<body>
<div style="overflow:auto; height: 620px;">
	<section class="content-header">
		<h1>
			<small>待考试卷</small>
		</h1>
	</section>
	<section class="content">
		<div class="box">
			<div class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>试卷名</th>
							<th>考试地点</th>
							<th>考试开始时间</th>
							<th>考试结束时间</th>
							<th>操作者</th>
							<th>操作时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value='plan' var="p" status='status'>
						<tr>
							<td>${status.count }</td>
							<td>${p.qname}</td>
							<td>${p.examClassroom}</td>
							<td><s:date name="#p.examTimeStart" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td><s:date name="#p.examTimeStop" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${p.uname}</td>
							<td><s:date name="#p.operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>
								<jsp:useBean id="now" class="java.util.Date" />
								 <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="nowDate"></fmt:formatDate>
								 <fmt:formatDate value="${p.examTimeStart}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="trainDate"/>
								 <c:if test="${nowDate gt trainDate}" var="rs"><a class="label label-success" href="${path}/stu/stu_exam.action?id=${p.id}" target="_blank">进入考试</a> </c:if> 
								 <c:if test="${!rs}"> <span class="label label-info">待入考试 </span></c:if>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	</div>
</body>
</html>