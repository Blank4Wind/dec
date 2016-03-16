$('#select_one').change(function(){
	var vlaues  = $('#select_one option:selected').text();
	if(vlaues != '学员'){
		$('#class_id').hide();
	}else{
		$('#class_id').show();
	}
});

$('#infoForm').validator({
    rules: {
        //自定义一个规则，用来代替remote（注意：要把$.ajax()返回出来）
        myRemote: function(element){
            return $.ajax({
                url: path + '/sys/user_isUserExist.action?date=' + new Date().getTime(),
                type: 'post',
                data: element.name +'='+ element.value,
                success: function(d){
                	if(d == '用户名已被占用'){
                		$('#btn').prop("disabled",true);
                	}else{
                		$('#btn').prop("disabled",false);
                	}
                }
            });
        }
    },
    fields: {
        'user.code': 'required; user.code; myRemote;',
        'user.name': 'required;',
        'user.sex': 'required;',
        'user.phone': 'required;mobile;',
        'user.email': 'required;email;',
        'user.roleName': 'required;',
        'user.memo' : 'length[0~255, true];filter(\'<>/);'
    },
});
