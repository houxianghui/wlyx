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
		Assert.assertEquals("����ǹ��", user.getServerDuelConfigMap().get(Profession.��ɱϵ).getActiveSkill());
		Assert.assertEquals("��ղ���", user.getServerDuelConfigMap().get(Profession.����ϵ).getAssistSkillA());
	}

}
