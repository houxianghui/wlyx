package com.blue.monitor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.blue.common.User;
import com.blue.tools.PageService;
import com.blue.tools.Tools;


public class ShiKeMonitor {
	private static Logger logger = Logger.getLogger(ShiKeMonitor.class);
	private static Pattern pain = Pattern.compile("desPveSlavery\\((\\d+)\\)\">��ǲ");
	private static Pattern result = Pattern.compile("result\":\"(\\S+?)\"");
	//http://s4.verycd.9wee.com/modules/duel.php?act=pveslavery&action=myslavery&timeStamp=1285842393334&callback_func_name=ajaxCallback&callback_obj_name=my_pveslavery
	private static String SHI_KE_LIST = "modules/duel.php?act=pveslavery&action=myslavery&callback_func_name=ajaxCallback&callback_obj_name=my_pveslavery";
	//http://s4.verycd.9wee.com/modules/duel.php?act=pveslavery&action=despatchSubmit&rand=1285843187842&timeStamp=1285843174060
	private static String PAI_QIAN = "modules/duel.php?act=pveslavery&action=despatchSubmit";
	/**
	 * 1=ϰ�İ���
	 * 2=��Դ���
	 * 5=��������
	 * 3=���ұ���
	 * 4=������ľ
	 * 
	 * @param user
	 */
	public static void painShiKe(User user){
		String url = user.getUrl()+SHI_KE_LIST+Tools.getTimeStamp(true);
		String page = PageService.getPageWithCookie(url, user);
		Matcher m = pain.matcher(page);
		String[] step = user.getMenKeStep().split(",");
		while(m.find()){
			url = user.getUrl()+PAI_QIAN+Tools.getRandAndTime();
			for(int i = 0;i < step.length ;i++){
				String data = "pain_type="+step[i]+"&pve_id="+m.group(1)+"&role_name=&callback_func_name=ajaxCallback";
				page = PageService.postPage(url, data, user);
				Matcher r = result.matcher(page);
				if(r.find()){
					if(Tools.hexToString(r.group(1)).equals("��ǲ�ɹ�")){
						logger.info(user.getRoleName()+"��ǲ"+m.group(1)+"�ɹ�");
						break;
					}
				}
			}
		}
	}
}
