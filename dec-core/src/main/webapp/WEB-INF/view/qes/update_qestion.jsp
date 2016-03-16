<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link href="${path}/static/common/plugins/umeditor/themes/default/css/umeditor.min.css" type="text/css" rel="stylesheet">
		<link rel="stylesheet" href="${path}/static/common/plugins/ztree-v3.5/css/zTreeStyle/zTreeStyle.css">
	</head>
	<body>
	 <div style="overflow:auto;height: 620px;">
        <section class="content-header">
          <h1>
                             试题管理
            <small>修改试题</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          	<div class="main_content_wrap">
          	<form action="${path}/qes/qestions_sureUpdate.action?id=${qestionsEntity.id}" method="post">
	          	<div class="row">
				  <div class="col-md-1"><span class="title"><b>*</b>题　　目：</span></div>
				  <div class="col-md-11">
				  	<script type="text/plain" id="myEditor" style="width:400px;height:150px;" name="qestionsEntity.question">
							${qestionsEntity.question }
					</script>
				  </div>
				</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  附　　件：</span></div>
				  <div class="col-md-11">
				  	<input type="text" name="qestionsEntity.attachment" class="form-control" value="${qestionsEntity.attachment }">
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>题　　型：</span></div>
				  <div class="col-md-11">
				  	
				  	<s:select list="#{'1':'单选题','2':'多选题','3':'判断题'}" name="qestionsEntity.questionType" value="qestionsEntity.questionType" cssClass="form-control"></s:select>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>所属课程：</span></div>
				  <div class="col-md-11">
				  	<input type="text" name="courseName" class="form-control" id="parentName" readonly="readonly" value="${qestionsEntity.courseName }">
				  </div>
	           	</div>
	           	<div class="row">
	          		<div class="col-md-1">		
		  			</div>
	  				<div class="col-md-11">
	  					<ul id="treeDemo" class="ztree"></ul>
	  				</div>
	          	</div>
	          	<s:if test="qestionsEntity.questionType != 3">
		           	<div class="row">
		           	  <div class="col-md-1"><span class="title"> 选  项   A：</span></div>
					  <div class="col-md-11">
					  	<input type="text" name="qestionsEntity.answerA" class="form-control" value="${qestionsEntity.answerA }">
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-1"><span class="title"> 选  项   B：</span></div>
					  <div class="col-md-11">
					  	<input type="text" name="qestionsEntity.answerB" class="form-control" value="${qestionsEntity.answerB}">
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-1"><span class="title"> 选  项   C：</span></div>
					  <div class="col-md-11">
					  	<input type="text" name="qestionsEntity.answerC" class="form-control" value="${ qestionsEntity.answerC}">
					  </div>
		           	</div>
		           	<div class="row">
		           	  <div class="col-md-1"><span class="title"> 选  项   D：</span></div>
					  <div class="col-md-11">
					  	<input type="text" name="qestionsEntity.answerD" class="form-control" value="${qestionsEntity.answerD }">
					  </div>
		           	</div>
	           	</s:if>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>答　　案：</span></div>
				  <div class="col-md-11">
				  	<s:if test="qestionsEntity.questionType == 1">
				  		<s:radio list="#{'A':'A','B':'B','C':'C','D':'D'}" name="qestionsEntity.answer" value="qestionsEntity.answer"></s:radio>
				  	</s:if>
				  	<s:elseif test="qestionsEntity.questionType == 2">
				  		<s:checkboxlist list="#{'A':'A','B':'B','C':'C','D':'D'}" name="qestionsEntity.answer" value="#request.multiNumber"></s:checkboxlist>
				  	</s:elseif>
				  	<s:elseif test="qestionsEntity.questionType == 3">
				  		<s:radio list="#{'对':'对','错':'错'}" name="qestionsEntity.answer" value="qestionsEntity.answer"></s:radio>
				  	</s:elseif>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"><b>*</b>难　　度：</span></div>
				  <div class="col-md-11">
				  	<s:select list="#{'1':'低','2':'中','3':'较高','4':'高'}" name="qestionsEntity.difficulty" value="qestionsEntity.difficulty" cssClass="form-control"></s:select>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title">  分　　析：</span></div>
				  <div class="col-md-11">
				  	<input type="text" name="qestionsEntity.analysis" class="form-control" value="${qestionsEntity.analysis }">
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"> 关  键  词：</span></div>
				  <div class="col-md-11">
				  	<input type="text" name="qestionsEntity.keywords" class="form-control" value="${qestionsEntity.keywords }">
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"> 状       态：</span></div>
				  <div class="col-md-11">
				  	<select class="form-control" name="qestionsEntity.state">
				  		<s:if test="qestionsEntity.state == 1">
				  			<option value="1" selected="selected">有效</option>
				  			<option value="2">无效</option>
				  		</s:if>
				  		<s:if test="qestionsEntity.state == 2">
				  			<option value="1">有效</option>
				  			<option value="2" selected="selected">无效</option>
				  		</s:if>
				  	</select>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"><span class="title"> 备　　注：</span></div>
				  <div class="col-md-11">
				  	<textarea class="form-control" id="memo" name="qestionsEntity.memo">${qestionsEntity.memo}</textarea>
				  </div>
	           	</div>
	           	<div class="row">
	           	  <div class="col-md-1"></div>
				  <div class="col-md-11">
				  	<input type="submit" value="修改" class="btn btn-primary btn-sm">
				  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
				  </div>
	           	</div>
	         </form>
	         </div>
          </div>
        </section>
    <script type="text/javascript" charset="utf-8" src="${path}/static/common/plugins/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${path}/static/common/plugins/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${path}/static/common/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>
    <script src="${path}/static/common/plugins/ztree-v3.5/js/jquery.ztree.core-3.5.min.js"></script>
    <script>
    	var courseJson = '${courseJson}';
    	//实例化编辑器
	    var um = UM.getEditor('myEditor');
    </script>
    <script src="${path}/static/qes/js/insert_qes.js"></script>
	</div>
	</body>
	${error}
</html>