$(function() {
	
	 //显示*到必填元素
	 $("ul li :input.required").each(function(){
		 var $required=$("<strong class='high'> *</strong>");   //创建元素
		 $(this).parent().append($required);     //将它追加到文档中
	 });
	
	// 文本失去焦点后
	$('ul li :input').blur(
			function() {
				var $parent = $(this).parent();
				$parent.find(".formtips").remove();

				// 验证邮件
				if ($(this).is('#email')) {
					if (this.value == ""
							|| (this.value != "" && !/.+@.+\.[a-zA-Z]{2,4}$/
									.test(this.value))) {
						var errorMsg = '请输入正确Email地址.';
						$parent.append('<span class="formtips onError">'
								+ errorMsg + '</span>');

					}
					
				}

				// 验证密码
				if ($(this).is('#password')) {
					if (this.value == "" || this.value.length < 6
							|| this.value.length > 18) {
						var errorMsg = '请输入6-18位密码.';
						$parent.append('<span class="formtips onError">'
								+ errorMsg + '</span>');
					}
					
				}
				// 再次验证密码
				if ($(this).is('#passwordagain')) {
					if (this.value == "" || this.value.length < 6
							|| this.value.length > 18) {
						var errorMsg = '请输入6-18位密码.';
						$parent.append('<span class="formtips onError">'
								+ errorMsg + '</span>');
					} else if (this.value == password.value) {
						var okMsg = '输入正确.';
						
					} else {
						var disagreeMsg = '两次输入密码不一致.';
						$parent.append('<span class="formtips onError">'
								+ disagreeMsg + '</span>');
					}
				}

			}).keyup(function() {
		$(this).triggerHandler("blur");
	}).focus(function() {
		$(this).triggerHandler("blur");
	});//end blur
	
	//提交，最终验证。
	 $('#register').click(function(){
		// console.log('register begin');
			$("ul li :input.required").trigger('blur');
			var numError = $('form .onError').length;
			if(numError){
			//	console.log('error occurs');
				return false;
			} 
			
			 $.ajax({
				 type:"post",
				 url:"local/user/register.do",
				 data:{
					 userName:$("#email").val(),
					 password:$("#password").val(),
					 nickName:$("#passwordagain").val()},
				 success:function(data){ //默认已经转换成了json的对象
					 var msg = data.state;
                   if(msg==0){
                	   console.log('success');
				      window.location="login.html";
					 }else if(msg==1){
						 alert("该邮箱已被注册!");
						 $("ul li :input.required").attr("value","");
					 }else if(msg==-1){
						// console.log("未知错误!");
					 }
				 },
				 error:function(jqXHR, textStatus, errorThrown){
					 console.log(errorThrown);
				 }
			});
			 
	 });
	
	
});	
