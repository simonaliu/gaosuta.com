		  $(function(){
				  
				  var name = 'guide';
				  if(readCookie(name)){ //check cookie, if 'guide' exists, hide the line.
					  $(".guide").hide();
					  $("input.cha").hide();
				  }
				  $("input.cha").click(function(){ //hide the line
					  writeCookie(name, "1", "30");
					  $(".guide").hide();
					  $("input.cha").hide();
				  });
		  });
	  
	  $(document).ready(function(){
		 
			  $("#text").focus(function(){
			  $("#text").animate({height:60},"fast");
			  });
		   $("#text").blur(function(){
			   $("#text").animate({height:40},"fast");
			   });
		 
		  });
	  
//template of page
(function(){
	//html template in string
	var record = '<div>hello world {name}</div>';
	//compile template into object source, template source name is 'record'
	var r_c = dust.compile(record, 'record');
	//load the source into the library
	dust.loadSource(r_c);
})();

$(function(){
	//render the template named record
	dust.render('record', {name: 'rightgenius'}, function(err, out){
		console.log(out);
		$("#left_content").append(out);
	});
});