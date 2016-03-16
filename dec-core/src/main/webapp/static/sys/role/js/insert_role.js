$('#infoForm').validator({
    rules: {
        //自定义一个规则，用来代替remote（注意：要把$.ajax()返回出来）
        myRemote: function(element){
            return $.ajax({
                url: path + '/sys/role_isRoleExist.action?date=' + new Date().getTime(),
                type: 'post',
                data: element.name +'='+ element.value,
                success: function(d){
                	if(d == '角色名已被占用'){
                		$('#btn').prop("disabled",true);
                	}else{
                		$('#btn').prop("disabled",false);
                	}
                }
            });
        }
    },
    fields: {
        'roleEntity.name': 'required; roleEntity.name; myRemote;',
        'roleEntity.memo' : 'length[0~255, true];filter(\'<>/);'
    },
});