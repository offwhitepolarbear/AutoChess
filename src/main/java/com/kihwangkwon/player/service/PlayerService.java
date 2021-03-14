package com.kihwangkwon.player.service;

import com.kihwangkwon.domain.db.Player;
import com.kihwangkwon.riotapi.domain.RegionNation;

public interface PlayerService {
	Player getPlayerByName(RegionNation region,String playerName);
}
