
//On change, build the form of the selected channel
$("#selectChannel").change(function(){
  var selected = $( "#selectChannel" ).val();
  
  $.ajax({
      url: "TchatServlet?action=updateviewform&channel=" + selected,
      dataType: "json",
      success: function(data){
      	var result = "";
      	result += "<form action='TchatServlet?action=updateChannel&channel=" + data.idChannel + "' " + "method='Post' id='customer-info-form'>";
      	result += "<div class='row'>";
      	result += "<div class='col-md-5 registered-users'>";
      	result += "<div>";
      	result += "<ul class='form-list'>";
      	result += "<li>";
      	result += "<label for='pass' class='required'>Name</label>";
      	result += "<div class='input-box'>";
      	result += "<input type='text' name='name' class='input-text' title='name' value='" + data.name + "'>"
      	result += "</div>";
      	result += "</li>";
      	result += "<li>";
      	result += "<label for='pass' class='required'>Description</label>";
      	result += "<div class='input-box'>";
      	result += "<textarea name='description' class='input-text' title='description'>" + data.description + "</textarea>"
      	result += "</div>";
      	result += "</li>";
      	result += "</ul>";
      	result += "</div>";
      	result += "<br>";
      	result += "<button class='btn btn-default' type='submit'>Update !</button>";
      	result += "</div>";
      	result += "</div>";
      	result += "</form>";

          $("#update").html(result);
      },
      error: function(){
          console.log("erreur.");
      }
	});
});
