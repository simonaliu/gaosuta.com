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
	//var record = '<div>hello world {name}</div>';
	//compile template into object source, template source name is 'record'
	//var r_c = dust.compile(record, 'record');
	//load the source into the library
	//dust.loadSource(r_c);
	
	var contentrecord = '{#records}<div class="content"><img src="images/photo.PNG" /><div class="name"><span class="name">id:{id}</span><span class="state">:text:{text}</span></div><div class="picture"><img src={imageUrl}/></div><div class="time"><span class="date">time:{generateTime}</span><span class="resource">来自</span><span class="date"><a href="#">{resource}</a></span></div> </div>{~n}{/records} ';
	
	var c_r = dust.compile(contentrecord,'contentrecord');
	dust.loadSource(c_r);
})();

$(function(){
	//render the template named record
//	dust.render('record', {name: 'rightgenius'}, function(err, out){
//		console.log(out);
//		$("#left_content").append(out);
	console.log(TELL.user.id);
	$.getJSON('local/record/findListByUserId.do',{
		userId: TELL.user.id,
		pageNum: 1,
		pageCapacity: 10
		
	}, function(data){
		console.log(data);
		if(typeof data === 'array') {
			
			return;
		}
		dust.render('contentrecord',data,function(err,out){
			if(err){
				console.log(err);
				return;
			}
		console.log(out);
		$("#left_content").append(out);
		
	});
	});	
	
	});
	
