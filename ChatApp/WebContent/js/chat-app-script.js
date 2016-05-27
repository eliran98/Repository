


$("#form-registration").submit(function(e){
	
	if(!validateFields()){
		showErrorMsg("error in fields");
		e.preventDefault();
		return;
	}
	
	 $("#errorMsg").hide();
	
	 var url = $("#form-registration").attr('action'); // the script where you handle the form input.
	 var urlChatPage =  $("#form-registration").attr('data-context') + "/jsp/chat.jsp";
	 
	    $.ajax({
	           type: "POST",
	           url: url,
	           data: $("#form-registration").serialize(), // serializes the form's elements.
	           success: function(data)
	           {
	               window.location = urlChatPage; 
	           },
	           error: function(error){
	        	   console.log(error.responseText);
	        	   showErrorMsg(error.responseText);
	           }
	         });
	    
	    //avoid to execute the actual submit of the form.
	    e.preventDefault(); 
});

$("#form-signIn").submit(function(e){
	
	$("#errorMsgSignIn").hide();
	
	var url = $("#form-signIn").attr('action'); // the script where you handle the form input.
	var urlChatPage =  $("#form-signIn").attr('data-context') + "/jsp/chat.jsp";
	
	$.ajax({
		type: "POST",
		url: url,
		data: $("#form-signIn").serialize(), // serializes the form's elements.
		success: function(data)
		{
			window.location = urlChatPage; 
		},
		error: function(error){
			console.log(error.responseText);
			showSignInErrorMsg(error.responseText);
		}
	});
	
	//avoid to execute the actual submit of the form.
	e.preventDefault(); 
});

function validateFields(){
	var newPasswordValue = $("#newPassword").val();
	var confirPasswordValue = $("#confirmPassword").val();
	if(newPasswordValue.length<7 || newPasswordValue !== confirPasswordValue){
		return false;
	}
	return true;
}

$("#errorMsgCancel").on('click',function(e){
	e.preventDefault();
	$("#errorMsg").hide();
});

$("#errorMsgSignInCancel").on('click',function(e){
	e.preventDefault();
	$("#errorMsgSignIn").hide();
});

function showErrorMsg(errorMsg){
	$("#errorMsg span").text(errorMsg);
	$("#errorMsg").show();
}

function showSignInErrorMsg(errorMsg){
	$("#errorMsgSignIn span").text(errorMsg);
	$("#errorMsgSignIn").show();
}
