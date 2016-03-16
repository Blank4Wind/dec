<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<small>站内消息</small>
		</h1>
	</section>
	<section class="content">
		<div class="box">
			<div class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>序号</th>
							<th>标题</th>
							<th>发送者</th>
							<th>发送角色</th>
							<th>发送时间</th>
							<th>过期时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value='assist' var="a" status='status'>
						<tr>
							<td>${status.count }</td>
							<td>${a.title }</td>
							<td>${a.uname }</td>
							<td>${a.rname }</td>
							<td><s:date name="#a.sendTime" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td><s:date name="#a.expireTime" format="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<a href="#" class="label label-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="${a.title}" data-content="${a.content}" data-uname="${a.uname}"data-rname="${a.rname}" data-stime="<s:date name="#a.sendTime" format="yyyy-MM-dd HH:mm:ss"/>"data-etime="<s:date name="#a.expireTime" format="yyyy-MM-dd HH:mm:ss"/>" >详情查看</a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	  <!--style="border-radius:25px;box-shadow: 10px 10px 5px #888888;"-->
	    <div class="modal-content" >
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" style="text-align: center;" id="exampleModalLabel">New message</h4>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      	<p></p>
        	</div>
	      </div>
	      <div class="modal-footer">
	        <div>发送者：<span class="suname"></span>　　发送者角色：<span class="srname"></span></div>
	        <hr>
	        <div>发送时间：<span class="stime"></span>　　过期时间:<span  class="etime"></span></div>
	      </div>
	    </div>
	  </div>
	</div>
	</section>
	<script type="text/javascript" src="${path}/static/stu/js/getAssistMessage.js">
	</script>
	</div>
</body>
</html>