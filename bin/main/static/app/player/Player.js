var all_item_map;
var all_champion_list;
var player;
var player_match_list;
var match_list;
get_all_item_list();

html_test();

function html_test(){
	var refresh_button = document.getElementsByClassName('fa-refresh')[0];
	refresh_button.setAttribute('onclick','javascript:testF()');
	//onclick="javascript:testF()"
	/*
	var region = document.getElementById('region').value;
	var star_html = document.createElement("div");
	star_html.setAttribute("class", 'champion_star')
	*/
}
function testF(){
	console.log('testF');
}

function press_enter_on_user_name(){
	if(event.keyCode==13){
		search_summoner();
	}
}

function search_summoner(){
	//재검색시 기존 목록 지우기
	clear_match_list_html();
	//clear_js_objects()
	get_player_from_server();
}

function clear_match_list_html(){
	var match_list_html = document.getElementById("match_list");
	match_list_html.innerHTML = '';
}

function clear_js_objects(){
	player = null;
	player_match_list = null;
	match_list = null;
}

function get_player_from_server(){
	
	var region = document.getElementById('region').value;
	
	var summoner_name = document.getElementById('summoner_name').value;
	
	// 공백제거 라이엇 정책 공백 없어도 검색되게 되어있음
	summoner_name = summoner_name.replace(/(\s*)/g, "");
	
	fetch('/player/rest/'+region+'/'+summoner_name)
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
				response => set_playe_match_list(response)
		)
		.catch(error => console.error('Error:', error));
}

function set_playe_match_list(server_player_match_list){
	player_match_list = server_player_match_list;
	player_match_list.forEach(function(player_match){
		var match_list_div = document.getElementById("match_list");
		var player_match_div = set_player_match_html(player_match);
		match_list_div.appendChild(player_match_div);
	});
}

function set_player_match_html(player_match){
	var player_match_div = document.createElement("div");
	player_match_div.setAttribute('id', player_match.matchId);
	player_match_div.setAttribute('class', 'player_match');
	return player_match_div;
}

function get_match_button_html(){
	
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

function set_match_player_html(match_player_list){
	
	match_player_list.forEach(function(match_player){
		
		var player_match_tag = document.getElementById(match_player.matchId);
		var match_player_div = document.createElement("div");
		
		match_player.matchPlayerChampionList.forEach(function(match_player_champion){
			var champion_tag = champion_html(match_player_champion, match_player.tftSetNumberDetail);
			match_player_div.appendChild(champion_tag);
		});
		
		match_player.matchPlayerTraitList.forEach(function(match_player_trait){
			
		});
		
		player_match_tag.appendChild(match_player_div);
		
	});
	
}

function champion_html(champion, tft_set_number_detail){
	console.log(champion);
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
	champion_img.setAttribute("class",'champion_cost_'+champion.cost);
	return champion_img;
}

function tft_set_folder_path(tft_set_number){
	console.log(tft_set_number);
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
		console.log('반복문');
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
	var url = '/searchMatch/'+player.region+'/'+match_id;
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