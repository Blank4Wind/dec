$('#select_one').change(function(){
	var vlaues  = $('#select_one option:selected').text();
	if(vlaues != '学员'){
		$('#class_id').hide();
	}else{
		$('#class_id').show();
	}
});


$('#infoForm').validator({
	fields : {
		'user.name' : 'required;',
		'user.sex' : 'required;',
		'user.phone' : 'required;mobile;',
		'user.email' : 'required;email;',
		'user.roleName' : 'required;',
		'user.memo' : 'length[0~255, true];filter(\'<>/);'
	},
});
