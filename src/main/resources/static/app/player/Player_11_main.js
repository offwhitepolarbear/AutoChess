var all_item_map;
var all_champion_list;
var player;
var player_match_list;
var match_list = new Map();
var rounds;
get_all_item_list();
get_all_round_list();

function get_all_item_list(){
	var url = '/item/rest/itemList/';
	//var data = match_id_list;
	fetch(url, {
	  method: 'POST', // or 'PUT'
	  //body: JSON.stringify(data), // data can be `string` or {object}!
	  headers:{
	    'Content-Type': 'application/json'
	  }
	}).then(res => res.json())
	.then(response => set_all_item(response))
	.catch(error => console.error('Error:', error));
}

function get_all_round_list(){
	//현재 5세트라 5할당
	fetch('/round/rest/rounds/'+'5')
	  .then(function(response) {
		  //console.log(response);
		return response.json();  
	  })
	  .catch(function(myJson){
		  console.log('round_null임');
	  })
	  .then(function(result){
		  rounds = result;
	   })
}

function press_enter_on_user_name(){
	if(event.keyCode==13){
		search_summoner();
	}
}

function search_summoner(){
	//재검색시 기존 목록 지우기
	clear_match_list_html();
	get_player_from_server();
}

function clear_match_list_html(){
	var match_list_html = document.getElementById("match_list");
	match_list_html.innerHTML = '';
}

function get_player_from_server(){
	
	var region = document.getElementById('region').value;
	
	var summoner_name = document.getElementById('summoner_name').value;
	
	// 공백제거 라이엇 정책 공백 없어도 검색되게 되어있음
	summoner_name = summoner_name.replace(/(\s*)/g, "");
	var fetch_url = '/player/rest/'+region+'/'+summoner_name;
	fetch(fetch_url)
	  .then(function(response) {
		return response.json()  
	  })
	  .catch(function(myJson){
		  console.log('player null 리턴 ');
	  })
	  .then(function(result){
		//스크립트 내 변수에 가져온 플레이어 배당 
		player = result;
		//player 정보 html에 적용
		set_player_html();
		//player_match 조회
		get_player_match_from_server();
		
		//가져온 player 정보를 기반으로 가지고 있는 매치 간략정보 가져오기
		get_match_player_from_server();	
	   })
	
}

function set_player_html(){
	console.log(player);
	var player_info = player;
	var img_url = 'http://ddragon.leagueoflegends.com/cdn/11.7.1/img/profileicon/'
			+player_info.profileIconId
			+'.png';
	document.getElementById('player_icon').setAttribute( 'src', img_url );
	document.getElementById('player_info').appendChild(get_player_info_html(player));
}

function get_player_info_html(player){
	/*
	 * 이름 = player.name
	 * 티어 = player.tier
	 * 티어 등급(숫자) = player.rank
	 * 티어 내 점수 = player.leaguePoints
	 * 승수 = player.wins
	 * 패수 = player.loses
	 * 계정레벨 = player.summonerLevel
	 * */
	var player_info = '이름' + player.name
						+'<br>티어'+player.tier + player.rank
						+'<br>점수'+player.leaguePoints
						+'<br>승'+player.wins
	+'<br>패'+player.losses
	+'<br>레벨'+player.summonerLevel;
	
	var player_html = document.createElement("div");
	player_html.setAttribute('class','player_info');
	player_html.setAttribute('value',player_info);
	
	player_html.innerHTML = player_info;
	return player_html;
}
function get_player_info(player){
	var player_tag = document.createElement("div");
	
	var name = document.createElement("div");
	var tier = document.createElement("div");
	var point = document.createElement("div");
	var win = document.createElement("div");
	var lose = document.createElement("div");
	var level = document.createElement("div");
	
	name.innerHTML
	
	
	player_tag.appendChild(name);
	player_tag.appendChild(tier);
	player_tag.appendChild(point);
	player_tag.appendChild(win);
	player_tag.appendChild(lose);
	player_tag.appendChild(level);
	
	return player_tag;
	
	function name_html(name){
		document.createElement("div");
	}
	
	function tier_html(tier, rank){
		document.createElement("div");
	}
	
	function point_html(point){
		document.createElement("div");
	}
	
	function win_html(win){
		document.createElement("div");
	}

	function lose_html(lose){
		document.createElement("div");
	}

	function level_html(level){
		document.createElement("div");
	}
}