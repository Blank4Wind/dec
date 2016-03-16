<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body>
        <section class="content-header">
          <h1>
                             试题管理
            <small>试题显示</small>
          </h1>
          <ol class="breadcrumb">
            <a href="${path}/qes/qestions_toInsertQestionPage.action" class="btn btn-primary btn-sm">新增试题</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
          		<form id="dataForm" action="${path}/qes/qestions_qestionList.action" method="post" class="form-inline">
          			<div class="form-group">
	          			<label class="sr-only">题目：</label>
	          			<p class="form-control-static">题目：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">题目：</label>
	          			<s:textfield name="queryParam.question" class="form-control"></s:textfield>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">题型：</label>
	          			<p class="form-control-static">题型：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">题型：</label>
	          			<s:select list="#{'1':'单选题','2':'多选题','3':'判断题'}" name="queryParam.questionType" headerKey="" headerValue="全部" cssClass="form-control"></s:select>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">难度：</label>
	          			<p class="form-control-static">难度：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">难度：</label>
	          			<s:select list="#{'1':'低','2':'中','3':'较高','4':'高'}" name="queryParam.difficulty" headerKey="" headerValue="全部" cssClass="form-control"></s:select>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<s:select list="#{'1':'有效','2':'无效'}" headerKey="" headerValue="全部" cssClass="form-control" name="queryParam.state"></s:select>
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
	                      <th>题目</th>
	                      <th>题型</th>
	                      <th>所属课程</th>
	                      <th>难度</th>
	                      <th>状态</th>
	                      <th>操作</th>
	                    </tr>
                 	</thead>
                    <tbody>
                    	<s:if test="pageModel.list.size == 0">
	                 		<tr>
	                 			<td colspan="7">没有相关信息</td>
	                 		</tr>
	                 	</s:if>
	                 	<s:else>
		                    <s:iterator value="pageModel.list" var="qes" status="v">
		                    	<tr class="dataTr">
			                      <td><s:property value="#v.count+(pageModel.pageNum-1)*pageModel.pageSize"/><input type="hidden" value="<s:property value="#v.count"/>"></td>
			                      <td>
			                      		<c:choose>
				  							<c:when test="${fn:length(qes.question) > 50}">
				  								<c:out value="${fn:substring(qes.question,0,50)}......"></c:out>
					  						</c:when>
					  						<c:otherwise>
					  							${qes.question}
					  						</c:otherwise>
				  						</c:choose>
			                      </td>
			                      <td>
			                      	<s:if test="#qes.questionType == 1">
			                      		单选题
			                      	</s:if>
			                      	<s:elseif test="#qes.questionType == 2">
			                      		多选题
			                      	</s:elseif>
			                      	<s:elseif test="#qes.questionType == 3">
			                      		判断题
			                      	</s:elseif>
			                      </td>
			                      <td>${qes.courseName}</td>
			                      <td>
			                      	<s:if test="#qes.difficulty == 1">
			                      		低
			                      	</s:if>
			                      	<s:elseif test="#qes.difficulty == 2">
			                      		中
			                      	</s:elseif>
			                      	<s:elseif test="#qes.difficulty == 3">
			                      		较高
			                      	</s:elseif>
			                      	<s:elseif test="#qes.difficulty == 4">
			                      		高
			                      	</s:elseif>
			                      </td>
			                      <td>
			                      	<s:if test="#qes.state == 1">
			                      		<span class="label label-success">有效</span>
			                      	</s:if>
			                      	<s:elseif test="#qes.state == 2">
			                      		<span class="label label-danger">无效</span>
			                      	</s:elseif>
			                      </td>
			                      <td>
			                      	<input type="hidden" class="qesId" value="${qes.id}">
									<input type="hidden" value="${qes.state}" class="status">
			                      	<s:if test="#qes.state == 2">
			                      		<a href="javascript:void(0);" class="state label label-success">有效</a>
			                      	</s:if>
			                      	<s:elseif test="#qes.state == 1">
			                      		<a href="javascript:void(0);" class="state label label-danger">无效</a>
			                      	</s:elseif>
			                      	<a href="${path}/qes/qestions_qestionUpdate.action?id=<s:property value="#qes.id"/>" title="修改" target="tag">
					                     <span class="label label-warning">详情</span>
					                </a>
			                      </td>
			                    </tr>
		                    </s:iterator>
		                    <tr>
							<td colspan="7">
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
    <script src="${path}/static/qes/js/course_list.js"></script>
    <script type="text/javascript">
	function pageNow(i){
		$('#dataForm').attr("action",path + "/qes/qestions!qestionList.action?page="+i);
		$('#dataForm').submit();
	}
	</script>
	</body>
	${success }
</html>