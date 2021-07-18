function set_html_display_show(match_html){
	var temp_class = match_html.className;
	temp_class.replace('display_hide','display_show');
	match_html.className = temp_class;
}

function set_html_display_hide(match_html){
	var temp_class = match_html.className;
	temp_class.replace('display_show','display_hide');
	match_html.className = temp_class;
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
		set_match_player_each_html(match_player);
		//player_match_tag = document.getElementById(match_player.matchId);
		
	});
	
}

function set_match_player_each_html(match_player){
	var player_match_tag = document.getElementById(match_player.matchId);
	
	//기존검색버튼(새로고침) 삭제
	remove_button(match_player.matchId);

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
	
	//새 매치검색 버튼 추가
	var match_button = get_match_from_db_button_html(match_player.matchId);
	player_match_tag.appendChild(match_button);
	
	
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

function fill_match_list(match_info){
	
	var match_list_div = document.getElementById("match_list");
	
	match_info.forEach(function(each_match_info){
		var newDiv = document.createElement("div");
		newDiv.innerHTML = each_match_info.matchId;
		newDiv.setAttribute("id", each_match_info.matchId);
		match_list_div.appendChild(newDiv);
	})
	
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

function get_match(match_id){
	
	var match = match_list[match_id];
	
	//매치가 없는경우 서버에서 가져오는 로직
	if(match==undefined){
		get_match_from_server(match_id);
	}
	
	//match가 있는 경우
	else{
		set_match_html(match);
	}
	
}

function get_match_from_server(match_id){
	var url = '/match/rest/searchMatch/'+player.region+'/'+match_id;
	fetch(url, {
		  headers:{
		    'Content-Type': 'application/json'
		  }
		})
		.then(res => res.json())
		.then(match => set_match_info_and_html(match))
		.catch(error => console.error('Error:', error));
	
	function set_match_info_and_html(match){
		match_list[match.matchId] = match;
		match_rank_sort(match);
		set_match_html(match);
		//remove_match_button(match.matchId);
	}
	
}

function match_rank_sort(match){
	match.matchPlayerList.sort(function( match_player_before, match_player_after){
		var result = 0;
		if(match_player_before.placement>match_player_after.placement){
			result = 1;
		}
		else {
			result = -1;
		}
		return result;
	});
}

function set_match_html(match){
	//매치 정보로 넣을 위치 찾고
	var target_html =  document.getElementById(match.matchId);
	//개인 기록 먼저 할당하고
	var match_html = get_match_html(match);
	target_html.appendChild(match_html);
	
	//버튼 교체
	button_change_hide(match.matchId);
}

//있는지 없는지 확인하는 거
function check_match_html(match_id){
	var result = true;
	var match_html = document.getElementById(match_id);
	if(match_html == undefined){
		result = false;
	}
	return result;
}
/*
function show_match(match_id){
	
}

function hide_match(match_id){
	
}
*/

function get_match_html(match){
	var match_html = document.createElement("div");
	match_html.setAttribute('class','match');
	match_html.setAttribute('id','match_'+match.matchId);
	match_html.setAttribute('value', match.matchId);
	match.matchPlayerList.forEach(function(match_player){
		var match_player_html = get_match_player_html(match_player);
		match_html.appendChild(match_player_html);
	});
	return match_html;
}

function get_match_player_html(match_player){

	var match_player_div = document.createElement("div");
	match_player_div.setAttribute('class','match_player_summary');

	var match_summary_html = get_match_summary_html(match_player);
	match_player_div.appendChild(match_summary_html);
	
	match_player.matchPlayerChampionList.forEach(function(match_player_champion){
		var champion_tag = champion_html(match_player_champion, match_player.tftSetNumberDetail);
		match_player_div.appendChild(champion_tag);
	});
	
	match_player.matchPlayerTraitList.forEach(function(match_player_trait){
		
	});
	
	return match_player_div;
}