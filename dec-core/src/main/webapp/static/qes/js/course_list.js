$(function() {
	$('.state').click(function() {
		var $sub = parseInt($(this).parents("tr").children("td:nth-child(1)").children('input:hidden').val()) - 1;
		var $id = $('.dataTr').children('td:nth-child(7)').children('.qesId').eq($sub).val();
		var $status = $('.dataTr').children('td:nth-child(7)').children('.status').eq($sub).val();
		$.get(path + "/qes/qestions_changeState.action?date=" + new Date().getTime(),{
			"id" : $id,
			"state" : $status
		},function(data) {
			if (data == 1) {
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).html('无效');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).removeClass('label label-success');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).addClass('label label-danger');
				$('.dataTr').children('td:nth-child(7)').children('.status').eq($sub).val("1");
				$('.dataTr').children('td:nth-child(6)').children('span').eq($sub).html('有效');
				$('.dataTr').children('td:nth-child(6)').children('span').eq($sub).removeClass('label label-danger');
				$('.dataTr').children('td:nth-child(6)').children('span').eq($sub).addClass('label label-success');

			}
			if (data == 2) {
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).html('有效');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).removeClass('label label-danger');
				$('.dataTr').children('td:nth-child(7)').children('.state').eq($sub).addClass('label label-success');
				$('.dataTr').children('td:nth-child(7)').children('.status').eq($sub).val("2");
				$('.dataTr').children('td:nth-child(6)').children('span').eq($sub).html('无效');
				$('.dataTr').children('td:nth-child(6)').children('span').eq($sub).removeClass('label label-success');
				$('.dataTr').children('td:nth-child(6)').children('span').eq($sub).addClass('label label-danger');
			}
		});
	});
});