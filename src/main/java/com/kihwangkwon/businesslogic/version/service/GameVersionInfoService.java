package com.kihwangkwon.businesslogic.version.service;

import java.util.List;

import com.kihwangkwon.businesslogic.version.domain.GameVersionInfo;

public interface GameVersionInfoService {
	public List<GameVersionInfo> getAllGameVersionInfo();
	public double getSetNumber(String gameVersion);
}
