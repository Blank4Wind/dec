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
                             角色管理
            <small>角色显示</small>
          </h1>
          <ol class="breadcrumb">
            <a href="${path}/sys/role_add.action" class="btn btn-primary btn-sm">新增角色</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
              	<form class="form-inline" action="${path}/sys/role_list.action" method="post">
          			<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<s:select list="#{'1':'启用','2':'禁用'}" cssClass="form-control" headerKey="" headerValue="全部" name="roleEntity.state"/>
	          		</div>
	          		<div class="form-group">
	          			<input type="submit" value="查询" class="btn btn-primary btn-sm">
	          		</div>
	          	</form>	
            </div>
           	<div class="box-body table-responsive no-padding">
                 <table class="table table-hover">
                 	<thead>
	                 	<tr>
							<th>序号</th>
							<th>角色名称</th>
							<th>操作者</th>
							<th>操作时间</th>
							<th>状态</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="roleList.size == 0">
							<tr>
								<td colspan="7">没有相关信息</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="roleList" var="role" status="v">
								<tr class="dataTr">
									<td><s:property value="#v.count"/></td>
									<td><s:property value="#role.name"/></td>
									<td><s:property value="#role.userName"/></td>
									<td><s:date name="#role.operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
									<s:if test="#role.state==1">
									<span class="label label-success">启用</span>
									</s:if>
									<s:elseif test="#role.state==2">
									<span class="label label-danger">禁用</span>
									</s:elseif>
									</td>
									<td><s:property value="#role.memo"/></td>
									<td>
									    <input type="hidden" class="roleId" value="${role.id}">
										<input type="hidden" value="${role.state}" class="status">
				                      	<s:if test="#role.state == 1">
				                      		<a href="javascript:void(0);" class="state label label-danger">禁用</a>
				                      	</s:if>
				                      	<s:elseif test="#role.state == 2">
				                      		<a href="javascript:void(0);" class="state label label-success">启用</a>
				                      	</s:elseif>								
						            <a href="${path}/sys/role_roleUpdate.action?id=<s:property value="#role.id"/>">
						               			<span class="label label-warning">详情</span>
						            </a>
									<a href="${path}/sys/role_powerMGR.action?id=${role.id}" class="label label-success">授权</a>					            </td>
								</tr>
							</s:iterator>
						</s:else>
					</tbody>
                 </table>
             </div>
          </div>
        </section>
    <script src="${path}/static/sys/role/js/data_show.js"></script>
	</body>
	${success}
</html>