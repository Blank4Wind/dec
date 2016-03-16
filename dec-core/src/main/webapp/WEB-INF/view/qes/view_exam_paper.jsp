<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link href="${path}/static/qes/css/view_exam_paper.css" rel="stylesheet">
	</head>
	<body>
	<div style="overflow:auto; height: 630px;">
       	<div id="container">
           <div class="header">
				<h1>${qesExamPaperEntity.name}</h1>
				<div class="header_content">
					<span class="header_title">所属课程：${qesExamPaperEntity.courseName}</span><br>
					<span class="header_title">试卷类型：
						<s:if test="qesExamPaperEntity.paperType==1">
							<span>平时测试</span>
						</s:if>
						<s:else>
							<span>结业测试</span>
						</s:else>
					</span><br>
					(<span class="header_title">考试时间：${qesExamPaperEntity.totalMinutes}分钟</span>
					<span class="header_title">总分数：${qesExamPaperEntity.totalPoint}分</span>
					<span class="header_title">通过分数：${qesExamPaperEntity.passPoint}分</span>)
				</div>
			</div>
					<div class="content">
			<div id="single">
				<h5>
					<span>第一部分：单选题</span>(本大题共<em>${examPaperEntity.singleOptionNumber}</em>小题，每小题<em>${examPaperEntity.singleOptionNumber}</em>分，共<em>${examPaperEntity.singleOptionNumber * examPaperEntity.singleOptionNumber}</em>分)
				</h5>
				<s:iterator value="qesExamPaperEntity.listSingle" var="single"
					status="v">
					<div class="single-content">
						<div class="single-content-qes" name="${single.id}">
								<span>第<s:property value="#v.count" />题</span>、${single.question}（）
						</div>
						<ul>
							<li>A.${single.answerA}</li>
							<li>B.${single.answerB}</li>
							<li>C.${single.answerC}</li>
							<li>D.${single.answerD}</li>
						</ul>
						<div class="single-content-answer">
							<strong>正确答案：</strong> <span>${single.answer}</span><br>
							<strong>关键字：</strong> <span>${single.keywords}</span>
						</div>
					</div>
				</s:iterator>
			</div>
			<div id="multi">
				<h5>
					<span>第二部分：多选题</span>(本大题共<em>${examPaperEntity.multiOptionNumber}</em>小题，每小题<em>${examPaperEntity.multiOptionEachPoint}</em>分，共<em>${examPaperEntity.multiOptionNumber * examPaperEntity.multiOptionEachPoint}</em>分)
				</h5>
				<s:iterator value="qesExamPaperEntity.listMulti" var="multi"
					status="v">
					<div class="single-content">
						<div class="single-content-qes" name="${multi.id}">
								<span>第<s:property value="#v.count" />题</span>、${multi.question}（）
						</div>
						<ul>
							<li>A.${multi.answerA}</li>
							<li>B.${multi.answerB}</li>
							<li>C.${multi.answerC}</li>
							<li>D.${multi.answerD}</li>
						</ul>
						<div class="single-content-answer">
							<strong>正确答案：</strong> <span>${multi.answer}</span><br>
							<strong>关键字：</strong> <span>${multi.keywords}</span>
						</div>
					</div>
				</s:iterator>
			</div>
			<div id="judge">
				<h5>
					<span>第三部分：判断题</span>(本大题共<em>${examPaperEntity.judgeNumber}</em>小题，每小题<em>${examPaperEntity.judgeEachPoint}</em>分，共<em>${examPaperEntity.judgeNumber * examPaperEntity.judgeEachPoint}</em>分)
				</h5>
				<s:iterator value="qesExamPaperEntity.listJudge" var="judge"
					status="v">
					<div class="single-content">
						<div class="single-content-qes" name="${judge.id}">
								<span>第<s:property value="#v.count" />题</span>、${judge.question}（）
						</div>
						<div class="single-content-answer">
							<strong>正确答案：</strong> <span>${judge.answer}</span><br>
							<strong>关键字：</strong> <span>${judge.keywords}</span>
						</div>
					</div>
				</s:iterator>
			</div>
		</div>
		</div>
      </div>
	</body>
</html>