package com.kihwangkwon.businesslogic.version.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kihwangkwon.businesslogic.version.domain.GameVersionInfo;
@Service
public class GameVersionServiceImpl implements GameVersionInfoService{
	
	private GameVersionInfoRepository gameVersionInfoRepository;
	private String[] results;
	
	@Autowired
	public GameVersionServiceImpl (GameVersionInfoRepository gameVersionInfoRepository) {
		this.gameVersionInfoRepository = gameVersionInfoRepository;
	}
	
	@Override
	public List<GameVersionInfo> getAllGameVersionInfo() {
		Sort sort = Sort.by(Sort.Direction.ASC,"setNumber");
		return gameVersionInfoRepository.findAll(sort);
	}
	
	@Override
	public double getSetNumber(String gameVersion) {
		List<GameVersionInfo> versionInfoList = getAllGameVersionInfo();
		double result = 0;

		gameVersion = extractGameVersion(gameVersion);
		                                                                                                                                                                                                                                                                    
		for(int i=0; i<versionInfoList.size(); i++) {
			
			//for문 마지막이 아닌경우 처리로직 (마지막인 경우 다음 gameVersiond 가져오다가 에러 발생)
			if(i<versionInfoList.size()-1) {
				GameVersionInfo recentVersion = versionInfoList.get(i);
				GameVersionInfo nextVersion = versionInfoList.get(i+1);
				boolean setVersionIsTrue = isThisSet(gameVersion, recentVersion, nextVersion);
				if(setVersionIsTrue) {
					//세트 넘버 뽑아서 할당하고 break로 for문 탈출
					double thisSet = recentVersion.getSetNumber();
					result = thisSet;
					break;
				}
			}
			//마지막에 걸린경우 
			else {
				//앞에서 안걸린 경우 더 계산할 것도 없이 제일 마지막 세트에 할당
				//세트 넘버 뽑아서 할당하고 break로 for문 탈출
				double lastSet = versionInfoList.get(i).getSetNumber();
				result = lastSet;
				break;
			}
		}
		return result;
	}
	
	private String extractGameVersion(String gameVersion) {
		StringBuffer result = new StringBuffer();
		gameVersion = gameVersion.replaceFirst("Version ", "");
		//정규식이라 그냥 . 이 아닌 [.]으로 split 실행
		String[] gameVersionSplit = gameVersion.split("[.]");
		result.append(gameVersionSplit[0]);
		result.append(".");
		result.append(gameVersionSplit[1]);
		return result.toString();
	}
	
	private boolean isThisSet(String gameVersion, GameVersionInfo recentVersion, GameVersionInfo nextVersion) {
		
		boolean result = false;
		String recentGameVersion = recentVersion.getGameVersion();
		String nextGameVersion = nextVersion.getGameVersion();
		
		String[] gameVersionSplit = gameVersion.split("[.]");
		String[] recentGameVersionSplit = recentGameVersion.split("[.]");
		String[] nextGameVersionSplit = nextGameVersion.split("[.]");
		
		int mainVersion = Integer.parseInt(gameVersionSplit[0]);
		int subVersion = Integer.parseInt(gameVersionSplit[1]);
		
		int recentMainVersion = Integer.parseInt(recentGameVersionSplit[0]);
		int recentSubVersion = Integer.parseInt(recentGameVersionSplit[1]);
		
		int nextMainVersion = Integer.parseInt(nextGameVersionSplit[0]);
		int nextSubVersion = Integer.parseInt(nextGameVersionSplit[1]);
		
		
		if(mainVersion>=recentMainVersion && subVersion>=recentSubVersion) {
			//쿼리에서 검색된 해당 버전 이상이고
			if(mainVersion==nextMainVersion && subVersion<nextSubVersion) {
				//다음 세트와 앞자리가 같고 서브버전이 작거나
				result = true;
			}
			
			if(mainVersion<nextMainVersion) {
				//아얘 앞자리가 작으면 해당세트에 속함
				result = true;
			}
			
		}
		
		return result;
	}
	
}
