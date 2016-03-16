$(function() {
	$('#parentName').click(function() {
		var setting = {
			data : {
				simpleData : {
					enable : true,
					pIdKey : 'pid'
				}
			},
			callback : {
				onClick : zTreeOnClick
			}
		};
		var zNodes = eval('(' + courseJson + ')');
		$(function() {
			$.fn.zTree.init($("#treeDemo"),setting,zNodes);
		});

	});
	// 点击某个节点 然后将该节点的名称赋值值文本框
	function zTreeOnClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		// 获得选中的节点
		var nodes = zTree.getSelectedNodes(), v = "";
		// 根据id排序
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
		}
		// 将选中节点的名称显示在文本框内
		if (v.length > 0)
			v = v.substring(0,v.length - 1);
		var cityObj = $("#parentName");
		cityObj.attr("value",v);
		return false;
	}

});
$('#infoForm').validator({
    rules: {
        //自定义一个规则，用来代替remote（注意：要把$.ajax()返回出来）
        myRemote: function(element){
            return $.ajax({
                url: path + '/sys/course_isCourseExist.action?date=' + new Date().getTime(),
                type: 'post',
                data: element.name +'='+ element.value,
                success: function(d){
                	if(d == '课程名已被占用'){
                		$('#btn').prop("disabled",true);
                	}else{
                		$('#btn').prop("disabled",false);
                	}
                }
            });
        }
    },
    fields: {
        'courseEntity.name': 'required; courseEntity.name; myRemote;',
        'courseEntity.memo' : 'length[0~255, true];filter(\'<>/);'
    },
});