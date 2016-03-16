		$('#exampleModal').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget); // Button that triggered the modal
			  var title = button.data('whatever'); // Extract info from data-* attributes
			  var content=button.data('content');
			  var sname =button.data('uname');
			  var rname =button.data('rname');
			  var stime=button.data('stime');
			  var etime=button.data('etime');
			  var modal = $(this);
			  modal.find('.modal-title').text(title);
			  modal.find('.modal-body p').text(content);
			  modal.find('.modal-footer .suname').text(sname);
			  modal.find('.modal-footer .srname').text(rname);
			  modal.find('.modal-footer .stime').text(stime);
			  modal.find('.modal-footer .etime').text(etime);
			});
