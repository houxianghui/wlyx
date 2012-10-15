package com.blue.serverarean;

import java.util.HashMap;
import java.util.Map;

public class ServerAreanConfig {
	private boolean needAutoSign;
	private boolean needAutoChallenge;
	private String signId;
	
	private Map<String,AutoChallengeConfig> challengeMap = new HashMap<String, AutoChallengeConfig>();

	public boolean isNeedAutoSign() {
		return needAutoSign;
	}

	public void setNeedAutoSign(boolean needAutoSign) {
		this.needAutoSign = needAutoSign;
	}

	public boolean isNeedAutoChallenge() {
		return needAutoChallenge;
	}

	public void setNeedAutoChallenge(boolean needAutoChallenge) {
		this.needAutoChallenge = needAutoChallenge;
	}

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}
	public void addChallengeConfig(AutoChallengeConfig config){
		challengeMap.put(config.getName(), config);
	}
	public AutoChallengeConfig getChallengeConfig(String name){
		return challengeMap.get(name);
	}
}
