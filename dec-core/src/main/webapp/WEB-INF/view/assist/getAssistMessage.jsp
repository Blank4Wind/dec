<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/view/common/base.jspf"%>
</head>
<body>
<div style="overflow:auto;">
	<section class="content-header">
          <h1>
                             站内消息管理
            <small>站内消息管理</small>
          </h1>
          <ol class="breadcrumb">
            <a href="${path}/assist/assist_goAddAssistMessage.action"  class="btn btn-primary btn-sm">新增站内消息</a>
          </ol>
        </section>
        
	<section class="content">
		<div class="box">
			<div class="box-tools">
          		<form class="form-inline" id="dataForm" action="${path}/assist/assist_getAssistMessage.action" method="post">
          			<div class="form-group">
	          			<label class="sr-only">标题：</label>
	          			<p class="form-control-static">标题：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">标题：</label>
	          			<s:textfield name="messageDetails.title" type="text" cssClass="form-control"/>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">类型：</label>
	          			<p class="form-control-static">类型：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">类型：</label>
	          			<s:select list="#{'true':'我发送','false':'其他发送'}" cssClass="form-control" name="messageDetails.uname"/>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">角色：</label>
	          			<p class="form-control-static">角色：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">角色：</label>
	          			<!--<s:select name="messageDetails.rname" list="roleList" listKey="name" listValue="name"  headerKey="" headerValue="--请选择角色--" cssClass="form-control" value="bean.roleEntity"/>-->
	          			<select class="form-control" name="messageDetails.rname">
	          				<option value="">全部</option>
	          				<s:iterator value="roleList" var="role">
	          					<option value="${role.name}" <c:if test="${messageDetails.rname eq role.name}">selected="selected"</c:if>>${role.name}</option>
	          				</s:iterator>
	          			</select>
	          			
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">消息状态：</label>
	          			<p class="form-control-static">消息状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">消息状态：</label>
	          			<s:select list="#{'1':'已发送','2':'待发送','3':'已删除'}" headerKey="0" headerValue="全部" cssClass="form-control" name="messageDetails.state"/>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">是否过期：</label>
	          			<p class="form-control-static">是否过期：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">是否过期：</label>
	          			<s:select list="#{'1':'是','2':'否'}" headerKey="0" headerValue="全部" cssClass="form-control" name="beOverdue"/>
	          		</div>
	          		<div class="form-group">
	          			<s:submit type="submit" value="查询" class="btn btn-primary btn-sm"/>
	          		</div>
          		</form>   		
            </div>
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
							<th>状态</th>
		                    <th>操作</th>
						</tr>
					</thead>
					<tbody>
					<s:if  test="assist.list.size!=0">
						<s:iterator value='assist.list' var="a" status='status'>
						<tr class="myassist">
							<td>${status.count+(pageModel.pageNum-1)*pageModel.pageSize}</td>
							<td>${a.title }</td>
							<td>${a.uname }</td>
							<td>${a.rname }</td>
							<td><s:date format="yyyy-MM-dd HH:mm:ss" name="#a.sendTime"/></td>
							<td><s:date format="yyyy-MM-dd HH:mm:ss" name="#a.expireTime"/></td>
							<td>
			                 <c:if test="${a.state == 1}">
			                      <span class="label label-success">已生效</span>
			                 </c:if>
			                 <c:if test="${a.state == 2}">
			                 <span class="label label-info">待生效</span>
			                 </c:if>
			                 <c:if test="${a.state==3}">
			                      <span class="label label-danger">已删除</span>
			                 </c:if>
			                      </td>
			                      <td>
			                       <c:if test="${a.senderId eq user.id}">
			                      	<c:if test="${a.state == 1}">
			                      		<a href="javascript:void(0);" onclick="update('${a.id}',3,${status.count})"  class="label label-danger del">删除</a>
			                      	</c:if>
			                      	<c:if test="${a.state==2}">
			                      		<a href="javascript:void(0);" class="label label-success com" onclick="update('${a.id}',1,${status.count})">生效</a>
			                      		<a href="javascript:void(0);" onclick="update('${a.id}',3,${status.count})" class="label label-danger del">删除</a>
			                      	</c:if>
			                      	<c:if test="${a.state==3}">
			                      	
			                      	</c:if>
			                      	</c:if>
			                      	<a href="${path}/assist/assist_goUpdateAssistMessage.action?message.id=${a.id}" title="修改" target="tag">
					                     <span class="label label-warning">详情</span>
					                </a>
			                      </td>
						</tr>
						</s:iterator>
						<tr>
							<td colspan="8">
								<nav>
									<ul class="pagination">
										<li <c:if test="${assist.first==assist.pageNum}">class="disabled"</c:if> ><a href="javascript:void(0);" <c:if test="${assist.first!=assist.pageNum}">  onclick="pageNow(${assist.first})"</c:if> id="first" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
										</a></li>
										
										<c:if test="${assist.totalPageNum>6}" var="paging">
										<c:forEach begin="1" end="${(assist.totalPageNum-assist.pageNum)+3<6?(assist.totalPageNum-assist.pageNum+3):6}" varStatus="s">
											<li onclick="pageNow(${assist.pageNum>=6?(assist.pageNum+s.count)-3:s.count})"
											 <c:if test="${(assist.pageNum>=6?(assist.pageNum+s.count)-3:s.count)==assist.pageNum}">class="active"</c:if>>
											<a href="javascript:void(0)">${assist.pageNum>=6?(assist.pageNum+s.count)-3:s.count}</a></li>
										</c:forEach>
										</c:if>
										<c:if test="${!paging}">
										<c:forEach begin="1" end="${assist.totalPageNum}" varStatus="s">
											<li onclick="pageNow(${s.count})" <c:if test="${s.count==assist.pageNum}">class="active"</c:if>>
											<a href="javascript:void(0)">${s.count}</a></li>
										</c:forEach>
										</c:if>
										<li <c:if test="${assist.totalPageNum==assist.pageNum}">class="disabled"</c:if> <c:if test="${assist.totalPageNum!=assist.pageNum}"> onclick="pageNow(${assist.last})"</c:if>><a href="javascript:void(0);"  id="last"
											aria-label="Next"><span aria-hidden="true">&raquo;</span>
										</a></li>
									</ul>
								</nav>
							</td>
						</tr>
						</s:if>
						<s:elseif  test="assist.list.size==0">
							<tr><td colspan="8">没有相关信息</td></tr>
						</s:elseif>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		function pageNow(i){
			$('#dataForm').attr("action",path + "/assist/assist_getAssistMessage.action?page="+i);
			$('#dataForm').submit();
		};
		function update(i,j,z){
			var c=z-1;
			var id = i;
			var state=j;
			if(state == 3){
				layer.confirm('站内消息已发送，是否确定删除？', {icon: 3}, function(index){
					$.get(path + "/assist/assist_updateAssistMessage.action?date=" + new Date().getTime(),{
						"message.id" : id,
						"message.state" : state
					},function(data) {
						if(data == 3) {
							$('.myassist').children('td:nth-child(7)').children('span').eq(c).attr("class","label label-danger").html('已删除');
							$('.myassist').children('td:last-child').eq(c).remove('.del,.com');
						}
						if (data == 1) {
							$('.myassist').children('td:nth-child(7)').children('span').eq(c).attr("class","label label-success").html('已生效');
							$('.myassist').children('td:last-child').eq(c).children('.com').css("display","none")//.remove('.label-success');
						}
						
					});
					layer.close(index);
				   	
				});
			}else{
				$.get(path + "/assist/assist_updateAssistMessage.action?date=" + new Date().getTime(),{
					"message.id" : id,
					"message.state" : state
				},function(data) {
					if(data == 3) {
						$('.myassist').children('td:nth-child(7)').children('span').eq(c).attr("class","label label-danger").html('已删除');
						$('.myassist').children('td:last-child').eq(c).remove('.del,.com');
					}
					if (data == 1) {
						$('.myassist').children('td:nth-child(7)').children('span').eq(c).attr("class","label label-success").html('已生效');
						$('.myassist').children('td:last-child').eq(c).children('.com').css("display","none")//.remove('.label-success');
					}
					
				});
			}
		}		
	</script>
	</div>
</body>
${success}
</html>