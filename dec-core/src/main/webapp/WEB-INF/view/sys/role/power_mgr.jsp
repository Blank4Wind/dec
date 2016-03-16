<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<%@ include file="/WEB-INF/view/common/base.jspf" %>
		<link rel="stylesheet" href="${path}/static/common/plugins/ztree-v3.5/css/zTreeStyle/zTreeStyle.css">
		<script src="${path}/static/common/plugins/ztree-v3.5/js/jquery.ztree.core-3.5.js"></script>
		<script src="${path}/static/common/plugins/ztree-v3.5/js/jquery.ztree.excheck-3.5.js"></script>
	</head>
	<body>
        <section class="content-header">
          <h1>
                角色管理        
            <small>修改权限</small>
          </h1>
        </section>
        <section class="content">
          <div class="box">
			<ul id="treeDemo" class="ztree"></ul>
			<button id="qu" class="btn btn-primary btn-sm">确定</button><s:hidden value="id"></s:hidden>
			<input type="button"  onClick="javascript:history.back(-1);" value="返回" class="btn btn-primary btn-sm">
          </div>
        </section>
	</body>
	<script>
	
		var treeCode = '${treeCode}';
		var setting = {
			data : {
				simpleData : {
					enable : true,
					pIdKey : 'pid'
				}
			},
			check:{
				enable : true,
				nocheckInherit: true
			}
		};
		var zNodes = eval('(' + treeCode + ')');
		$(function() {
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		$('#qu').click(function(){
			
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			var ss="";
			for(var i=0;i<nodes.length;i++){
				console.info(nodes[i].id)
				ss=ss+nodes[i].id+",";
			}
			ss = ss+'${id}';
			location=path+"/sys/role_power.action?ids="+ss;
		})
		
	</script>
</html>