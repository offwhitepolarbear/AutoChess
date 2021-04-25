package com.kihwangkwon.riotapi.domainconvert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.match.domain.Match;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayer;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerChampion;
import com.kihwangkwon.businesslogic.match.domain.MatchPlayerTrait;

@Service
public class JsonToMatchDomain {
	
	public Match getMatchObject(Map map) {
		
		Map metadata = (Map) map.get("metadata");
		String dataVersion = (String) metadata.get("data_version");
		String matchId = (String) metadata.get("match_id");
		Map info = (Map) map.get("info");
		Timestamp gameDateTime = new Timestamp(((BigInteger)info.get("game_datetime")).longValue());
		double gameLength = ((BigDecimal)info.get("game_length")).doubleValue();
		String gameVersion = (String) info.get("game_version");
		String queueId = ((BigInteger)info.get("queue_id")).toString();
		String tftSetNumber = ((BigInteger) info.get("tft_set_number")).toString();
	
		List<Map> playerList = (List<Map>) info.get("participants");
		
		List<MatchPlayer> matchPlayerList = mapListToMatchPlayerList(playerList, matchId);
		
		Match match = Match.builder()
				.matchId(matchId)
				.dataVersion(dataVersion)
				.gameVersion(gameVersion)
				.gameDatetime(gameDateTime)
				.gameLength(gameLength)
				.queueId(queueId)
				.tftSetNumber(tftSetNumber)
				.build();
		
		match.setMatchPlayerList(matchPlayerList);
		
		return match;
	}
	
	private List<MatchPlayer> mapListToMatchPlayerList(List<Map> playerList,String matchId){
		List<MatchPlayer> matchPlayerList = new ArrayList<MatchPlayer>();
		for (Map player:playerList) {
			MatchPlayer matchPlayer = mapToMatchPlayer(player, matchId);
			matchPlayerList.add(matchPlayer);
		}
		return matchPlayerList;
	}
	
	private MatchPlayer mapToMatchPlayer(Map map, String matchId) {

		Map pet = (Map) map.get("companion");
		final String contentId =  (String) pet.get("content_ID");
		final String skinId = ((BigInteger)pet.get("skin_ID")).toString();
		final String species = (String) pet.get("species");
		String puuid = (String) map.get("puuid");
		final int goldLeft = objectToInt(map.get("gold_left"));
		final int lastRound = objectToInt(map.get("last_round"));
		final int level = objectToInt(map.get("level"));
		final int placement = objectToInt(map.get("placement"));
		final int damegeToPlayers = objectToInt(map.get("total_damage_to_players"));
		final int playersEliminated = objectToInt(map.get("players_eliminated"));
		final double timeEliminated = ((BigDecimal)map.get("time_eliminated")).doubleValue();
		
		List<Map> championMapList = (List<Map>) map.get("units");
		List<Map> traitMapList = (List<Map>) map.get("traits");
		List<MatchPlayerChampion> matchPlayerChampionList = mapListToMatchPlayerChampionList(championMapList, matchId, puuid);
		List<MatchPlayerTrait> matchPlayerTraitList = mapListToMatchPlayerTraitList(traitMapList, matchId, puuid);
		
		MatchPlayer player = MatchPlayer.builder()
				.matchId(matchId)
				.puuid(puuid)
				.goldLeft(goldLeft)
				.lastRound(lastRound)
				.level(level)
				.placement(placement)
				.damegeToPlayers(damegeToPlayers)
				.playersEliminated(playersEliminated)
				.placement(placement)
				.timeEliminated(timeEliminated)
				.contentId(contentId)
				.skinId(skinId)
				.species(species)
				.build();
		
		player.setMatchPlayerChampionList(matchPlayerChampionList);
		player.setMatchPlayerTraitList(matchPlayerTraitList);
		
		return player;
	}

	
	private List<MatchPlayerChampion> mapListToMatchPlayerChampionList(List<Map> mapList, String matchId, String puuid){
		List<MatchPlayerChampion> championList = new ArrayList<MatchPlayerChampion>();
		for(Map map:mapList) {
			MatchPlayerChampion champion = mapToMatchPlayerChampion(map,matchId,puuid);
			championList.add(champion);
		}
		return championList;
	}
	
	private MatchPlayerChampion mapToMatchPlayerChampion(Map map, String matchId, String puuid) {
		String championId = (String) map.get("character_id");
		
		String name = (String) map.get("name");
		
		//코스트 0부터 시작하게 되어 있음 +1 해서 1부터 시작하게
		int cost = objectToInt(map.get("rarity"))+1;
		int star = objectToInt(map.get("tier"));
		String chosen;
		
		try {
			chosen = (String) map.get("chosen");
		}catch (Exception e) {
			chosen = null;
		}
		
		int itemOne = 0;
		int itemTwo = 0;
		int itemThree = 0;
		try {
			
			List<BigInteger> itemList = (List<BigInteger>) map.get("items");
			
			if(itemList.size()>0) {
				try {
					itemOne = itemList.get(0).intValue();
				}catch (Exception e) {
				}
				try {
					itemTwo=itemList.get(1).intValue();
				}catch (Exception e) {
					//아이템2가 없는 경우 에러발생
				}
				try {
					itemThree=itemList.get(2).intValue();
				}catch (Exception e) {
					//아이템3이 없는 경우 에러발생
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		MatchPlayerChampion champion = MatchPlayerChampion.builder()
				.matchId(matchId)
				.puuid(puuid)
				.championId(championId)
				.cost(cost)
				.star(star)
				.chosen(chosen)
				.name(name)
				.itemOne(itemOne)
				.itemTwo(itemTwo)
				.itemThree(itemThree)
				.build();
		
		return champion;
	}
	
	private List<MatchPlayerTrait> mapListToMatchPlayerTraitList(List<Map> mapList, String matchId, String puuid){
		List<MatchPlayerTrait> traitList = new ArrayList<MatchPlayerTrait>();
		for(Map map:mapList) {
			MatchPlayerTrait trait = mapToMatchPlayerTrait( map,  matchId,  puuid);
			traitList.add(trait);
		}
		return traitList;
	}

	private MatchPlayerTrait mapToMatchPlayerTrait(Map map, String matchId, String puuid) {
		final String traitName = (String) map.get("name");
		//int unitCount = map.get("num_units");
		int tierCurrent = objectToInt(map.get("tier_current"));
		//int tierTotal = map.get("tier_total");
		
		MatchPlayerTrait trait = MatchPlayerTrait
				.builder()
				.matchId(matchId)
				.puuid(puuid)
				.traitName(traitName)
				.traitTier(tierCurrent)
				.build();
		
		return trait;
	}
	
	private int objectToInt(Object object) {
		return ((BigInteger)object).intValue();
	}
	
}
