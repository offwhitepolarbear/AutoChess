package com.kihwangkwon.staticdata.round.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kihwangkwon.staticdata.round.domain.Rounds;
@Service
public class RoundsServiceImpl implements RoundsService {
	
	private RoundsRepository roundsRepository;
	
	@Autowired
	public RoundsServiceImpl(RoundsRepository roundsRepository) {
		this.roundsRepository = roundsRepository;
	}

	@Override
	public Collection<Rounds> getAllRounds() {
		Sort sort = Sort.by(Sort.Direction.ASC, "mainRound", "subRound");
		//sort.by
		return roundsRepository.findAll(sort);
	}

	@Override
	public Collection<Rounds> getRounds(String version) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int[] getRoundsCount(String version) {
		double doubleVersion = Double.parseDouble(version); 
		//몇세트 까지 있는지 선언(현재 7세트 까지 존재)
		int mainRoundCount = 7;
		int[] roundsCount = new int[mainRoundCount+1];
		Sort sort = Sort.by(Sort.Direction.ASC, "mainRound", "subRound");
		Collection<Rounds> roundsInfo = roundsRepository.findByTftSetNumberDetail(doubleVersion, sort);
		for(Rounds rounds : roundsInfo) {
			roundsCount[rounds.getMainRound()] += 1;
		}
		return roundsCount;
	}
	
}
