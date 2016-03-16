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
            <small>新增试卷</small>
            <small><strong>(*为必填项)</strong></small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
          <div class="main_content_wrap">
          <s:form action="qesExamPaper!addExamPaper" method="post"   autocomplete="off" id="infoForm">
	       	
	       	<div class="row">
			  <div class="col-md-1"><span class="title"> <b>*</b>试 卷 名 称：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.name" class="form-control"></s:textfield>
			  </div>
			</div>
			
	       	<div class="row">
			  <div class="col-md-1"><span class="title"> <b>*</b>试 卷 类 型：</span></div>
			  <div class="col-md-11">
			  	<s:select name="examPaperEntity.paperType" class="form-control" list="#{'1':'平时测试','2':'结业测试'}"></s:select>
			  </div>
			</div>
			
			<div class="row">
			  <div class="col-md-1"><span class="title"> <b>*</b>选 择 课 程：</span></div>
			  <div class="col-md-11">
			  	<s:select name="examPaperEntity.courseName"  class="form-control" list="listClazzName"></s:select>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title"> <b>*</b>总     　   分：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.totalPoint"  class="form-control" readonly="true" id="count"></s:textfield>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title"> <b>*</b>通 过 分 数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.passPoint" class="form-control"></s:textfield>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title"> <b>*</b>总 分 钟 数：</span></div>
			  <div class="col-md-11">
			 	<s:textfield name="examPaperEntity.totalMinutes"  class="form-control"></s:textfield>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title">  单选题个数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.singleOptionNumber" class="form-control" id="singleNum" onmouseout="calc()"></s:textfield>
			  </div>
			</div>	

			<div class="row">
			  <div class="col-md-1"><span class="title">  单选题分数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.singleOptionEachPoint" class="form-control" id="singlePoint" onmouseout="calc()"></s:textfield>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title">  单选题难度：</span></div>
			  <div class="col-md-11">
			  	<s:select name="examPaperEntity.singleOptionDifficulty" class="form-control" list="#{'1':'--低--','2':'--中--','3':'-较高-','4':'--高--'}"></s:select>
			  </div>
			</div>	

			<div class="row">
			  <div class="col-md-1"><span class="title">  多选题个数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.multiOptionNumber" class="form-control" id="multieNum" onmouseout="calc()"></s:textfield>
			  </div>
			</div>	

			<div class="row">
			  <div class="col-md-1"><span class="title">  多选题分数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.multiOptionEachPoint" class="form-control" id="multiePotin" onmouseout="calc()"></s:textfield>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title">  多选题难度：</span></div>
			  <div class="col-md-11">
			  	<s:select name="examPaperEntity.multiOptionDifficulty" class="form-control" list="#{'1':'--低--','2':'--中--','3':'-较高-','4':'--高--'}"></s:select>
			  </div>
			</div>											

			<div class="row">
			  <div class="col-md-1"><span class="title">  判断题个数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.judgeNumber" class="form-control" id="judgeNum" onmouseout="calc()"></s:textfield>
			  </div>
			</div>	

			<div class="row">
			  <div class="col-md-1"><span class="title">  判断题分数：</span></div>
			  <div class="col-md-11">
			  	<s:textfield name="examPaperEntity.judgeEachPoint" class="form-control" id="judgePoint" onmouseout="calc()"></s:textfield>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"><span class="title">  判断题难度：</span></div>
			  <div class="col-md-11">
			  	<s:select name="examPaperEntity.judgeDifficulty" class="form-control" list="#{'1':'--低--','2':'--中--','3':'-较高-','4':'--高--'}"></s:select>
			  </div>
			</div>

			<div class="row">
			  <div class="col-md-1"><span class="title"> 备  注  信  息：</span></div>
			  <div class="col-md-11">
			  	<s:textarea name="examPaperEntity.memo" class="form-control" id="memo"></s:textarea>
			  </div>
			</div>	
			
			<div class="row">
			  <div class="col-md-1"></div>
			  <div class="col-md-11">
			  	<s:submit value="确定" cssClass="btn btn-primary btn-sm" id="btnsub"></s:submit>
			  	<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
			  </div>
			</div>			

          </s:form>
          </div>
          </div>
        </section>
        </div>
	</body>
	<script src="${path}/static/qes/js/add_qes_exam_paper.js"></script>
</html>