<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/view/common/base.jspf"%>
<link href="${path}/static/qes/css/qes.list.css" rel="stylesheet" />
</head>
<body>
	<section class="content-header">
		<h1>
			辅助模块 <small>备份还原</small>
		</h1>
		<ol class="breadcrumb">
			<li><button id="backUp"
				class="btn btn-primary btn-sm"><span
					class="glyphicon glyphicon-hdd" aria-hidden="true"></span> 备份</button></li>
		</ol>
	</section>
	<section class="content">
		<div class="box">
			<div class="box-tools">
				<table>
					<tr>
						<td>文件名</td>
						<td>备份时间</td>
						<td>操作</td>
					</tr>
					<tr>
						<td>文件名</td>
						<td>备份时间</td>
						<td><a href="${path}/exam/plan_toInsertPlan.action"
							class="btn btn-primary btn-sm"> <span
								class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
								还原
						</a></td>
					</tr>
				</table>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	 $('#backUp').click(function(){
		 $.get(path +"/common/comm_backUp.action?date=" + new Date().getTime(),{
			},function(data) {
				if(data){
					layer.msg('备份成功',{icon: 1});
				}else{
					layer.msg('备份失败',{icon: 1});
				}
			});
	 });
     
      //loc${path}/common/common_backUp.action
</script>
${success }
</html>