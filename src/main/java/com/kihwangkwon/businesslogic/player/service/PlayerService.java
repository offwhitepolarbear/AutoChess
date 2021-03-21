package com.kihwangkwon.businesslogic.player.service;

import com.kihwangkwon.businesslogic.player.domain.Player;
import com.kihwangkwon.riotapi.domain.RegionNation;

public interface PlayerService {
	Player getPlayerByName(RegionNation region,String playerName);
}
