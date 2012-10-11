package com.blue.user;

import org.junit.Assert;
import org.junit.Test;

import com.blue.common.User;
import com.blue.enums.Profession;
import com.blue.tools.FileUtil;

public class RoleSkillForProfessionTest {

	@Test
	public void testSetUserProfession() throws Exception{
		String s = FileUtil.readFileToString("role_skill.html");
		User user = new User();
		RoleSkillForProfession.setUserProfession(user, s);
		Assert.assertNotNull(user.getServerDuelConfigMap());
		Assert.assertEquals("亢龙枪法", user.getServerDuelConfigMap().get(Profession.刺杀系).getActiveSkill());
		Assert.assertEquals("金刚不坏", user.getServerDuelConfigMap().get(Profession.控制系).getAssistSkillA());
	}

}
