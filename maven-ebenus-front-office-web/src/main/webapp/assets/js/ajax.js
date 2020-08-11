var messageBox = document.getElementById('message');
var submitMessage = document.getElementById('submitMessage');


//function for the button send message
function send(){
	var message = messageBox.value;
	messageBox.value = '';
	var idChannel = $('#which').attr('channel');
	$.post("TchatServlet?action=sendMessage&content=" + message + "&channel=" + idChannel);
	console.log('new message just sent !')
}

//Onclick button lunch this function to set some data on the page, the refresh function needs it.
function requestChannel(id, creator){
	$('#which').attr('channel', id);
	$('#which').attr('creator', creator);
}

//function delete message
function del(idMessage){
	var idChannel = $('#which').attr('channel');
	$.get("TchatServlet?action=delete&idMessage=" + idMessage +"&channel=" + idChannel);
	console.log('deleted!');
}


//refresh the tchat page and gives the data to fill it
function refresh() {
	
	var idChannel = $('#which').attr('channel');
	var creator = $('#which').attr('creator');
	var idUser = document.getElementById('idUser').value;
	$.ajax({
        url: "TchatServlet?action=refresh&channel=" + idChannel,
        dataType: "json",
        success: function(data){
        	var result = "";
        	var description = "";
        	
        	if(creator == idUser){
        		for(var message of data){
                	if(message.user['civilite'] == 'Mrs'){
                		result += "<div class='container message-box-girl'>";
                	}else{
                		result += "<div class='container message-box'>";
                	}
                	result += "<div class='row'>";
                	if(message.user['idUser'] == creator){
                		result += "<p id='' class='author-message creator'>" + message.user['nickname'] + "</p>"
                	}else{
                		result += "<p id='' class='author-message'>" + message.user['nickname'] + "</p>"
                	}
                	result += "<small id='' class='date-message'>" + message.date + "</small>"
                	result += "</div>";
                	result += "<p class='message-conversation'>" + message.content + "</p>"
                	result += "<button onclick='del(" + message.idMessage + ");' class='delete'>X</button>"
                	result += "</div>";
                }
        	}else{
        		for(var message of data){
                	if(message.user['civilite'] == 'Mrs'){
                		result += "<div class='container message-box-girl'>";
                	}else{
                		result += "<div class='container message-box'>";
                	}
                	result += "<div class='row'>";
                	if(message.user['idUser'] == creator){
                		result += "<p id='' class='author-message creator'>" + message.user['nickname'] + "</p>"
                	}else{
                		result += "<p id='' class='author-message'>" + message.user['nickname'] + "</p>"
                	}
                	result += "<small id='' class='date-message'>" + message.date + "</small>"
                	result += "</div>";
                	result += "<p class='message-conversation'>" + message.content + "</p>"
                	if(message.user['idUser'] == idUser){
                		result += "<button onclick='del(" + message.idMessage + ");' class='delete'>X</button>"
                	}
                	result += "</div>";
                }
        	}
            
            $("#ajax").html(result);
        },
        error: function(){
            console.log("Channel non selectionné.");
        }
	});
}

window.setInterval(refresh, 1000);

	
	
