function col(i){
	var idval = $('.userId').eq(i-1).val();
	var stateval = $('.clazzState').eq(i-1).val();
	$.get(path + "/sys/user_changeState.action?date=" + new Date().getTime(),{
	"id" : idval,
	"state" : stateval
	},function(data){
		if(data==1){
			$('.clazzState').eq(i-1).val("1");
			$('.clazzState').eq(i-1).next().html('禁用');	
			$('.clazzState').eq(i-1).next().removeClass('tate label label-success').addClass('tate label label-danger');
			$('.tagSpan').eq(i-1).next().html('有效');
			$('.tagSpan').eq(i-1).next().removeClass('label label-danger').addClass('label label-success');
		}
		if(data==2){
			$('.clazzState').eq(i-1).val("2");
			$('.clazzState').eq(i-1).next().html('启用');
			$('.clazzState').eq(i-1).next().removeClass('tate label label-danger').addClass('tate label label-success');
			$('.tagSpan').eq(i-1).next().html('无效');
			$('.tagSpan').eq(i-1).next().removeClass('label label-success').addClass('label label-danger');			
		}
	});
}

function cols(i){
	var ids = $('.userIds').eq(i-1).val();
	var password1 = $('.userpw').eq(i-1).val();
		$.get(path + "/sys/user_toUserPassword.action?date=" + new Date().getTime(),{
		"id" :ids ,
		"password" :password1
	},function(data) {
		
		if(data == 1){
			$(":button").html('初始密码');
		}
	});
}