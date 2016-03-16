$('#infoForm').validator({
	fields : {
		'classEntity.name' : 'required;',
		'courseEntity.name' : 'required',
		'classEntity.memo' : 'length[0~255, true];filter(\'<>/);'
	},
});
