function press_enter_on_match_id(){
	if(event.keyCode==13){
		search_match();
	}

}

function search_match(){
	var region = document.getElementById("region")
	var match_id = document.getElementById("match_id");
	var request_url = "/match/rest/searchMatch/";
	request_url += region.value;
	request_url += "/"
	request_url += match_id.value;
		
	fetch(request_url)
	  .then(function(response) {
	    return response.json();
	  })
	  .then(function(myJson) {
	    console.log(myJson);
	  });
}