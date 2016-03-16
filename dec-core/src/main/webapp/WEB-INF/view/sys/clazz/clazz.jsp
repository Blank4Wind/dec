<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<script src="${path}/static/common/plugins/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
        <section class="content-header">
          <h1>
                             班级管理
            <small>班级显示</small>
          </h1>
          <ol class="breadcrumb">
          	<a href="${path}/sys/clazz_toAddClazzPage.action" class="btn btn-primary btn-sm">新增班级</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
          		<s:form id="dataForm" action="clazz!toClazzPage" method="post" class="form-inline">
          			<div class="form-group">
	          			<label class="sr-only">名称：</label>
	          			<p class="form-control-static">名称：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">名称：</label>
	          			<s:textfield name="queryParam.name" class="form-control"></s:textfield>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">时间(S)：</label>
	          			<p class="form-control-static">时间(S)：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">时间(S)：</label>
	          			<input id="d4311" type="text"  readonly="readonly" class="Wdate form-control" style="height: 33px;border-color: #cecece"
									name="queryParam.startTime" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="queryParam.startTime"/>'
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" />
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">时间(E)：</label>
	          			<p class="form-control-static">时间(E)：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">时间(E)：</label>
	          			<input id="d4312" value='<s:date format="yyyy-MM-dd HH:mm:ss" name="queryParam.endTime"/>' readonly="readonly" class="Wdate form-control"
									name="queryParam.endTime" type="text" style="height: 33px;border-color: #cecece"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" />
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
							<s:select name="queryParam.state" list="#{'1':'启用','2':'禁用' }" headerKey="" headerValue="全部" class="form-control"></s:select>
	          		</div>
	          		<div class="form-group">
	          			<input type="submit" value="查询" class="btn btn-primary btn-sm">
	          		</div>
          		</s:form>
			 </div>
			 <div class="box-body table-responsive no-padding">
				<table class="table table-hover">
					<thead>
						<tr class="ui-jqgrid-labels" role="rowheader">
							<th>序号</th>
							<th>班级名称</th>
							<th>创建时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<s:if test="pageModel.list.size == 0">
						<tr>
							<td colspan="5">没有相关信息</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator var="list" value="pageModel.list" status="v">
						<tr class="dataTr">
							<td><span><s:property value="#v.count+(pageModel.pageNum-1)*pageModel.pageSize"/></span></td>
							<td><s:property value="name"></s:property></td>
							<td><span><s:date format="yyyy-MM-dd HH:mm:ss" name="operateTime"></s:date></span></td>
							<td>
								<input type="hidden" class="tagSpan">
								<s:if test="state==1">
									<span class="label label-success">有效</span>
								</s:if>
								<s:else>
									<span class="label label-danger">无效</span>
								</s:else>
							</td>
							<td>
							
							<input type="hidden" value="${list.id}" class="clazzId">
							<input type="hidden" value="${list.state}" class="clazzState">
							<s:if test="state==1">
								<a href="javascript:void(0);" onclick="col(${v.count})" class="tate label label-danger">禁用</a>
							</s:if>
							<s:else>
								<a href="javascript:void(0);" onclick="col(${v.count})" class="tate label label-success">启用</a>
							</s:else>
								<a href="${path}/sys/clazz_getClassInfo.action?classEntity.id=${list.id}">
					             		   <span class="label label-warning">详情</span>
					            </a>
							</td>
						</tr>
					</s:iterator>
						<tr>
							<td colspan="5">
								<nav>
									<ul class="pagination">
										<li <c:if test="${pageModel.first==pageModel.pageNum}">class="disabled"</c:if> ><a href="javascript:void(0);" <c:if test="${pageModel.first!=pageModel.pageNum}">  onclick="pageNow(${pageModel.first})"</c:if> id="first" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
										</a></li>
										
										<c:if test="${pageModel.totalPageNum>5}" var="paging">
										<c:forEach begin="1" end="${pageModel.totalPageNum-pageModel.pageNum+3<5?pageModel.totalPageNum-pageModel.pageNum+pageModel.totalPageNum%5+1:5}" varStatus="s">
											<li onclick="pageNow(${pageModel.pageNum>=5?(pageModel.pageNum-2)+s.count:s.count})" <c:if test="${(pageModel.pageNum>=5?(pageModel.pageNum-2)+s.count:s.count)==pageModel.pageNum}">class="active"</c:if>>
											<a href="javascript:void(0)">${pageModel.pageNum>=5?(pageModel.pageNum-2)+s.count:s.count}</a></li>
										</c:forEach>
										</c:if>
										
										<c:if test="${!paging}">
										<c:forEach begin="1" end="${pageModel.totalPageNum}" varStatus="s">
											<li onclick="pageNow(${s.count})" <c:if test="${s.count==pageModel.pageNum}">class="active"</c:if>>
											<a href="javascript:void(0)">${s.count}</a></li>
										</c:forEach>
										</c:if>
										<li <c:if test="${pageModel.totalPageNum==pageModel.pageNum}">class="disabled"</c:if> <c:if test="${pageModel.totalPageNum!=pageModel.pageNum}"> onclick="pageNow(${pageModel.last})"</c:if>><a href="javascript:void(0);"  id="last"
											aria-label="Next"><span aria-hidden="true">&raquo;</span>
										</a></li>
									</ul>
								</nav>
							</td>
						</tr>
					</s:else>
			   </tbody>
			 </table>
          	</div>
          </div>
        </section>
	</body>
	<script type="text/javascript">
	function pageNow(i){
		$('#dataForm').attr("action",path + "/sys/clazz!toClazzPage.action?page="+i);
		$('#dataForm').submit();
	}
	</script>
	<script src="${path}/static/sys/class/js/class_page.js"></script>
	${success}
</html>