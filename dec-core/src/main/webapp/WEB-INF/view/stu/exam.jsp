<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@ include file="/WEB-INF/view/common/base.jspf"%>
<link href="${path}/static/stu/css/exam.css" rel="stylesheet">
</head>
<body>
	<!-- 浮动div开始 -->
	<div id="rt-div">
		<div id="show-title">
			剩余时间：<span></span>
		</div>
		<div id="show_box">
			<div id="single-slibar">
				<p>单选题：</p>
				<div class="single-slibar-number">
					<s:iterator value="qesExamPaperEntity.listSingle" var="single" status="v">
						<a href="#so${v.count}" class="single-d ${single.id}" name="${single.id}"><s:property value="#v.count" /></a>
					</s:iterator>
				</div>
			</div>
			<div id="mutli-slibar">
				<p>多选题：</p>
				<div class="mutli-slibar-number">
					<s:iterator value="qesExamPaperEntity.listMulti" var="multi" status="v">
						<a href="#mo${v.count}" class="multi-d ${multi.id}" name="${multi.id}"><s:property value="#v.count" /></a>
					</s:iterator>
				</div>
			</div>
			<div id="judge-slibar">
				<p>判断题：</p>
				<div class="judge-slibar-number">
					<s:iterator value="qesExamPaperEntity.listJudge" var="judge" status="v">
						<a href="#ju${v.count}" class="judge-d ${judge.id}" name="${judge.id}"><s:property value="#v.count" /></a>
					</s:iterator>
				</div>
			</div>
		</div>
		<div id="show_btn">
			<a href="${path}/stu/stu_submitExam.action?id=${id}&qesExamPaperEntity.passPoint=${qesExamPaperEntity.passPoint}" class="btn btn-primary btn-sm" target="_parent">提交答案</a>
		</div>
	</div>
	<!-- 浮动div结束 -->
	<div id="container">
		<div class="header">
			<h1>${qesExamPaperEntity.name}</h1>
			<div class="header_content">
				(<span class="header_title">考试时间：${qesExamPaperEntity.totalMinutes}分钟</span> <span class="header_title">总分数：${qesExamPaperEntity.totalPoint}分</span> <span class="header_title">通过分数：${qesExamPaperEntity.passPoint}分</span>)
			</div>
		</div>
		<div class="content">
			<div id="single">
				<h5>
					<span>第一部分：单选题</span>(本大题共<em>${qesExamPaperEntity.examPaperEntity.singleOptionNumber}</em>小题，每小题<em>${qesExamPaperEntity.examPaperEntity.singleOptionEachPoint}</em>分，共<em>${qesExamPaperEntity.examPaperEntity.singleOptionEachPoint * qesExamPaperEntity.examPaperEntity.singleOptionNumber}</em>分)
				</h5>
				<s:iterator value="qesExamPaperEntity.listSingle" var="single" status="v">
					<div class="single-content">
						<div class="single-content-qes" name="${single.id}">
							<a name="so${v.count}"> <span>第<s:property value="#v.count" />题
							</span>
							</a>,${single.question}（）
						</div>
						<ul>
							<li>A.${single.answerA}</li>
							<li>B.${single.answerB}</li>
							<li>C.${single.answerC}</li>
							<li>D.${single.answerD}</li>
						</ul>
						<div class="single-content-answer">
							<strong>选择答案：</strong> <label><input type="radio" name="${single.id}" class="answer" value="A">A</label> <label><input type="radio" name="${single.id}" class="answer" value="B">B</label> <label><input type="radio" name="${single.id}" class="answer" value="C">C</label> <label><input type="radio" name="${single.id}" class="answer" value="D">D</label>
						</div>
					</div>
				</s:iterator>
			</div>
			<div id="multi">
				<h5>
					<span>第二部分：多选题</span>(本大题共<em>${qesExamPaperEntity.examPaperEntity.multiOptionNumber}</em>小题，每小题<em>${qesExamPaperEntity.examPaperEntity.multiOptionEachPoint}</em>分，共<em>${qesExamPaperEntity.examPaperEntity.multiOptionEachPoint * qesExamPaperEntity.examPaperEntity.multiOptionNumber}</em>分)
				</h5>
				<s:iterator value="qesExamPaperEntity.listMulti" var="multi" status="v">
					<div class="single-content">
						<div class="single-content-qes" name="${multi.id}">
							<a name="mo${v.count}"> <span>第<s:property value="#v.count" />题
							</span>
							</a>,${multi.question}（）
						</div>
						<ul>
							<li>A.${multi.answerA}</li>
							<li>B.${multi.answerB}</li>
							<li>C.${multi.answerC}</li>
							<li>D.${multi.answerD}</li>
						</ul>
						<div class="single-content-answer">
							<strong>选择答案：</strong> <label><input type="checkbox" name="${multi.id}" class="multIanswer" value="A">A</label> <label><input type="checkbox" name="${multi.id}" class="multIanswer" value="B">B</label> <label><input type="checkbox" name="${multi.id}" class="multIanswer" value="C">C</label> <label><input type="checkbox" name="${multi.id}" class="multIanswer" value="D">D</label>
						</div>
					</div>
				</s:iterator>
			</div>
			<div id="judge">
				<h5>
					<span>第三部分：判断题</span>(本大题共<em>${qesExamPaperEntity.examPaperEntity.judgeNumber}</em>小题，每小题<em>${qesExamPaperEntity.examPaperEntity.judgeEachPoint}</em>分，共<em>${qesExamPaperEntity.examPaperEntity.judgeEachPoint * qesExamPaperEntity.examPaperEntity.judgeNumber}</em>分)
				</h5>
				<s:iterator value="qesExamPaperEntity.listJudge" var="judge" status="v">
					<div class="single-content">
						<div class="single-content-qes" name="${judge.id}">
							<a name="ju${v.count}"> <span>第<s:property value="#v.count" />题
							</span>
							</a>,${judge.question}（）
						</div>
						<div class="single-content-answer">
							<strong>选择答案：</strong> <label><input type="radio" name="${judge.id}" class="answer" value="对">正确</label> <label><input type="radio" name="${judge.id}" class="answer" value="错">错误</label>
						</div>
					</div>
				</s:iterator>
			</div>
			<div id="asserdd" style="display: none;">
				<s:if test="examAnswerList!=null">
					<s:iterator value="examAnswerList" var="stuAsser">
						<div class="stuAsser" name="${stuAsser.id}">${stuAsser.answer}</div>
					</s:iterator>
				</s:if>
			</div>
		</div>
	</div>
</body>
<script src="${path}/static/stu/js/exam.js"></script>
<script src="${path}/static/stu/js/jquery.floatDiv.js"></script>
<script defer="defer">
	//var examAnswerList = new Array();
	//examAnswerList='${examAnswerList}';
	var singleOptionNumber = '${qesExamPaperEntity.examPaperEntity.singleOptionNumber}';
	var multiOptionNumber = '${qesExamPaperEntity.examPaperEntity.multiOptionNumber}';
	var judgeNumber = '${qesExamPaperEntity.examPaperEntity.judgeNumber}';
	var pId = '${id}';
	var totalMinutes = '${mm}';

	$(function() {
		$("#rt-div").floatdiv({
			right : "0",
			top : "122px"
		});//设置浮动层靠右距离为0，靠上距离50px 根据需求调整
	});
	var time = '${mm}';
	var leftsecond = parseInt(time * 60);
	function showCountDown(leftsecond) {
		window.setInterval(function() {
			if (leftsecond > 0) {
				day = Math.floor(leftsecond / (60 * 60 * 24));
				hour = Math.floor(leftsecond / (60 * 60)) - (day * 24);
				minute = Math.floor(leftsecond / 60) - (day * 24 * 60)
						- (hour * 60);
				second = Math.floor(leftsecond) - (day * 24 * 60 * 60)
						- (hour * 60 * 60) - (minute * 60);
			}
			if (minute <= 9)
				minute = '0' + minute;
			if (second <= 9)
				second = '0' + second;
			$('#show-title span').html(hour + ':' + minute + ":" + second);
			leftsecond--;
		}, 1000);
	}
	$(function() {
		showCountDown(leftsecond);
	});

	function getnums() {
		var getTime = $('#show-title span').html();
		var m = --totalMinutes;
		if (m == 0) {
			alert("考试时间已到!");
			location = "${path}/stu/stu_submitExam.action?id=${id}&qesExamPaperEntity.passPoint=${qesExamPaperEntity.passPoint}";
			window.close();
		} else {
			$.get(path + "/stu/stu_submitTime.action?date="
					+ new Date().getTime(), {
				"id" : pId,
				"minutes" : m
			}, function(data) {

			});
		}
	}
</script>
</html>