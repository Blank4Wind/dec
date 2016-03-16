var setting = {
	data : {
		simpleData : {
			enable : true,
			pIdKey : 'pid'
		}
	}
};
var zNodes = eval('(' + courseJson + ')');
$(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.expandAll(true);
});
