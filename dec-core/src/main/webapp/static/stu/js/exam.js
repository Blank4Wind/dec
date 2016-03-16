$(function() {
	$('input:radio').click(function() {
		var name = $(this).attr("name");
		$('.' + name).css('background', '#4FD0E5');
	});
	$('input:checkbox').click(function() {
		var name = $(this).attr("name");
		$('.' + name).css('background', '#4FD0E5');
		var ck = $('input:checkbox[name="' + name + '"]');
		var c = true;
		var answer = "";
		var qId = $(this).attr("name");

		for (var i = 0; i < ck.length; i++) {
			if (ck.eq(i).prop("checked")) {
				c = false;
				var b = ck.eq(i).val();
				answer = answer + b;
			}
		}
		if (c) {

			$('.' + name).css('background', '#FFFFFF');
		} else {
			$('.' + name).css('background', '#4FD0E5');
		}

		$.get(path + "/stu/stu_params.action?date=" + new Date().getTime(), {
			"examAnswer.id" : qId,
			"examAnswer.answer" : answer,
			"id" : pId
		}, function(data) {

		});
	});

	$('.answer').click(function() {
		var qId = $(this).attr("name");
		var answer = $(this).val();
		$.get(path + "/stu/stu_params.action?date=" + new Date().getTime(), {
			"examAnswer.id" : qId,
			"examAnswer.answer" : answer,
			"id" : pId
		}, function(data) {
		});
	});

	$(document).ready(function() {
		setInterval(getnums, 1000 * 60);
	});

	var vab = $('.stuAsser');
	for (var i = 0; i < vab.length; i++) {
		var vam = vab.eq(i).attr("name");
		var vaj = vab.eq(i).html();

		$('input:radio[name="' + vam + '"][value="' + vaj + '"]').attr(
				"checked", "checked");

		if (vaj) {
			$('.' + vam).css('background', '#4FD0E5')
		}
		var van = $('input:checkbox[name="' + vam + '"]');
		if (van.length > 0) {
			for (var j = 0; j < van.length; j++) {
				var c = van.eq(j).val();
				if (vaj.indexOf(c) >= 0) {
					van.eq(j).attr("checked", "checked");
				}
			}

		}
	}
});
