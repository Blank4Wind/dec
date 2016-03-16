function pageNow(i){
	$('#dataForm').attr("action",path + "/qes/qesExamPaper!toQesExamPaper.action?page="+i);
	$('#dataForm').submit();
}
function col(i){
	var idval = $('.clazzId').eq(i-1).val();
	var stateval = $('.clazzState').eq(i-1).val();
	$.get(path + "/qes/qesExamPaper_updateState.action?date=" + new Date().getTime(),{
	"id" : idval,
	"state" : stateval
	},function(data){
		if(data==1){
			$('.clazzState').eq(i-1).val("1");
			$('.clazzState').eq(i-1).next().html('禁用');	
			$('.clazzState').eq(i-1).next().removeClass('tate label label-success').addClass('tate label label-danger');
			$('.tagSpan').eq(i-1).next().html('有效');
			$('.tagSpan').eq(i-1).next().removeClass('label label-danger').addClass('label label-success');
		}
		if(data==2){
			$('.clazzState').eq(i-1).val("2");
			$('.clazzState').eq(i-1).next().html('启用');
			$('.clazzState').eq(i-1).next().removeClass('tate label label-danger').addClass('tate label label-success');
			$('.tagSpan').eq(i-1).next().html('无效');
			$('.tagSpan').eq(i-1).next().removeClass('label label-success').addClass('label label-danger');			
		}
	});
}
$('#exampleModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  var recipient = button.data('whatever') // Extract info from data-* attributes
	   var coursename = button.data('coursename')
	   var examtype = button.data('examtype')
	   var totalpoint = button.data('totalpoint')
	   var passpoint = button.data('passpoint')
	   	   
	   var totalminutes = button.data('totalminutes')
	   var singleoptionnumber = button.data('singleoptionnumber')
	   var singleoptioneachpoint = button.data('singleoptioneachpoint')
	   var singleoptiondifficulty = button.data('singleoptiondifficulty')
	   var multioptionnumber = button.data('multioptionnumber')
	   var multioptioneachpoint = button.data('multioptioneachpoint')
	   var multioptiondifficulty = button.data('multioptiondifficulty')
	   var judgenumber = button.data('judgenumber')
	   var judgeeachpoint = button.data('judgeeachpoint')
	   var judgedifficulty = button.data('judgedifficulty')
	   var opreatetime = button.data('opreatetime')
	   var state = button.data('state')
	   var memo = button.data('memo')
	   var username = button.data('username')
	   
	   console.info(opreatetime);
	  console.info(state);
	  console.info(singleoptiondifficulty);
	  console.info(totalminutes);
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  
	  var modal = $(this)
	  modal.find('.modal-title').text("试卷详细信息")
	  modal.find('.modal-body .epename').html(recipient)
	  modal.find('.modal-body .coname').html(coursename)
	  modal.find('.modal-body .totalpoint').html(totalpoint)
	  modal.find('.modal-body .passpoint').html(passpoint)
	  if(examtype==1){
		  modal.find('.modal-body .epeexamtype').html("平时测试") 
	  }else{
		  modal.find('.modal-body .epeexamtype').html("结业测试") 		  
	  }
	  modal.find('.modal-body .totalminutes').html(totalminutes)
	  modal.find('.modal-body .singleoptionnumber').html(singleoptionnumber)
	  modal.find('.modal-body .singleoptioneachpoint').html(singleoptioneachpoint)
	  if(singleoptiondifficulty==1){
		  modal.find('.modal-body .singleoptiondifficulty').html("低")
	  }
	  if(singleoptiondifficulty==2){
		  modal.find('.modal-body .singleoptiondifficulty').html("中")
	  }
	  if(singleoptiondifficulty==3){
		  modal.find('.modal-body .singleoptiondifficulty').html("较高")
	  }
	  if(singleoptiondifficulty==4){
		  modal.find('.modal-body .singleoptiondifficulty').html("高")
	  }
	  modal.find('.modal-body .multioptionnumber').html(multioptionnumber)
	  modal.find('.modal-body .multioptioneachpoint').html(multioptioneachpoint)
	  if(multioptiondifficulty==1){
		  modal.find('.modal-body .multioptiondifficulty').html("低")
	  }
	  if(multioptiondifficulty==2){
		  modal.find('.modal-body .multioptiondifficulty').html("中")
	  }
	  if(multioptiondifficulty==3){
		  modal.find('.modal-body .multioptiondifficulty').html("较高")
	  }
	  if(multioptiondifficulty==4){
		  modal.find('.modal-body .multioptiondifficulty').html("高")
	  }
	  modal.find('.modal-body .judgenumber').html(judgenumber)
	  modal.find('.modal-body .judgeeachpoint').html(judgeeachpoint)
	  if(judgedifficulty==1){
		  modal.find('.modal-body .judgedifficulty').html("低")
	  }
	  if(judgedifficulty==2){
		  modal.find('.modal-body .judgedifficulty').html("中")
	  }
	  if(judgedifficulty==3){
		  modal.find('.modal-body .judgedifficulty').html("较高")
	  }
	  if(judgedifficulty==4){
		  modal.find('.modal-body .judgedifficulty').html("高")
	  }
	  modal.find('.modal-body .opreatetime').html(opreatetime.substring(0,10))
	  if(state==1){
		  modal.find('.modal-body .state').html("有效")
	  }
	  if(state==2){
		  modal.find('.modal-body .state').html("无效")
	  }
	  modal.find('.modal-body .memo').html(memo)
	  modal.find('.modal-body .username').html(username)
	});

function ccoll(i){
	$.ajax({
		type:'post',
		async:true,  
		url:path+'/qes/qesExamPaper_reCreateQesExamPaper.action?examId='+i,
		success:function(data){
			alert('success');
		}
	});
}
