package com.blue.fyzb;

import com.blue.enums.Profession;

public class ServerDuelConfig {
	private Profession challengProfession;
	
	private String activeSkill;
	private String assistSkillA;
	private String assistSkillB;
	public Profession getChallengProfession() {
		return challengProfession;
	}
	public void setChallengProfession(Profession challengProfession) {
		this.challengProfession = challengProfession;
	}
	public String getActiveSkill() {
		return activeSkill;
	}
	public void setActiveSkill(String activeSkill) {
		this.activeSkill = activeSkill;
	}
	public String getAssistSkillA() {
		return assistSkillA;
	}
	public void setAssistSkillA(String assistSkillA) {
		this.assistSkillA = assistSkillA;
	}
	public String getAssistSkillB() {
		return assistSkillB;
	}
	public void setAssistSkillB(String assistSkillB) {
		this.assistSkillB = assistSkillB;
	}
}
