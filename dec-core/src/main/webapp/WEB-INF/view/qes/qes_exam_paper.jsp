<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
	</head>
	<body>
		 <div style="overflow:auto; height: 620px;">
        <section class="content-header">
          <h1>
                试卷管理        
            <small>试卷管理</small>
          </h1>
          <ol class="breadcrumb">
          	<a href="${path}/qes/qesExamPaper_toAddQesExamPaper.action" class="btn btn-primary btn-sm">新增试卷</a>
          </ol>
        </section>
        <section class="content">
          <div class="box">
          	<div class="box-tools">
				<s:form id="dataForm" class="form-inline" action="qesExamPaper!toQesExamPaper" method="post">
          			<div class="form-group">
	          			<label class="sr-only">名称：</label>
	          			<p class="form-control-static">名称：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">名称：</label>
	          			<s:textfield name="condition.name" class="form-control"/>
	          		</div>
	          		<div class="form-group select">
	          			<label class="sr-only">状态：</label>
	          			<p class="form-control-static">状态：</p>
	          		</div>
	          		<div class="form-group">
	          			<label class="sr-only">状态：</label>
							<s:select name="condition.state" list="#{'0':'全部','1':'启用','2':'禁用' }" class="form-control"></s:select>
	          		</div>
	          		<div class="form-group">
	          			<input type="submit" value="查询" class="btn btn-primary btn-sm">
	          		</div>
          		</s:form>   		
            </div>
			<div class="box-body table-responsive no-padding">
			<s:if test="pageModel.list!=null&&pageModel.list.size!=0">
			<table class="table table-hover">
				<tr class="ui-jqgrid-labels" role="rowheader">
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">序号</span></th>
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">试卷名称</span></th>
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">所属课程</span></th>
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">总分分数</span></th>
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">通过分数</span></th>
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">总时间</span></th>
					<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">状态</span></th>
					<th colspan="2" class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">操作</span></th>
				</tr>
				
				<s:iterator var="list" value="pageModel.list" status="v">
					<tr>
						<td class="ui-state-default ui-th-column ui-th-ltr"><span>${v.count}</span></td>
						<td class="ui-state-default ui-th-column ui-th-ltr"><s:property value="#list.name"></s:property></td>
						<td class="ui-state-default ui-th-column ui-th-ltr"><s:property value="#list.courseName"></s:property></td>
						<td class="ui-state-default ui-th-column ui-th-ltr"><s:property value="#list.totalPoint"></s:property></td>
						<td class="ui-state-default ui-th-column ui-th-ltr"><s:property value="#list.passPoint"></s:property></td>
						<td class="ui-state-default ui-th-column ui-th-ltr"><s:property value="#list.totalMinutes"></s:property></td>
						<td class="ui-state-default ui-th-column ui-th-ltr">
							<input type="hidden" class="tagSpan">
							<s:if test="#list.state==1"><span class="label label-success">有效</span></s:if>
							<s:else><span class="label label-danger">无效</span></s:else>
						</td>
						<td class="ui-state-default ui-th-column ui-th-ltr">
						<input type="hidden" value="${list.id}" class="clazzId">
						<input type="hidden" value="${list.state}" class="clazzState">
						<s:if test="#list.state==1">
							<a href="javascript:void(0);" onclick="col(${v.count})" class="tate label label-danger">禁用</a>
						</s:if><s:else>
							<a href="javascript:void(0);" onclick="col(${v.count})" class="tate label label-success">启用</a>
						</s:else>
						<a class="label label-warning" data-toggle="modal" data-target="#exampleModal" data-whatever="${list.name}" data-coursename="${list.courseName}" data-examtype="${list.paperType}" data-totalpoint="${list.totalPoint}" data-passpoint="${list.passPoint}" data-totalminutes="${list.totalMinutes}" data-singleoptionnumber="${list.singleOptionNumber}" data-singleoptioneachpoint="${list.singleOptionEachPoint}" data-singleoptiondifficulty="${list.singleOptionDifficulty}" data-multioptionnumber="${list.multiOptionNumber}" data-multioptioneachpoint="${list.multiOptionEachPoint}" data-multioptiondifficulty="${list.multiOptionDifficulty}" data-judgenumber="${list.judgeNumber}" data-judgeeachpoint="${list.judgeEachPoint}" data-judgedifficulty="${list.judgeDifficulty}" data-opreatetime="${list.opreateTime}" data-state="${list.state}" data-memo="${list.memo}" data-username="${list.userName}">详情</a> 
						<a class="label label-info" href="${path}/qes/qesExamPaper_viewExamPaper.action?id=${list.id}">试卷预览</a>
						<a href="javascript:void(0);" onclick="ccoll('${list.id}')" class="label label-default">重新生成</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="exampleModalLabel"></h4>
			      </div>
			      <div class="modal-body">
			      <table class="infotb">
			      	<tr class="infotr"><td class="infotd"><span class="infosp"> 试卷名称:</span></td><td class="infotd"><span class="epename"></span></td><td class="infotd"><span class="infosp"> 课程名称:</span></td><td class="infotd"><span class="coname"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp"> 考试类型:</span></td><td class="infotd"><span class="epeexamtype"></span></td><td class="infotd"><span class="infosp">总 分 数:</span></td><td class="infotd"><span class="totalpoint"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp"> 通过分数:</span></td><td class="infotd"><span class="passpoint"></span></td><td class="infotd"><span class="infosp"> 考试时长:</span></td><td class="infotd"><span class="totalminutes"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp">状       态:</span></td><td class="infotd"><span class="state"></span></td><td class="infotd"><span class="infosp">操 作 者:</span></td><td class="infotd"><span class="username"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp"> 操作时间:</span></td><td class="infotd"><span class="opreatetime"></span></td><td class="infotd"><span class="infosp"> 单选题数:</span></td><td class="infotd"><span class="singleoptionnumber"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp">单选题分数:</span></td><td class="infotd"><span class="singleoptioneachpoint"></span></td><td class="infotd"><span class="infosp">单选题难度:</span></td><td class="infotd"><span class="singleoptiondifficulty"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp"> 多选题数:</span></td><td class="infotd"><span class="multioptionnumber"></span></td><td class="infotd"><span class="infosp"> 判断题数:</span></td><td class="infotd"><span class="judgenumber"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp">多选题分数:</span></td><td class="infotd"><span class="multioptioneachpoint"></span></td><td class="infotd"><span class="infosp">判断题分数:</span></td><td class="infotd"><span class="judgeeachpoint"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp">多选题难度:</span></td><td class="infotd"><span class="multioptiondifficulty"></span></td><td class="infotd"><span class="infosp">判断题难度:</span></td><td class="infotd"><span class="judgedifficulty"></span></td></tr>
			      	<tr class="infotrr"><td></td></tr>
			      	<tr class="infotr"><td class="infotd"><span class="infosp"> 备注信息:</span></td><td colspan="3"><span class="memo"></span></td></tr>
			      </table>
			      </div>
			    </div>
			  </div>
			</div>
			
			<table>
				<tr>
					<td colspan="8">
						<nav>
							<ul class="pagination">
								<li <c:if test="${pageModel.first==pageModel.pageNum}">class="disabled"</c:if> ><a href="javascript:void(0);" <c:if test="${pageModel.first!=pageModel.pageNum}">  onclick="pageNow(${pageModel.first})"</c:if> id="first" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
								</a></li>
								
								<c:if test="${pageModel.totalPageNum>5}" var="paging">
								<c:forEach begin="1" end="${pageModel.totalPageNum-pageModel.pageNum+3<5?pageModel.totalPageNum-pageModel.pageNum+pageModel.totalPageNum%5+1:5}" varStatus="s">
									<li onclick="pageNow(${pageModel.pageNum>=5?(pageModel.pageNum-2)+s.count:s.count})" <c:if test="${(pageModel.pageNum>=5?(pageModel.pageNum-2)+s.count:s.count)==pageModel.pageNum}">class="active"</c:if>>
									<a href="javascript:void(0);">${pageModel.pageNum>=5?(pageModel.pageNum-2)+s.count:s.count}</a></li>
								</c:forEach>
								</c:if>
								
								<c:if test="${!paging}">
								<c:forEach begin="1" end="${pageModel.totalPageNum}" varStatus="s">
									<li onclick="pageNow(${s.count})" <c:if test="${s.count==pageModel.pageNum}">class="active"</c:if>>
									<a href="javascript:void(0);">${s.count}</a></li>
								</c:forEach>
								</c:if>
								<li <c:if test="${pageModel.totalPageNum==pageModel.pageNum}">class="disabled"</c:if> <c:if test="${pageModel.totalPageNum!=pageModel.pageNum}"> onclick="pageNow(${pageModel.last})"</c:if>><a href="javascript:void(0);"  id="last"
									aria-label="Next"><span aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</nav>
					</td>
				</tr>
			</table>
			</s:if><s:else>
				<table class="table table-hover">
					<tr class="ui-jqgrid-labels" role="rowheader">
						<th class="ui-state-default ui-th-column ui-th-ltr"><span class="ui-state-default ui-th-column ui-th-ltr">没有信息</span></th>
					</tr>
				</table>
			</s:else>
          </div>
          </div>
        </section>
        </div>
	</body>
	<script src="${path}/static/qes/js/qes_exam_paper.js"></script>
</html>