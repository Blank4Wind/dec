$(function() {
	$('.state').click(function() {
		var $sub = parseInt($(this).parents("tr").children("td:nth-child(1)").html()) - 1;
		var $id = $('.dataTr').children('td:nth-child(7)').children('.roleId').eq($sub).val();
		var $status = $('.dataTr').children('td:nth-child(7)').children('.status').eq($sub).val();
		$.get(path + "/sys/role_changeState.action?date=" + new Date().getTime(), {
			"id" : $id,
			"status" : $status
		}, function(data) {
			if (data == 1) {
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).html('禁用');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).removeClass('label label-success');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).addClass('label label-danger');
				$('.dataTr').children('td:nth-child(7)').children('.status').eq($sub).val("1");
				$('.dataTr').children('td:nth-child(5)').children('span').eq($sub).html('启用');
				$('.dataTr').children('td:nth-child(5)').children('span').eq($sub).removeClass('label label-danger');
				$('.dataTr').children('td:nth-child(5)').children('span').eq($sub).addClass('label label-success');

			}
			if (data == 2) {
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).html('启用');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).removeClass('label label-danger');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).addClass('label label-success');
				$('.dataTr').children('td:nth-child(7)').children('.status').eq($sub).val("2");
				$('.dataTr').children('td:nth-child(5)').children('span').eq($sub).html('禁用');
				$('.dataTr').children('td:nth-child(5)').children('span').eq($sub).removeClass('label label-success');
				$('.dataTr').children('td:nth-child(5)').children('span').eq($sub).addClass('label label-danger');
			}
		});
	});
});
