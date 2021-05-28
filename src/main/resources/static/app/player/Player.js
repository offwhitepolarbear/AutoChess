var all_item_map;
var all_champion_list;
var player;
var player_match_list;
var match_list;
var rounds;
get_all_item_list();
get_all_round_list();

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
	
	console.log(region+'지역코드');
	
	// 공백제거 라이엇 정책 공백 없어도 검색되게 되어있음
	summoner_name = summoner_name.replace(/(\s*)/g, "");
	var fetch_url = '/player/rest/'+region+'/'+summoner_name;
	console.log(fetch_url+'지역코드');
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
	var player_info = player;
	var img_url = 'http://ddragon.leagueoflegends.com/cdn/11.7.1/img/profileicon/'
			+player_info.profileIconId
			+'.png';
	document.getElementById('player_icon').setAttribute( 'src', img_url );
}

function get_player_match_from_server(){
	var url = '/player/rest/playerMatch/'+player.region+'/'+player.puuid;
	fetch(url, {
		  method: 'POST',
		  headers:{
		    'Content-Type': 'application/json'
		  }
		}).then(res => res.json())
		.then(
				response => set_player_match_list(response)
		)
		.catch(error => console.error('Error:', error));
}

function set_player_match_list(server_player_match_list){
	player_match_list = server_player_match_list;
	var match_list_div = document.getElementById("match_list");
	player_match_list.forEach(function(player_match){
		var player_match_div = set_player_match_html(player_match);
		match_list_div.appendChild(player_match_div);
	});
}




//검색된 유저의 player_match 목록에 대한 내역 html 구성
//일단 초기화 해서 get_match 가져올 수 있는 사앹로 만들고
//리스트 작성 이후 match_player 가져와서 있는 내역은 다시 할당해야됨
/*
function set_player_match_html(player_match_list){
	console.log(player_match_list+'anjse[');
	var player_match_tag = document.getElementById(match_list);
	player_match_list.forEach(function(player_match){
		//var player_match_tag = document.getElementById(match_player.matchId);
		var player_match_div = document.createElement("div");
		player_match_div.setAttribute('id',player_match.matchId)
		player_match_div.setAttribute('class','player_match')
		match_player.matchPlayerChampionList.forEach(function(match_player_champion){
			var champion_tag = champion_html(match_player_champion, match_player.tftSetNumberDetail);
			player_match_div.appendChild(champion_tag);
		});
		
		
		player_match_tag.appendChild(player_match_div);
		
		
	});
}
*/

function set_player_match_html(player_match){
	var player_match_div = document.createElement("div");
	player_match_div.setAttribute('id', player_match.matchId);
	player_match_div.setAttribute('class', 'player_match');
	player_match_div.appendChild(get_match_button_html(player_match.matchId));
	return player_match_div;
}

function get_match_button_html(match_id){
	var match_button_html = document.createElement("div");
	match_button_html.setAttribute('class','get_match_button');
	match_button_html.setAttribute("onclick", "javascript:get_match_from_server('"+match_id+"')");
	
	var refresh_button = document.createElement("i");
	refresh_button.setAttribute('class','fa fa-refresh fa-2x');
	refresh_button.setAttribute('aria-hidden','true');
	match_button_html.appendChild(refresh_button);
	return match_button_html;
}


function get_match_player_from_server(){
	var url = "/match/rest/matchPlayerList/"+player.puuid;
	fetch(url, {
		  method: 'POST',
		  headers:{
		    'Content-Type': 'application/json'
		  }
		}).then(res => res.json())
		.then(
				response => set_match_player_html(response)
		)
		.catch(error => console.error('Error:', error));
}

//match 검색했을때 가져온 match_player별 내역 html 
function set_match_player_html(match_player_list){
	
	match_player_list.forEach(function(match_player){
		
		var player_match_tag = document.getElementById(match_player.matchId);
		
		//매치상세 추가
		player_match_tag.appendChild(get_match_summary_html(match_player));
		
		var match_player_div = document.createElement("div");
		match_player_div.setAttribute('class','match_player_summary');
		match_player.matchPlayerChampionList.forEach(function(match_player_champion){
			var champion_tag = champion_html(match_player_champion, match_player.tftSetNumberDetail);
			match_player_div.appendChild(champion_tag);
		});
		
		match_player.matchPlayerTraitList.forEach(function(match_player_trait){
			
		});
		
		player_match_tag.appendChild(match_player_div);
		//player_match_tag.insertBefore(get_match_summary_html(match_player), player_match_tag.firstChild);
		
	});
	
}






function champion_html(champion, tft_set_number_detail){
	var champion_html = document.createElement("div");
	champion_html.setAttribute('class', 'champion');
	champion_html.appendChild(champion_star_html(champion.star));
	champion_html.appendChild(champion_img_html(champion, tft_set_number_detail));
	champion_html.appendChild(items_img_html(champion, tft_set_number_detail));
	return champion_html;
}

function champion_star_html(champion_star){
	var star_html = document.createElement("div");
	star_html.setAttribute("class", 'champion_star')
	for(var i=0;i<champion_star;i++){
		var star_img = document.createElement("i");
		star_img.setAttribute('class','fa fa-star');
		star_img.setAttribute('aria-hidden','true');
		star_html.appendChild(star_img);
	}
	return star_html;
}

function champion_img_html(champion, tft_set_number_detail){
	var champion_img = document.createElement("img");
	champion_img.setAttribute("src", "/file/sets/"+tft_set_folder_path(tft_set_number_detail)+"/champions/"+champion.championId+".png");
	champion_img.setAttribute("class",'champion_cost_'+champion.cost+' champion_img');
	return champion_img;
}

function tft_set_folder_path(tft_set_number){
	var tft_set_main = tft_set_number;
	var tft_set_main_legnth;
	var half_season_check = '.';
	var isHalfSeason = String(tft_set_number).includes(half_season_check);
	
	if(isHalfSeason){
		tft_set_main = String(tft_set_number).split('.');
		tft_set_main = tft_set_main[0];
	}

	tft_set_main_legnth = String(tft_set_main).length;
	for(var i=0; i<3-tft_set_main_legnth ;i++){
		tft_set_number = '0'+tft_set_number;
	}
	return tft_set_number;
}

function items_img_html(champion, tft_set_number_detail){
	var items_html = document.createElement("div");
	items_html.setAttribute("class",'champion_items');
	items_html.appendChild(item_img_html(champion.itemOne, tft_set_number_detail));
	items_html.appendChild(item_img_html(champion.itemTwo, tft_set_number_detail));
	items_html.appendChild(item_img_html(champion.itemThree, tft_set_number_detail));
	return items_html;
	
	//여기서만 사용할 거 같은 펑션은 이너로 생성
	function item_img_html(item_id, tft_set_number_detail){
		if(item_id>0){
			if(item_id<10){
				//(아이템 파일경로명 참조하게 변경
				item_id = '0'+item_id;
			}
			var item_img = document.createElement("img")
			item_img.setAttribute('class','item_img');
			item_img.setAttribute("src", "/file/sets/"+tft_set_folder_path(tft_set_number_detail)+"/items/"+item_id+".png");
			return item_img;
		}
		else{
			var empty_itme_img = document.createElement("i")
			empty_itme_img.setAttribute('class','empty_item_img');
			return empty_itme_img;
		}
	}

}

function get_match_from_server(match_id_list){
	var url = '/match/rest/matchList/matchIds';
	var data = match_id_list;
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
function set_match_html(){
	
}

function fill_match_list(match_info){
	
	var match_list_div = document.getElementById("match_list");
	
	match_info.forEach(function(each_match_info){
		var newDiv = document.createElement("div");
		newDiv.innerHTML = each_match_info.matchId;
		newDiv.setAttribute("id", each_match_info.matchId);
		match_list_div.appendChild(newDiv);
	})
	
}

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

function set_all_item(item_info){
	//item_info = item_info.entries();
	var item_map = new Map();
	
	item_info.forEach(function(item){
		item_map[item.itemId] = item;
	})
	all_item_map = item_map;
	
}
function get_all_champion_list(){
	
}

function get_match_from_server(match_id){
	var url = '/match/rest/searchMatch/'+player.region+'/'+match_id;
	fetch(url, {
		  headers:{
		    'Content-Type': 'application/json'
		  }
		}).then(res => res.json())
		.then(response => set_match_html(response))
		.catch(error => console.error('Error:', error));
}

function set_match_html(match){
	
}

function get_match_summary_html(match_player){
	var match_summary_html = document.createElement("div");
	match_summary_html.setAttribute("class", 'match_summary');
	
	var match_summary_placement_html = get_match_summary_placement_html(match_player.placement);
	var match_summary_last_round_html = get_match_summary_last_round_html(match_player.lastRound);
	var match_summary_time_eliminated_html =  get_match_summary_time_eliminated_html(match_player.timeEliminated);
	var match_summary_damage_to_players_html = get_match_summary_damage_to_players_html(match_player.damageToPlayers);
	var match_summary_players_eliminated_html = get_match_summary_players_eliminated_html(match_player.playersEliminated);
	var match_summary_gold_left_html = get_match_summary_gold_left_html(match_player.goldLeft);
	
	
	match_summary_html.appendChild(match_summary_placement_html);
	match_summary_html.appendChild(match_summary_last_round_html);
	match_summary_html.appendChild(match_summary_time_eliminated_html);
	match_summary_html.appendChild(match_summary_damage_to_players_html);
	match_summary_html.appendChild(match_summary_players_eliminated_html);
	match_summary_html.appendChild(match_summary_gold_left_html);
	
	//match_player.placement
	//match_player.lastRound
	//match_player.timeEliminated
	//match_player.damageToPlayers
	//match_player.playersEliminated
	//match_player.goldLeft;

	return match_summary_html;
}

function get_match_summary_placement_html(placement){
	var match_summary_placement_html = document.createElement("div");
	match_summary_placement_html.setAttribute("class", 'match_summary_placement_'+placement);
	match_summary_placement_html.innerHTML = '등수 '+placement;
	return match_summary_placement_html;
}

function get_match_summary_last_round_html(lastRound){
	var match_summary_lastRound_html = document.createElement("div");
	match_summary_lastRound_html.setAttribute("class", 'match_summary_last_round');
	match_summary_lastRound_html.innerHTML = '최종라운드'+round_count(lastRound);
	return match_summary_lastRound_html;
}

function round_count(lastRound){
	var count = 0;
	var roundIndex = 0;
	while (count <= lastRound){
		roundIndex += 1;
		count += rounds[roundIndex];
	}
	//
	var main_round = roundIndex;
	var sub_round = lastRound - (count-rounds[roundIndex]);
	
	return main_round+'-'+sub_round;
	
}

function get_match_summary_time_eliminated_html(timeEliminated){
	var match_summary_timeEliminated_html = document.createElement("div");
	match_summary_timeEliminated_html.setAttribute("class", 'match_summary_time_eliminated');
	match_summary_timeEliminated_html.innerHTML = '종료시간 '+timestamp_to_js_time(timeEliminated);
	return match_summary_timeEliminated_html;
	
	function timestamp_to_js_time(timeEliminated){
		var minutes = Math.floor(timeEliminated / 60);
		var seconds = Math.floor(timeEliminated % 60);
		return minutes +":"+seconds;
	}
	
}

function get_match_summary_damage_to_players_html(damageToPlayers){
	var match_summary_damageToPlayers_html = document.createElement("div");
	match_summary_damageToPlayers_html.setAttribute("class", 'match_summary_damage_to_players');
	match_summary_damageToPlayers_html.innerHTML = '가한 피해량'+damageToPlayers;
	return match_summary_damageToPlayers_html;
}

function get_match_summary_players_eliminated_html(playersEliminated){
	var match_summary_playersEliminated_html = document.createElement("div");
	match_summary_playersEliminated_html.setAttribute("class", 'match_summary_players_eliminated');
	match_summary_playersEliminated_html.innerHTML = '탈락시킨 플레이어 수'+playersEliminated;
	return match_summary_playersEliminated_html;
}

function get_match_summary_gold_left_html(goldLeft){
	var match_summary_goldLeft_html = document.createElement("div");
	match_summary_goldLeft_html.setAttribute("class", 'match_summary_gold_left');
	match_summary_goldLeft_html.innerHTML = '잔여골드'+goldLeft;
	return match_summary_goldLeft_html;
}

/*
var eElement; // some E DOM instance
var newFirstElement; //element which should be first in E

eElement.insertBefore(newFirstElement, eElement.firstChild);

*/