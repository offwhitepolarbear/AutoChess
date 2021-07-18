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
