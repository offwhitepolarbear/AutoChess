
//전체 매치 목록 가져오는 작업
//결과가 없는 매치도 있다
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
//상세는 아니고 목록만 가져오는 작업 상세 가져오는 작업은 따로
function set_player_match_list(server_player_match_list){
	player_match_list = server_player_match_list;
	var match_list_div = document.getElementById("match_list");
	player_match_list.forEach(function(player_match){
		var player_match_div = get_player_match_html(player_match);
		match_list_div.append(player_match_div);
	});
}

function get_player_match_html(player_match){
	var player_match_div = document.createElement("div");
	player_match_div.setAttribute('id', player_match.matchId);
	player_match_div.setAttribute('class', 'player_match');
	player_match_div.appendChild(get_match_button_html(player_match.matchId));
	return player_match_div;
}

function remove_button(match_id){
	var match_button = document.getElementById("match_button_"+match_id);
	match_button.remove();
}


//초기에 사용할 버튼
//검색된 적이 있는 전적인 경우에는 다른 버튼으로 변경됨
function get_match_button_html(match_id){
	var match_button_html = document.createElement("i");
	match_button_html.setAttribute('id','match_button_'+match_id);
	match_button_html.setAttribute('class','fa fa-refresh fa-2x');
	match_button_html.setAttribute('aria-hidden','true');
	match_button_html.setAttribute("onclick", "javascript:get_match('"+match_id+"')");
	return match_button_html;
}
/*
function set_match_from_db_button_html(match_id){

	var match_button_html = document.getElementById(match_id);
	match_button_html.setAttribute('id','match_button_'+match_id);
	match_button_html.setAttribute('class','fa fa-search-plus fa-2x');
	match_button_html.setAttribute('aria-hidden','true');
	match_button_html.setAttribute("onclick", "javascript:get_match('"+match_id+"')");
}
*/
function get_match_from_db_button_html(match_id){

	var match_button_html = document.createElement("i");
	match_button_html.setAttribute('id','match_button_'+match_id);
	match_button_html.setAttribute('class','fa fa-search-plus fa-2x');
	match_button_html.setAttribute('aria-hidden','true');
	match_button_html.setAttribute("onclick", "javascript:get_match('"+match_id+"')");
	
	return match_button_html;
}

function get_match_show_button(match_id){
	
	var match_show_button = document.createElement("i");
	match_button_html.setAttribute('id','match_button_'+match_id);
	match_show_button.setAttribute('class','fa fa-search-plus fa-2x');
	match_show_button.setAttribute('aria-hidden','true');
	match_show_button.setAttribute('onclick', "javascript:show_match('"+match_id+"')");
	
	return match_show_button;
}

function get_match_hide_button(match_id){
	
	var match_hide_button = document.createElement("i");
	match_button_html.setAttribute('id','match_button_'+match_id);	
	match_hide_button.setAttribute('class','fa fa-search-minus');
	match_hide_button.setAttribute('aria-hidden','true');
	match_hide_button.setAttribute('onclick', "javascript:hide_match('"+match_id+"')");
	
	return match_hide_button;
}


function button_change_show(match_id){
	var match_button = document.getElementById('match_button_'+match_id);
	match_button.setAttribute('class', 'fa fa-search-plus fa-2x inline_block');
	match_button.setAttribute('onclick', "javascript:show_match('"+match_id+"')");
}

function button_change_hide(match_id){
	var match_button = document.getElementById('match_button_'+match_id);
	match_button.setAttribute('class', 'fa fa-search-minus fa-2x inline_block');
	match_button.setAttribute('onclick', "javascript:hide_match('"+match_id+"')");
}


//표시된 매치내역 감추는 기능 
function hide_match(match_id){
	// 매치내역 안보이게 숨기고
	var match_div = document.getElementById('match_'+match_id);
	match_div.classList.add("display_hide");
	//버튼 변경
	button_change_show(match_id);
}



function show_match(match_id){
	//매치내역 보이게 설정하고
	var match_div = document.getElementById('match_'+match_id);
	match_div.classList.remove("display_hide");
	
	//버튼변경
	button_change_hide(match_id);
}

