var all_item_map;
var all_champion_list;
var player;
var player_match_list;
var match_list;
get_all_item_list();

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
		  method: 'POST', // or 'PUT'
		 // body: JSON.stringify(data), // data can be `string` or {object}!
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
	console.log(player_match_list);
}

function get_match_player_from_server(){
	var url = "/match/rest/matchPlayerList/"+player.puuid;
	fetch(url, {
		  method: 'POST', // or 'PUT'
		 // body: JSON.stringify(data), // data can be `string` or {object}!
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
	var match_list_div = document.getElementById("match_list");
	
	match_player_list.forEach(function(match_player){
		var match_player_div = document.createElement("div");
		
		match_player.matchPlayerChampionList.forEach(function(match_player_champion){
			
			var champion_tag = champion_html(match_player_champion);
			match_player_div.appendChild(champion_tag);
		});
		
		match_list_div.appendChild(match_player_div);
	});
	
}

function champion_html(champion){
	var champion_html = document.createElement("div");
	champion_html.setAttribute('class', 'champion');
	/*
	champion.championId
	champion.cost
	champion.star
	champion.chosen
	champion.itemOne
	champion.itemTwo
	champion.itemThree
	*/
	champion_html.appendChild(champion_star_html(champion.star));
	/*
	for(var i=0;i<champion.star;i++){
		//<i class="fa fa-star" aria-hidden="true"></i>
		var star_img = document.createElement("i");
		star_img.setAttribute('class','fa fa-star');
		star_img.setAttribute('aria-hidden','true');
		champion_html.appendChild(star_img);
	}
	*/
	/*
	var champion_img = document.createElement("img");
	champion_img.setAttribute("src", "/file/current_set/champions/"+champion.championId+".png");
	champion_img.setAttribute("class",'champion_cost_'+champion.cost);
	*/
	champion_html.appendChild(champion_img_html(champion));
	/*
	champion_html.appendChild(item_img_html(champion.itemOne));
	champion_html.appendChild(item_img_html(champion.itemTwo));
	champion_html.appendChild(item_img_html(champion.itemThree));
	*/
	champion_html.appendChild(items_img_html(champion));
	return champion_html;
}
function champion_star_html(champion_star){
	var star_html = document.createElement("div");
	star_html.setAttribute("class", 'champion_star')
	for(var i=0;i<champion_star;i++){
		/*<i class="fa fa-star" aria-hidden="true"></i>*/
		var star_img = document.createElement("i");
		star_img.setAttribute('class','fa fa-star');
		star_img.setAttribute('aria-hidden','true');
		star_html.appendChild(star_img);
	}
	return star_html;
}

function champion_img_html(champion){
	var champion_img = document.createElement("img");
	champion_img.setAttribute("src", "/file/current_set/champions/"+champion.championId+".png");
	champion_img.setAttribute("class",'champion_cost_'+champion.cost);
	return champion_img;
}
function items_img_html(champion){
	var items_html = document.createElement("div");
	items_html.setAttribute("class",'champion_items');
	items_html.appendChild(item_img_html(champion.itemOne));
	items_html.appendChild(item_img_html(champion.itemTwo));
	items_html.appendChild(item_img_html(champion.itemThree));
	return items_html;
	
	//여기서만 사용할 거 같은 펑션은 이너로 생성
	function item_img_html(item_id){
		if(item_id>0){
			var item_img = document.createElement("img")
			item_img.setAttribute('class','item_img');
			item_img.setAttribute("src", "/file/current_set/items/"+item_id+".png");
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