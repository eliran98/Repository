<%@page import="com.prectise.ICommons.ICommons"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		String userName = "";
		if(request.getSession() != null){
			userName = (String)request.getSession().getAttribute(ICommons.ATTR_USER_NAME);
		}
		String contextPath = request.getContextPath();
		String loadMsgsUrl = contextPath + "/controller?action=LoadMsgsAction";
		
		String homePageUrl = contextPath + "/jsp/" + ICommons.PAGE_HOME_PAGE;
 	%>
    
    <head>
        <title>Chat</title>
        <link rel="icon" href="/ChatApp/images/chat_icon.jpg">
        <meta charset="UTF-8">
        <!--meta name="viewport" content="width=device-width"-->
        <script type="text/javascript" src="/ChatApp/js/jquery-2.2.3.min.js"></script>
        <link rel="stylesheet" href="/ChatApp/css/chatPage.css"></link>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <style type="text/css">
        	.right-side{
        		float: right !important;
        	}
        	
        </style>
    </head>
    
    <body>
    	<div class="container-chat">
   			<div id="mySidenav" class="sidenav">
			  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			  <a href="#">About</a>
			  <a href="#">Services</a>
			  <a href="#">Clients</a>
			  <a href="#">Contact</a>
			</div>
			
			<!--span onclick="openNav()">open</span-->
			
			<div id="main">    	
	    		<!--div class="titleStyle">Welcome <%=userName%>!</div-->
	    		<div id="main-page-container" class="main-page-container" data-context="<%=contextPath%>">
					<div class="horizontal-tool-bar-container">
						<ul class="horizontal-tool-bar">
						  <li><a class="list-group-item" href="<%=homePageUrl%>"><i class="fa fa-home fa-fw fa-lg" aria-hidden="true"></i>&nbsp; Home</a></li>
						  <li class="right-side"><a class="nav-bar rotate" onclick="onClickNavBar()" href="javascript:void(0)"><i class="fa fa-bars animated-hover" aria-hidden="true"></i></a></li>
						  <li class="right-side"><a href="javascript:void(0)">Welcome <%=userName%>!</a></li>
						</ul>
					</div>
			    </div>
		        <div class="converstion" id="converstion">
		        	<button id="load-btn" class="load-btn">
		        		<div class="wrapper=load-btn-content">
							<div class="yt-spinner-img">Loading...</div>
		        			<div class="yt-spinner-text">Load More</div>
		        			<div class="yt-end-text">End</div>		        		
		        		</div>
		        	</button>
		        	<!--img class="img-style" src="../images/nature_2.jpg"-->
		        	<ul id="converstionList" class="converstion-list">
		        	</ul>
		        </div>
		        <div class="footer">
		        	<div class="footer-wrapper">
		        		<input type="text" id="messageinput" placeholder="what in your mind"/>
		            	<button id="send-btn" type="button" >Send</button>
		        	</div>
		        </div>
		     </div>   
    	</div>
                
        <!--div>
            <button type="button" onclick="openSocket();" >Open</button>
            <button type="button" onclick="closeSocket();" >Close</button>
            <div id="messages"></div>
        </div-->
       
        <!-- Script to utilise the WebSocket -->
        <script type="text/javascript">
        
        	$(document).ready(function(){
        		openSocket();
        		page = 1;
        		
        		$('#send-btn').on('click',function(){
        			send();
        		});
        		
        		$('#load-btn').on('click',function(){
        			$('.yt-spinner-text').hide();
        			$('.yt-spinner-img').show();
        			fetchBlockConverstion(++page,true);
        		});
        		
        		$("#messageinput").keypress(function(e) {
				    if(e.which == 13) {
				        send();
				    }
				});
				
				fetchBlockConverstion(page,false);
        	});
                       
            var webSocket;
            var messages = document.getElementById("messages");
           
           
            function openSocket(){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.",false,false);
                    return;
                }
                
                webSocket = new WebSocket("ws://" + window.location.host + "/ChatApp/echo/" + "<%=userName%>");
                 
                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined){
						return;                    
                    }
                    writeResponse(event.data,false,false);
                };
 
                webSocket.onmessage = function(event){
                    writeResponse(event.data,false,true);
                };
 
                webSocket.onclose = function(event){
                    writeResponse("Connection closed",false,false);
                };
            }
           
            /**
             * Sends the value of the text input to the server
             */
            function send(){
            	if(webSocket.readyState == WebSocket.CLOSED){
            	   return;
            	}
            
                var text = document.getElementById("messageinput").value;
                webSocket.send("<%=userName%>" + ": " + text);
                document.getElementById("messageinput").value='';
            }
           
            function closeSocket(){
                webSocket.close();
            }
 
            function writeResponse(text,isLoadBtn,isFrontInsert){
            	if(isFrontInsert){
            		$("#converstionList").append('<li class="item">' + text + '</li><br>');
            	}else{
					$("#converstionList").prepend('<li class="item">' + text + '</li><br>');            	
            	}
            
                if(!isLoadBtn){
					scrollBottom();                
                }
            }
            
            function scrollBottom(){
            	var converstionDiv = document.getElementById("converstion");
            	converstionDiv.scrollTop = converstionDiv.scrollHeight;
            }
            
            function fetchBlockConverstion(blockNum,isLoadBtn){
            	$.ajax({
		           type: "POST",
		           url: "<%=loadMsgsUrl%>",
		           data: {
		           	  'userName': "<%=userName%>",
		           	  'blockNumber': blockNum	
		           }, 
		           success: function(messages)
		           {
		                if(messages){
		                	for (var i = 0; i < messages.length; i++) {
								writeResponse(messages[i].text,isLoadBtn,false);
							}
		                }
		                if(messages.length == 0){
		                	$('#load-btn').addClass('finish-load');
		                	$('#load-btn').attr("disabled", true);
		                	$('.yt-spinner-img').fadeOut(100,function(){
		                		$('.yt-end-text').show();
		                	});
		                	return;
		                }
		                if(isLoadBtn){
		                	$('.yt-spinner-img').fadeOut(100,function(){
		                		$('.yt-spinner-text').show();
		                	});
		                }
		                
		           },
		           error: function(error){
		        	   console.log(error.responseText);
		           }
		        });
            }
            
            function onClickNavBar(){
            	if(isNavBarOpen()){
            		closeNav();
            	}else{
            		openNav();
            	}
            	
            }
            
            /* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
			function openNav() {
			    document.getElementById("mySidenav").style.width = "250px";
			    document.getElementById("main").style.marginRight = "250px";
			    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
			    $('.nav-bar').addClass("down"); 
			}
			
			/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
			function closeNav() {
			    document.getElementById("mySidenav").style.width = "0";
			    document.getElementById("main").style.marginRight = "0";
			    document.body.style.backgroundColor = "white";
			    $('.nav-bar').removeClass("down");
			}
			
			function isNavBarOpen(){
				return document.getElementById("main").style.marginRight == "250px";
			}
            
        </script>
       
    </body>
</html>