package com.kihwangkwon.staticdata.round.service;

import java.util.Collection;

import com.kihwangkwon.staticdata.round.domain.Rounds;

public interface RoundsService {
	public Collection<Rounds> getAllRounds();
	public Collection<Rounds> getRounds(String version);
	public int[] getRoundsCount(String version);
}
