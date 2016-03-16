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
                             用户管理
            <small>用户显示</small>
          </h1>
          <ol class="breadcrumb">
            <a href="${path}/sys/user_toUserPage.action" class="btn btn-primary btn-sm">新增用户</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
				
       			<form class="form-inline" action="${path}/sys/user_findUserByCondition.action" method="post" id="dataForm">
          			<div class="form-group">
	          			<label class="sr-only">姓名：</label>
	          			<p class="form-control-static">姓名：</p>
	          		</div>
					<div class="form-group">
	          			<label class="sr-only">姓名：</label>
	          			 <s:textfield name="user2.name" cssClass="form-control"/>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">角色：</label>
	          			<p class="form-control-static">角色：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">角色：</label>
	          			<s:select list="roleList" cssClass="form-control" name="roleId" listKey="id" listValue="name" id="select_one" headerKey=""  headerValue="全部"/>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
	          			<s:select list="#{'1':'启用','2':'禁用'}" cssClass="form-control" name="user2.state" headerKey=""  headerValue="全部"/>
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
	                      <th>账号</th>
	                      <th>姓名</th>
	                      <th>性别</th>
	                      <th>电话</th>
	                      <th>邮箱</th>
	                      <th>创建时间</th>
	                      <th>所属角色</th>
	                      <th>所属班级</th>
	                      <th>状态</th>
	                      <th>备注</th>
	                      <th>操作</th>
	                    </tr>
                 	</thead>
                    <tbody>
                    <s:if test="userPageModel.list.size == 0">
                    	<tr>
                    		<td colspan="12">没有相关信息</td>
                    	</tr>
                    </s:if>
                    <s:else>
                    <s:iterator value="userPageModel.list" var="userlist" status="v">
	                    <tr>
	                      <td><s:property value="#v.count+(userPageModel.pageNum-1)*userPageModel.pageSize"/></td>
	                      <td>${userlist.code}</td>
	                      <td>${userlist.name}</td>
						  <td>
						  	<s:if test="sex==1">
						  		<span>男</span>
						  	</s:if>
						  	<s:if test="sex==2">
	                      		<span>女</span>
	                      	</s:if>
	                      </td>
	                      <td>${userlist.phone}</td>
	                      <td>${userlist.email}</td>
	                      <td>
	                      	<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> 
	                      </td>
	                      <td>${userlist.roleName}</td>
	                       <td>
	                       	<c:if test="${not empty userlist.clazzName}">
	                       		${userlist.clazzName}
	                       	</c:if>
	                       	<c:if test="${empty userlist.clazzName}">
	                       		无
	                       	</c:if>
	                       </td>
	                      <td>
	                      	<input type="hidden" class="tagSpan">
		                     <s:if test="state==1">
		                      	<span class="label label-success">有效</span>
		                     </s:if>
		                     <s:if test="state==2">
		                       <span class="label label-danger">无效</span>
		                     </s:if>
	                     </td>
	                      <td>${userlist.memo}</td>
	                      <td>        
	                        <input type="hidden" class="userId" value="${userlist.id}">
							<input type="hidden" value="${userlist.state}" class="clazzState">
	                      	
	                      	<c:if test="${userlist.state == 1}">
	                      		<a href="javascript:void(0);" onclick="col(${v.count})" class="state label label-danger">禁用</a>
	                      	</c:if>
	                      	<c:if test="${userlist.state == 2}">
	                      		<a href="javascript:void(0);" onclick="col(${v.count})" class="state label label-success">启用</a>
	                      	</c:if>
	                      	<a href="${path}/sys/user_toUserUpdate.action?id=<s:property value="#userlist.id"/>">
				                		<span class="label label-warning">详情</span>
				            </a>
	                      <c:if test="${userlist.password == 'e10adc3949ba59abbe56e057f20f883e'}">
	                      	<span class="label label-info">初始密码</span>
	                      </c:if>
	                      <c:if test="${userlist.password != 'e10adc3949ba59abbe56e057f20f883e'}">
		                      <input type="hidden" class="userIds" value="${userlist.id}">
		                      <input type="hidden" class="userpw" value="${userlist.password}">
		                      <a href="javascript:void(0);"  onclick="cols(${v.count});"><span class="label label-danger">重置密码</span></a>
 	                       </c:if> 
	                      </td>
	                    </tr>
	                   </s:iterator>
	                  </s:else>
	                   <tr>
	                    <td colspan="12">
		                        <ul class="pagination">
									<li <c:if test="${userPageModel.first==userPageModel.pageNum}">class="disabled"</c:if> >
										<a href="javascript:void(0);" <c:if test="${userPageModel.first!=userPageModel.pageNum}">  onclick="pageNow(${userPageModel.first})"</c:if> id="first" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span>
										</a>
									</li>
									<c:if test="${userPageModel.totalPageNum>5}" var="paging">
										<c:forEach begin="1" end="${userPageModel.totalPageNum-userPageModel.pageNum+3<5?userPageModel.totalPageNum-userPageModel.pageNum+userPageModel.totalPageNum%5+1:5}" varStatus="s">
											<li onclick="pageNow(${userPageModel.pageNum>=5?(userPageModel.pageNum-2)+s.count:s.count})" <c:if test="${(userPageModel.pageNum>=5?(userPageModel.pageNum-2)+s.count:s.count)==userPageModel.pageNum}">class="active"</c:if>>
												<a href="javascript:void(0)">${userPageModel.pageNum>=5?(userPageModel.pageNum-2)+s.count:s.count}</a>
											</li>
										</c:forEach>
									</c:if>
									
									<c:if test="${!paging}">
										<c:forEach begin="1" end="${userPageModel.totalPageNum}" varStatus="s">
											<li onclick="pageNow(${s.count})" <c:if test="${s.count==userPageModel.pageNum}">class="active"</c:if>>
												<a href="javascript:void(0)">${s.count}</a>
											</li>
										</c:forEach>
									</c:if>
									<li <c:if test="${userPageModel.totalPageNum==userPageModel.pageNum}">class="disabled"</c:if> 
										<c:if test="${userPageModel.totalPageNum!=userPageModel.pageNum}"> onclick="pageNow(${userPageModel.last})"</c:if>>
										<a href="javascript:void(0);"  id="last"
											aria-label="Next"><span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</ul>
	                    </td>
	                   </tr>
                   </tbody>
                 </table>
             </div>
          </div>
        </section>
        <script  src="${path}/static/sys/user/js/data_show.js"></script>
        <script type="text/javascript">
		function pageNow(i){
			console.info(i);
			$('#dataForm').attr("action",path + "/sys/user_findUserByCondition.action?pageNo="+i);
			$('#dataForm').submit();
		}
	</script>
	${success}
	</body>
</html>