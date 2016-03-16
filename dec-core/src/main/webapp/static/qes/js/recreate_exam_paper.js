function clicks(i){
	alert(i);
     $.ajax({
    	 type:'post',
    	 url:path+'/qes/qesExamPaper!reCreateQesExamPaper.action?id='+i,
    	 success:function(data){
    		 alert(data);
    	 }
     });
}