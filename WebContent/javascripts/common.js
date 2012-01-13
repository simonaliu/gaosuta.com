var TELL = {
	prefix: "local/user/",
	user:{
		id: readCookie('id'),
		username: readCookie('username'),
		nickname: readCookie('nickname'),
		avatar: readCookie('avatar')
	}
};
(function(){
	if(TELL.user.username) {
		console.log(TELL.user.username);
		return;//if cookie is still useful
	}
	$.getJSON(TELL.prefix + "findLogin.do", function(data){
		function updateCache(name, value){
			console.log('update '+name+' : '+value);
			TELL.user[name] = value;
			writeCookie(name, value, 999);
		}
		console.log(data.state);
		if(data.state == 0){
			updateCache('uid', data.id);
			$.getJSON(TELL.prefix + "findById.do", {id: TELL.user.id}, function(data){
				if(data.state == 0){
					updateCache('username', data.user.login.userName);
					updateCache('nickname', data.user.nickname);
					updateCache('avatar', data.user.photoUrl);
				}
			});
		}else{
			alert('服务器故障！');
		}
	});
})();