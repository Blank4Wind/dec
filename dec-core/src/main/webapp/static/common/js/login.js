/**
 * 验证码的刷新
 */
$(function() {
	$('#kaptchaImg').click(function() {
		$('#kaptchaImg').attr('src',path + '/Kaptcha.jpg?' + Math.floor(Math.random() * 100));
	});
});

/**
 * 
  var kaptchaImg=document.getElementById('kaptchaImg');
  kaptchaImg.onclick=function(){
	kaptchaImg.src=path+'/kaptcha.jpg?'+Math.floor(math.random()*100);
  }
*
*/