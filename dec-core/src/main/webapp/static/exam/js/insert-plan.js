$('#studentsdd').hide();

$('#r1').click(function(){
	$('#studentsdd').hide();
	$('.stu-box').css("display", "none");
	$('#classdd').show();
})
$('#r2').click(function(){
	$('#classdd').hide();
	$('#studentsdd').show();
	$('.stu-box').css("display", "block");
})

$('#studentsName').click(function() {
	$(".stu-box").fadeIn("slow", function() {
		$('.stu-box').show();
		$('#rowClass').hide();
	});
});

function addStudent(i, k) {
	var userId = $('#studentsId').val();
	var b = new Array();
	var flag = true;
	if (userId != null && userId != "") {
		b = userId.split(',');
		for (var j = 0; j < b.length; j++) {
			if (i == b[j]) {
				flag = false;
			}
		}
	}
	var b = "'" + i + "'";
	if (flag) {
		$('#studentsName').append('<div class="' + i + '" style="float:left;" onclick="deleteStudent(' + b + ')" title="双击取消">' + k + '</div>');
		$('#studentsId').val(userId + i + ',');
	}
}

function deleteStudent(m) {
	var userId = $('#studentsId').val();
	re = new RegExp(m + ",", "g");
	var newstart = userId.replace(re, "");
	$('#studentsId').val(newstart);
	$("#studentsName div").remove("." + m);
}

function showStudent(i) {

	var url = '.icon-block .' + i;
	var after = $(url).parent("li");
	var url2 = '.a' + i;
	$(url2).insertAfter(after);
	if ($(url2).css("display") === "none") {
		$(url).removeClass("groupcloselm_ico");
		$(url).addClass("groupopenlm_ico");
		$(url2).show()
	} else {
		$(url).removeClass("groupopenlm_ico");
		$(url).addClass("groupcloselm_ico");
		$(url2).hide();
	}
}


$('#infoForm').validator({
	fields : {
		'planEntity.examTimeStart' : 'required;',
		'planEntity.examTimeStop' : 'required;',
		'planEntity.examClassroom' : 'required;',
		'planEntity.examPaperId' : 'required;',
		'planEntity.memo' : 'length[0~255, true];filter(\'<>/);',
		'planEntity.state' : 'required',
	},
});

$('#paper').change(function(){
	var startTime = $('#d4311').val();
	var id = $(this).val();
	$.get(path + '/exam/plan_queryEndTime.action?date=' + new Date().getTime(),{
		"planEntity.examTimeStart" : startTime,
		"paperId" : id
	},function(data){
		if(data == ''){
			$('#d4312').val('');
		}else{
			$('#d4312').val(data);
		}
	});
});
