$('#infoForm').validator({
	rules : {
		//自定义一个规则，用来代替remote（注意：要把$.ajax()返回出来）
		myRemote : function(element) {
			return $.ajax({
				url : path + '/sys/clazz_checkAccount.action?date=' + new Date().getTime(),
				type : 'post',
				data : element.name + '=' + element.value,
				success : function(d) {
					if (d == '班级名已被占用') {
						$('#btn').prop("disabled", true);
					} else {
						$('#btn').prop("disabled", false);
					}
				}
			});
		}
	},
	fields : {
		'classEntity.name' : 'required; classEntity.name; myRemote;',
		'courseEntity.name' : 'required',
		'classEntity.memo' : 'length[0~255, true];filter(\'<>/);'
	},
});
