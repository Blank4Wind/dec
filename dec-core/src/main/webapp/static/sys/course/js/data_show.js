function col(i) {
	var idval = $('.courseId').eq(i - 1).val();
	var stateval = $('.status').eq(i - 1).val();
	$.get(path + "/sys/course_changeState.action?date=" + new Date().getTime(), {
		"id" : idval,
		"status" : stateval
	}, function(data) {
		if (data == 1) {
			$('.status').eq(i - 1).val("1");
			$('.status').eq(i - 1).next().html('禁用');
			$('.status').eq(i - 1).next().removeClass('tate label label-success').addClass('tate label label-danger');
			$('.tagSpan').eq(i - 1).next().html('有效');
			$('.tagSpan').eq(i - 1).next().removeClass('label label-danger').addClass('label label-success');
		}
		if (data == 2) {
			$('.status').eq(i - 1).val("2");
			$('.status').eq(i - 1).next().html('启用');
			$('.status').eq(i - 1).next().removeClass('tate label label-danger').addClass('tate label label-success');
			$('.tagSpan').eq(i - 1).next().html('无效');
			$('.tagSpan').eq(i - 1).next().removeClass('label label-success').addClass('label label-danger');
		}
	});
}