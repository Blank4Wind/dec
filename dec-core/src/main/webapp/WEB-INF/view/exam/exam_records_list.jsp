<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link href="${path}/static/qes/css/qes.list.css" rel="stylesheet">
	</head>
	<body>
	<div style="overflow:auto; height: 620px;">
        <section class="content-header">
          <h1>
                             考试记录
            <small>考试记录显示</small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
          		<form class="form-inline" action="${path}/exam/records_recordsList.action" method="post" id="dataForm">
          			<div class="form-group">
	          			<label class="sr-only">学员名称：</label>
	          			<p class="form-control-static">学员名称：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">学员名称：</label>
	          			<s:textfield name="recordsEntity.userName" cssClass="form-control"/>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">试卷名：</label>
	          			<p class="form-control-static">试卷名：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">试卷名：</label>
	          			<s:textfield name="recordsEntity.paperName" cssClass="form-control" />
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">班级名：</label>
	          			<p class="form-control-static">班级名：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">班级名：</label>
	          			<s:select name="clazzId" list="classList" headerKey="" headerValue="全部" listKey="id" listValue="name" cssClass="form-control"></s:select>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">得分数：</label>
	          			<p class="form-control-static">得分数：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">得分数：</label>
	          			<s:select name="recordsEntity.getPoint" cssClass="form-control" list="#{'1':'60分以下','2':'60-70分','3':'70-80分','4':'80-100分','5':'100分以上'}" headerKey="0" headerValue="全部"></s:select>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<s:select name="recordsEntity.isPass" cssClass="form-control" list="#{'1':'通过','2':'未通过','3':'未通过或超时'}" headerKey="0" headerValue="全部"></s:select>
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
	                      <th>学员名称</th>
	                      <th>试卷名称</th>
	                      <th>开始时间</th>
	                      <th>结束时间</th>
	                      <th>总分数</th>
	                      <th>得分数</th>
	                      <th>是否通过</th>
	                    </tr>
                 	</thead>
                    <tbody>
                    	<s:if test="pageModel.list.size == 0">
                    		<tr>
                    			<td colspan="8">没有相关信息</td>
                    		</tr>
                    	</s:if>
                    	<s:else>
                    		<s:iterator value="pageModel.list" var="records" status="v">
                    			<tr>
                    			  <td><s:property value="#v.count+(pageModel.pageNum-1)*pageModel.pageSize"/></td>
			                      <td>${records.userName}</td>
			                      <td>${records.paperName}</td>
			                      <td>
			                      	<s:date name="#records.startTime" format="yyyy-MM-dd HH:mm:ss"/>
			                      </td>
			                      <td>
			                      	<s:date name="#records.submitTime" format="yyyy-MM-dd HH:mm:ss"/>
			                      </td>
			                      <td>
			                      	${records.totalPoint}
			                      </td>
			                      <td>
			                      	${records.getPoint}
			                      </td>
			                      <td>
			                      	<s:if test="#records.isPass == 1">
			                      		<span class="label label-success">通过</span>
			                      	</s:if>
			                      	<s:elseif test="#records.isPass == 2">
			                      		<span class="label label-danger">未通过</span>
			                      	</s:elseif>
			                      	<s:elseif test="#records.isPass == 3">
			                      		<span class="label label-info">未提交或超时</span>
			                      	</s:elseif>
			                      </td>
			                      </tr>
			                  </s:iterator>
			                  <tr>
                    			<td colspan="8">
                    				<nav>
										<ul class="pagination">
											<li 
												<c:if test="${pageModel.first==pageModel.pageNum}">class="disabled"</c:if> >
													<a href="javascript:void(0);" <c:if test="${pageModel.first!=pageModel.pageNum}">  onclick="pageNow(${pageModel.first})"</c:if> id="first" aria-label="Previous">
														<span aria-hidden="true">&laquo;</span>
													</a>
											</li>
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
											<li <c:if test="${pageModel.totalPageNum==pageModel.pageNum}">class="disabled"</c:if> <c:if test="${pageModel.totalPageNum!=pageModel.pageNum}"> onclick="pageNow(${pageModel.last})"</c:if>>
												<a href="javascript:void(0);"  id="last" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
												</a>
											</li>
										</ul>
									</nav>
                    			</td>
                    		</tr>
			           </s:else>
                   </tbody>
                 </table>
             </div>
          </div>
          <div id="container" style="height:250px"></div>
        </section>
        </div>
	</body>
	<script>
	function pageNow(i){
		$('#dataForm').attr("action",path + "/exam/records_recordsList.action?pageNo="+i);
		$('#dataForm').submit();
	}
	var recordsJson = '${recordsJson}';
	</script>
    <script src="${path}/static/common/plugins/highcharts/highcharts.js"></script>
    <script src="${path}/static/common/plugins/highcharts/exporting.js"></script>
    <script src="${path}/static/exam/js/records-list.js"></script>
</html>