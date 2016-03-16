<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body> 
        <section class="content">
           	<div class="box-body table-responsive no-padding">
                 <table class="table table-hover">
                 	<thead>
                 		<tr>
	                      <th>序号</th>
	                      <th>课程名</th>
	                      <th>父类名称</th>
	                      <th>操作者</th>
	                      <th>操作时间</th>
	                      <th>状态</th>
	                      <th>备注</th>
	                      <th>操作</th>
	                    </tr>
                 	</thead>
                    <tbody>
                    	<s:if test="courseList.size == 0">
                    		<tr>
                    			<td colspan="8">没有相关信息</td>
                    		</tr>
                    	</s:if>
	                    <s:else>
	                    	<s:iterator value="courseList" var="course" status="v">
	                    		<tr class="dataTr">
			                      <td><s:property value="#v.count"/><input type="hidden" value="<s:property value="#v.count"/>"></td>
			                      <td>${course.name}</td>
			                      <td>${course.parentName}</td>
			                      <td>${course.userName}</td>
			                      <td><s:date name="#course.operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			                      <td>
			                      	<input type="hidden" class="tagSpan">
			                      	<s:if test="#course.state == 1">
			                      		<span class="label label-success">有效</span>
			                      	</s:if>
			                      	<s:elseif test="#course.state == 2">
			                      		<span class="label label-danger">无效</span>
			                      	</s:elseif>
			                      </td>
			                      <td>${course.memo}</td>
			                      <td>
			                      	<input type="hidden" class="courseId" value="${course.id}">
									<input type="hidden" value="${course.state}" class="status">
				                    <s:if test="#course.name != '全部课程'">
				                      	<s:if test="#course.state == 1">
				                      		<a href="javascript:void(0);" class="state label label-danger" onclick="col(${v.count})">无效</a>
				                      	</s:if>
				                      	<s:elseif test="#course.state == 2">
				                      		<a href="javascript:void(0);" class="state label label-success" onclick="col(${v.count})">有效</a>
				                      	</s:elseif>
				                     </s:if>
				                     
				                     <s:if test="#course.name != '全部课程'">
				                      	<a href="${path}/sys/course_beforeUpdate.action?courseEntity.id=${course.id}"  target="tag">
				                      		<span class="label label-warning">详情</span>
				                      	</a>
				                      </s:if>
				                     
				                     
			                      </td>
			                     </tr>
	                    	</s:iterator>
	                    </s:else>
                   </tbody>
                 </table>
          </div>
        </section>
        <script src="${path}/static/sys/course/js/data_show.js"></script>
	</body>
</html>