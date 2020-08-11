


	//var btnJson = document.getElementById('export_json');


	
	// Get parameters from the current URL
	var action = window.location.search;
	action = action.substring(8);
	
	
	
	console.log(action);

	switch (action) {
	  case 'expjson':
		  window.open("C:\\Users\\Jimmy\\Desktop\\Nouveau dossier (2)\\group-777918\\maven-quest-ebenus-web\\src\\main\\webapp\\WEB-INF/utilisateurs.json", '_self');
	    break;

	  default:
	    console.log(`Sorry, we are out of ${expr}.`);
	}