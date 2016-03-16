<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link href="${path}/static/qes/css/qes.list.css" rel="stylesheet">
	</head>
	<body>
        <section class="content-header">
          <h1>
                             考试安排
            <small>考试安排显示</small>
          </h1>
          <ol class="breadcrumb">
            <a href="${path}/exam/plan_toInsertPlan.action" class="btn btn-primary btn-sm">新增考试安排</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
          		<form class="form-inline" action="${path}/exam/plan_planList.action" method="post" id="dataForm">
          			<div class="form-group">
	          			<label class="sr-only">类型：</label>
	          			<p class="form-control-static">类型：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">类型：</label>
	          			<s:select name="type" cssClass="form-control" list="#{'true':'我所新增','false':'其他新增' }"></s:select>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<s:select name="planEntity.state" cssClass="form-control" list="#{'1':'生效','2':'待生效','3':'已删除'}" headerKey="" headerValue="全部"></s:select>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">是否过期：</label>
	          			<p class="form-control-static">是否过期：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">是否过期：</label>
	          			<s:select name="timeout" cssClass="form-control" list="#{'1':'是','2':'否'}" headerKey="0" headerValue="全部"></s:select>
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
	                      <th>考试开始时间</th>
	                      <th>考试结束时间</th>
	                      <th>考试教室</th>
	                      <th>试卷名称</th>
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
                    		<s:iterator value="pageModel.list" var="plan" status="v">
                    			<tr class="dataTr">
			                      <td><s:property value="#v.count+(pageModel.pageNum-1)*pageModel.pageSize"/><input type="hidden" value="<s:property value="#v.count"/>"></td>
			                      <td><s:date name="#plan.examTimeStart" format="yyyy-MM-dd HH:mm:ss"/></td>
			                      <td>
			                      	<s:date name="#plan.examTimeStop" format="yyyy-MM-dd HH:mm:ss"/>
			                      </td>
			                      <td>${plan.examClassroom}</td>
			                      <td>
			                      	${plan.paperName}
			                      </td>
			                      <td>
			                      	<s:if test="#plan.state == 1">
			                      		<span class="label label-success">生效</span>
			                      	</s:if>
			                      	<s:elseif test="#plan.state == 2">
			                      		<span class="label label-info">待生效</span>
			                      	</s:elseif>
			                      	<s:elseif test="#plan.state == 3">
			                      		<span class="label label-danger">删除</span>
			                      	</s:elseif>
			                      </td>
			                      <td>
			                      	<input type="hidden" value="${plan.id}" class="planId">
			                      	<input type="hidden" value="${plan.state}" class="status">
				                    <s:if test="#plan.operateUserId ==#session.user.id">
				                      	<s:if test="#plan.state == 1">
				                      		<a href="javascript:void(0);" class="label label-danger del" onclick="updateState(${v.count},3,'${plan.id}');">删除</a>
				                      	</s:if>
				                      	<s:if test="#plan.state == 2">
				                      		<a href="javascript:void(0);" class="label label-success com" onclick="updateState(${v.count},1,'${plan.id}');">生效</a>
				                      		<a href="javascript:void(0);" class="label label-danger del" onclick="updateState(${v.count},3,'${plan.id}');">删除</a>
				                      	</s:if>
				                      	<s:if test="#plan.state == 3">
				                      	
				                      	</s:if>
				                     </s:if>
				                     <a href="${path}/exam/plan_beforeUpdate.action?planEntity.id=${plan.id}" title="修改"  target="tag">
					                     <span class="label label-warning">详情</span>
					                </a>
			                      </td>
		                     	</tr>
                    		</s:iterator>
                    			<tr>
                    				<td colspan="7">
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
									</td>
								  </tr>
                    	</s:else>
                   </tbody>
                 </table>
             </div>
          </div>
        </section>
	</body>
	<script>
	function pageNow(i){
		$('#dataForm').attr("action",path + "/exam/plan_planList.action?pageNo="+i);
		$('#dataForm').submit();
	}
	function updateState(count,state,id){
		var c = count - 1;
		var state = state;
		var id= id;
		if(state == 3){
			layer.confirm('考试安排已执行，是否确定删除？', {icon: 3}, function(index){
				$.get(path + "/exam/plan_changeState.action?date=" + new Date().getTime(),{
					"planEntity.id":id,
					"planEntity.state":state
				},function(data){
					if(data == 3) {
						$('.dataTr').children('td:nth-child(6)').children('span').eq(c).attr("class","label label-danger").html('已删除');
						$('.dataTr').children('td:last-child').eq(c).remove('.del,.com');
					}
					if (data == 1) {
						$('.dataTr').children('td:nth-child(6)').children('span').eq(c).attr("class","label label-success").html('生效');
						$('.dataTr').children('td:last-child').eq(c).children('.com').css("display","none");
					}
				});
				layer.close(index);
			   	
			});
		}else{
		$.get(path + "/exam/plan_changeState.action?date=" + new Date().getTime(),{
			"planEntity.id":id,
			"planEntity.state":state
		},function(data){
			if(data == 3) {
				$('.dataTr').children('td:nth-child(6)').children('span').eq(c).attr("class","label label-danger").html('已删除');
				$('.dataTr').children('td:last-child').eq(c).remove('.del,.com');
			}
			if (data == 1) {
				$('.dataTr').children('td:nth-child(6)').children('span').eq(c).attr("class","label label-success").html('生效');
				$('.dataTr').children('td:last-child').eq(c).children('.com').css("display","none");
			}
		});
	  }
		
	}
	</script>
	${success }
</html>