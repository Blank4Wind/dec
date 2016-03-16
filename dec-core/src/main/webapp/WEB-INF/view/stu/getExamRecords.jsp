<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/view/common/base.jspf"%>
<link href="${path}/static/sys/role/css/role_show.css" rel="stylesheet">
</head>
<body>
	<div style="overflow: auto; height: 620px;">
		<section class="content-header">
			<h1>
				<small>考试记录</small>
			</h1>
		</section>
		<section class="content">
			<div class="box">
				<div class="box-tools">
					<form id="dataForm" class="form-inline"
						action="${path}/stu/stu_getExamRecords.action" method="post">
						<div class="form-group">
							<label class="sr-only">试卷名：</label>
							<p class="form-control-static">试卷名：</p>
						</div>
						<div class="form-group">
							<label class="sr-only">试卷名：</label>
							<s:textfield name="recordsDetails.qname" cssClass="form-control" />
						</div>
						<div class="form-group select">
							<label class="sr-only">状态：</label>
							<p class="form-control-static">状态：</p>
						</div>
						<div class="form-group">
							<span class="sr-only">状态：</span>
							<s:select name="recordsDetails.isPass" cssClass="form-control"
								list="#{'1':'已通过','2':'未通过','3':'未提交或超时' }" headerKey="0"
								headerValue="全部"></s:select>
						</div>
						<button class="btn btn-primary btn-sm">提交</button>
					</form>
				</div>
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>试卷名</th>
								<th>考试开始时间</th>
								<th>提交时间</th>
								<th>总分数</th>
								<th>得分数</th>
								<th>是否通过</th>
							</tr>
						</thead>
						<tbody>
							<s:if test="records.list.size!=0">
								<s:iterator value="records.list" var="r" status="s">
									<tr>
										<td>${s.count+(pageModel.pageNum-1)*pageModel.pageSize }</td>
										<td>${r.qname}</td>
										<td><s:date format="yyyy-MM-dd HH:mm:ss"
												name="#r.startTime" /></td>
										<td><s:date format="yyyy-MM-dd HH:mm:ss"
												name="#r.submitTime" /></td>
										<td>${r.tpoint}</td>
										<td>${r.getPoint}</td>
										<td><c:if test="${r.isPass==1}"><span class="label label-success">已通过</span></c:if> <c:if
												test="${r.isPass==2}"><span class="label label-danger">未通过</span></c:if> <c:if test="${r.isPass==3}"><span class="label label-info">未提交或超时</span></c:if>
										</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="7">
										<nav>
											<ul class="pagination">
												<li
													<c:if test="${records.first==records.pageNum}">class="disabled"</c:if>><a
													href="javascript:void(0);"
													<c:if test="${records.first!=records.pageNum}">  onclick="pageNow(${records.first})"</c:if>
													id="first" aria-label="Previous"> <span
														aria-hidden="true">&laquo;</span>
												</a></li>
												<c:if test="${records.totalPageNum>5}" var="paging">
													<c:forEach begin="1"
														end="${records.totalPageNum-records.pageNum+3<5?records.totalPageNum-records.pageNum+records.totalPageNum%5+1:5}"
														varStatus="s">
														<li
															onclick="pageNow(${records.pageNum>=5?(records.pageNum-2)+s.count:s.count})"
															<c:if test="${(records.pageNum>=5?(records.pageNum-2)+s.count:s.count)==records.pageNum}">class="active"</c:if>>
															<a href="javascript:void(0)">${records.pageNum>=5?(records.pageNum-2)+s.count:s.count}</a>
														</li>
													</c:forEach>
												</c:if>
												<c:if test="${!paging}">
													<c:forEach begin="1" end="${records.totalPageNum}"
														varStatus="s">
														<li onclick="pageNow(${s.count})"
															<c:if test="${s.count==records.pageNum}">class="active"</c:if>>
															<a href="javascript:void(0)">${s.count}</a>
														</li>
													</c:forEach>
												</c:if>
												<li
													<c:if test="${records.totalPageNum==records.pageNum}">class="disabled"</c:if>
													<c:if test="${records.totalPageNum!=records.pageNum}"> onclick="pageNow(${records.last})"</c:if>><a
													href="javascript:void(0);" id="last" aria-label="Next"><span
														aria-hidden="true">&raquo;</span> </a></li>
											</ul>
										</nav>
									</td>
								</tr>
							</s:if>
							<s:elseif test="records.list.size==0">
								<tr>
									<td colspan="7">没有相关信息！！！</td>
								</tr>
							</s:elseif>
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</div>           
	<script type="text/javascript" src="${path}/static/stu/js/getExamRecords.js">
	</script>
</body>
</html>