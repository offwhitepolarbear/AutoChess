package com.kihwangkwon.businesslogic.match.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MatchPlayerTrait {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String matchId;
	String playerId;
	String traitName;
	int traitTier;
	String traitTierColor;
    //"name": "Set4_Brawler", 시너지명
    //"num_units": 3, 해당 시너지 기물 수
    //"style": 1, ??
    //"tier_current": 1, 시너지 등급(브론즈 실버 골드 크로마)
    //"tier_total": 4 전체 시너지 등급 ()
	//tier current>0 일때만 저장 시켜야 될듯 발동도 안된 시너지 통계를 낼 필요가 없음
}
/*통계용 테이블 해당 매치의 시너지 상태는 챔피언 정보만 가지고도 확인 가능 집계 할 때 그걸 다 돌리긴 어려우니까 따로 수집*/