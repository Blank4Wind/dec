function calc() {
	var judP = $('#count').val(0);
	var sinN = $('#singleNum').val();
	var sinP = $('#singlePoint').val();
	var mulN = $('#multieNum').val();
	var mulP = $('#multiePotin').val();
	var judN = $('#judgeNum').val();
	var judP = $('#judgePoint').val();
	var count;
	if (sinN == null || sinN == "") {
		sinN = 0;
	}
	if (mulN == null || mulN == "") {
		mulN = 0;
	}
	if (judN == null || judN == "") {
		judN = 0;
	}
	if (sinP == null || sinP == "") {
		sinP = 0;
	}
	if (mulP == null || mulP == "") {
		mulP = 0;
	}
	if (judP == null || judP == "") {
		judP = 0;
	}

	count = sinN * sinP + mulN * mulP + judN * judP;
	var judP = $('#count').val(count);
}


$('#infoForm').validator({
	rules : {
		//自定义一个规则，用来代替remote（注意：要把$.ajax()返回出来）
		singleOptionNumbervalidator : function(element1) {
			return $.ajax({
				url : path + '/qes/qesExamPaper_calcSingleNumber.action?date=' + new Date().getTime(),
				type : 'post',
				data : element1.name + '=' + element1.value,
				success : function(d) {
					if (d == '已超过单选题总个数,最大为') {
						$('#btnsub').prop("disabled", true);
					} else {
						$('#btnsub').prop("disabled", false);
					}
				}
			});
		},
		multiOptionNumbervalidator : function(element2) {
			return $.ajax({
				url : path + '/qes/qesExamPaper_calcMultiNumber.action?date=' + new Date().getTime(),
				type : 'post',
				data : element2.name + '=' + element2.value,
				success : function(d) {
					if (d == '已超过多选题总个数,最大为') {
						$('#btnsub').prop("disabled", true);
					} else {
						$('#btnsub').prop("disabled", false);
					}
				}
			});
		},
		judgeNumbervalidator : function(element3) {
			return $.ajax({
				url : path + '/qes/qesExamPaper_calcJudgeNumber.action?date=' + new Date().getTime(),
				type : 'post',
				data : element3.name + '=' + element3.value,
				success : function(d) {
					if (d == '已超过判断题总个数,最大为') {
						$('#btnsub').prop("disabled", true);
					} else {
						$('#btnsub').prop("disabled", false);
					}
				}
			});
		}
	},
	fields : {
		'examPaperEntity.name' : 'required;',
		'examPaperEntity.paperType' : 'required;',
		'examPaperEntity.courseName' : 'required;',
		'examPaperEntity.totalPoint' : 'required;',
		'examPaperEntity.passPoint' : 'required;',
		'examPaperEntity.totalMinutes' : 'required;',
		'examPaperEntity.memo' : 'length[0~255, true];filter(\'<>/);',
		'examPaperEntity.singleOptionNumber' : 'digits;range[1~100];examPaperEntity.singleOptionNumber; singleOptionNumbervalidator;',
		'examPaperEntity.singleOptionEachPoint' : 'digits;range[1~100]',
		'examPaperEntity.multiOptionNumber' : 'digits;range[1~100];examPaperEntity.multiOptionNumber; multiOptionNumbervalidator;',
		'examPaperEntity.multiOptionEachPoint' : 'digits;range[1~100]',
		'examPaperEntity.judgeNumber' : 'digits;range[1~100];examPaperEntity.judgeNumber; judgeNumbervalidator;',
		'examPaperEntity.judgeEachPoint' : 'digits;range[1~100]',
	},
});
