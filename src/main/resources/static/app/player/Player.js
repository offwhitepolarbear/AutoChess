var player = new Object();

function press_enter_on_user_name(){
	if(event.keyCode==13){
		search_summoner();
	}
}

function search_summoner(){
	var summoner_name = document.getElementById('summoner_name').value;
	// 공백제거 라이엇 정책상 공백 없어도 검색되게 되어있음
	summoner_name = summoner_name.replace(/(\s*)/g, "");
	
	var region = document.getElementById('region').value; 
	fetch('/player/rest/'+region+'/'+summoner_name)
	  .then(function(response) {
		  // console.log(response);
		 // console.log(response.json());
	    return response.json();
	  })
	  .catch(function(myJson){
		  console.log('널와서 캐치');
	  })
	  .then(function(myJson) {
		  console.log(myJson);
		  player = myJson;
		  fill_player_info(myJson);
		  var img_url = 'http://ddragon.leagueoflegends.com/cdn/11.7.1/img/profileicon/'
			  				+myJson.profileIconId
			  				+'.png';
		  document.getElementById('player_icon').setAttribute( 'src', img_url );
	  })
	  ;
}

function fill_player_info(player_info){
	fill_match_list(player_info.playerMatchList);
	getMatchList(player_info.playerMatchList);
}

function fill_match_list(match_info){
	var match_list_div = document.getElementById("match_list");
	
	match_info.forEach(function(each_match_info){
		var newDiv = document.createElement("div");
		newDiv.innerHTML = each_match_info.matchId;
		newDiv.setAttribute("id",each_match_info.matchId);
		match_list_div.appendChild(newDiv);
	})
	
}

function getMatchList(match_id_list){
	
	var url = '/match/rest/matchList/matchIds';
	var data = match_id_list;
	console.log(data);
	fetch(url, {
	  method: 'POST', // or 'PUT'
	  body: JSON.stringify(data), // data can be `string` or {object}!
	  headers:{
	    'Content-Type': 'application/json'
	  }
	}).then(res => res.json())
	.then(response => console.log('Success:', JSON.stringify(response)))
	.catch(error => console.error('Error:', error));
}
