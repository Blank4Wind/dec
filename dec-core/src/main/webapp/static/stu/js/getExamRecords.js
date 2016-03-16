function pageNow(i) {
	$('#dataForm').attr("action",
			path + "/stu/stu_getExamRecords.action?page=" + i);
	$('#dataForm').submit();
}
