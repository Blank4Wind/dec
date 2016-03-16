$('#infoForm').validator({
    rules: {
        //自定义一个规则，用来代替remote（注意：要把$.ajax()返回出来）
        myRemote: function(element){
            return $.ajax({
                url: path + '/common/common_isPasswdTrue.action?date=' + new Date().getTime(),
                type: 'post',
                data: element.name +'='+ element.value,
                success: function(d){
                	if(d == '原密码错误'){
                		$('#btn').prop("disabled",true);
                	}else{
                		$('#btn').prop("disabled",false);
                	}
                }
            });
        }
    },
    fields: {
        'oldpwd': 'required; oldpwd; myRemote;',
        'newpwd':'required;length[6~14];',
        'newpwd2':'required;match[newpwd]'
    },
});
